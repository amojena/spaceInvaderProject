import java.awt.event.KeyEvent;
import java.security.Key;
import javax.swing.*;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.KeyListener;

public class Herd 
{
    public static int rowInv = 3;
    public static int colInv = 7;
    private int factor = 1;
    private int changeY = 0;
    Invader[][] herd = new Invader[rowInv][colInv];
    public static int alive = rowInv * colInv;

    Herd()
    {
        int xpos = 200;
        int ypos = 60;
        for(int i = 0; i < rowInv; i++)
        {
            for(int j = 0; j < colInv; j++)
            {
                herd[i][j] = new Invader();
                herd[i][j].xPos = xpos;
                herd[i][j].yPos = ypos;
                xpos += 50;
            }
            ypos += 60;
            xpos = 200;
        }
    }

    public void move(Player player)
    {
        if (herd[0][colInv -1].xPos > game.WIDTH - 60 || herd[0][0].xPos < 20)
        {
            factor *= -1;
            changeY = 12;
        }   

        for(int i = 0; i < rowInv; i++)
        {   
            for(int j = 0; j < colInv; j++)
            {   
                herd[i][j].xPos += 3 * factor;
                herd[i][j].yPos += changeY;
                if ( (herd[i][j].yPos >= Player.yPos && herd[i][j].yPos + 45 <= Player.yPos + 64) && ( (herd[i][j].xPos >= player.xPos && herd[i][j].xPos <= player.xPos + 64) || (herd[i][j].xPos + 35 >= player.xPos && herd[i][j].xPos + 35 <= player.xPos + 64)))
                {
                    Player.lives--;
                    player.xPos = game.WIDTH/2;
                    if (Player.lives == 0) {game.play = false;}
                }
            }
        }
        changeY = 0; 
    }
}
