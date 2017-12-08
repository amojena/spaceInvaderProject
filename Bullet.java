import java.awt.event.KeyEvent;
import java.security.Key;
import javax.swing.*;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.KeyListener;

public class Bullet 
{
    public int xPos;
    public int yPos;
    public boolean hit = false;

    ImageIcon newImageSprite = new ImageIcon("bullet.png");
    Image representation = newImageSprite.getImage(); 

    Bullet(Player player){
        xPos = player.xPos + 16;
        yPos = Player.yPos - 30;
    }
}
