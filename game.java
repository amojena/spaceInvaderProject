import java.awt.event.KeyEvent;
import java.security.Key;
import javax.swing.*;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.KeyListener;

import javax.sound.sampled.*;
import java.io.*;

public class game extends JPanel implements KeyListener, Runnable {
    public static int WIDTH  = 800;
    public static int HEIGHT = 700;
    Player p = new Player();
    //Invader i = new Invader();
    Herd h = new Herd();

    public void paint(Graphics g) 
    {
        Graphics2D g2 = (Graphics2D) g;
        ImageIcon newImageSprite = new ImageIcon("background.png");
        Image background = newImageSprite.getImage();
        g2.drawImage(background, 0,0, null);
        g2.drawImage(p.representation, p.xPos, p.yPos, null);
        drawHerd(g2);
    }

    public void drawHerd(Graphics2D g2)
    {
        Invader temp;
        for(int i = 0; i < h.rowInv; i++)
        {
            for(int j = 0; j < h.colInv; j++)
            {
                temp = h.herd[i][j];
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

        while(true) {
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
            p.xPos += 6;
        }
        if(key == KeyEvent.VK_LEFT)
        {
            p.xPos -= 6;
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

