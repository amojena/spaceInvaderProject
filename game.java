import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;
import javax.swing.*;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.KeyListener;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Random;
import java.util.Vector;

public class game extends JPanel implements KeyListener, Runnable {
    public static int WIDTH  = 800;
    public static int HEIGHT = 700;
    public static boolean play = true;
    Player p = new Player();
    Herd h = new Herd();
    BigBarrier b = new BigBarrier(0);
    BigBarrier b1 = new BigBarrier(30);
    BigBarrier b2 = new BigBarrier(60);
    Bomb bomb = new Bomb(h.herd[0][0]);
    Random rand = new Random();
    public int randomRow;
    public int randomCol;
    public int fireRate = 11;
    public int score = 0;
    Vector <Bomb> bombsOnScreen = new Vector <Bomb>(1, 1);

    private static ImageIcon newImageSprite = new ImageIcon("gameOver.png");
    private static Image gameover = newImageSprite.getImage();
    

    public void paint(Graphics g) 
    {
        Graphics2D g2 = (Graphics2D) g;
        ImageIcon newImageSprite = new ImageIcon("background.png");
        Image background = newImageSprite.getImage();
        g2.drawImage(background, 0,0, null);
        g2.drawImage(p.representation, p.xPos, Player.yPos, null);
        g.setColor(Color.white);
        g.drawString("Lives: " + Integer.toString(Player.lives), 10, 20);
        g.drawString("Score: " + Integer.toString(score), 10, 40);

        generateBomb(g2);
        drawHerd(g2);
        drawBarriers(g2);
        if(!game.play)
            g2.drawImage(game.gameover, WIDTH/2, 10, null);
    }
    public void generateBomb(Graphics2D g2)
    {
        randomRow = rand.nextInt(Herd.rowInv);
        randomCol = rand.nextInt(Herd.colInv);
        boolean belowIsHit = randomRow != 2 && h.herd[randomRow +1][randomCol].hit;

        if (randomRow == 2 && (!h.herd[randomRow][randomCol].hit) || belowIsHit)
        { 
            if(rand.nextInt() % fireRate == 0)
                bombsOnScreen.add(new Bomb(h.herd[randomRow][randomCol]));
        }

        for(int i = 0; i < bombsOnScreen.size(); i++)
        {
            Bomb tempBomb = bombsOnScreen.elementAt(i);
            int playerRange = 64;
            int bombRange = 29;
            int rightOfPlayer = p.xPos + playerRange;
            int abovePlayer   = Player.yPos - playerRange;
            
            if (tempBomb != null) {

                if(tempBomb.yPos > Player.yPos + 5 || tempBomb.hit)
                    bombsOnScreen.remove(i);

                else if(tempBomb.yPos >= Player.yPos -10 && (tempBomb.xPos >= p.xPos && (tempBomb.xPos + bombRange) <= p.xPos + playerRange))
                {
                    tempBomb.hit = true;
                    bombsOnScreen.remove(i);
                    Player.hit = true;
                    Player.lives--;
                    if(Player.lives == 0)
                    {
                        g2.drawString("Game over", 10, 50);
                        game.play = false;
                        break;
                    }
                }

                
                else if (!tempBomb.hit)
                {
                    g2.drawImage(tempBomb.representation, tempBomb.xPos, tempBomb.yPos, null);
                    tempBomb.yPos += 2;
                }
            }
        }



    }
    public void drawBarriers(Graphics2D g2)
    {
        for(int i = 0; i < 12; i++){
            if (!b.barrier[i].hit)
                g2.drawImage(b.barrier[i].representation, b.barrier[i].xPos, b.barrier[i].yPos, null);
            if (!b1.barrier[i].hit)
               g2.drawImage(b1.barrier[i].representation, b1.barrier[i].xPos, b1.barrier[i].yPos, null);
            if (!b2.barrier[i].hit)
                g2.drawImage(b2.barrier[i].representation, b2.barrier[i].xPos, b2.barrier[i].yPos, null);
        }
    }

    public void drawHerd(Graphics2D g2)
    {
        Invader temp;
        for(int i = 0; i < h.rowInv; i++)
        {
            for(int j = 0; j < h.colInv; j++)
            {
                temp = h.herd[i][j];
                if(!temp.hit)
                    g2.drawImage(temp.representation, temp.xPos, temp.yPos, null);
            }
        }
    }

    public game() {addKeyListener(this);}

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }
    
    public void run() {
        Thread thread = new Thread(this);
        /*
        try{
            File backgroundMusic = new File("mario.mp3");
            AudioInputStream stream = AudioSystem.getAudioInputStream(backgroundMusic);
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(stream);
            audioClip.loop(1000);
        }

        catch (UnsupportedAudioFileException ex){
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        }

        catch (LineUnavailableException ex){
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        }

        catch (IOException ex){
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
        */
        while(game.play) {
            repaint();
            h.move();            
            try {
                thread.sleep(10);
            }
            catch(InterruptedException e) {
                System.out.println("Error");
            }
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Invaders");
        game g = new game();
        frame.add(g);
        frame.setSize(WIDTH,HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        g.run();
    }

    public void keyPressed(KeyEvent event)
    {
        //KeyEvent.VK_LEFT/VK_RIGHT/VK_SPACE
        int key = event.getKeyCode();
        if(key == KeyEvent.VK_RIGHT)
        {
            p.xPos += 9;
        }
        if(key == KeyEvent.VK_LEFT)
        {
            p.xPos -= 9;
        }

    }

    public void keyReleased(KeyEvent event)
    {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_SPACE)
        { 
            //shoot bullet
        }
    }

    public void keyTyped(KeyEvent event) { }
}

