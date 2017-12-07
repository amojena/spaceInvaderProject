import java.awt.event.KeyEvent;
import java.security.Key;
import javax.swing.*;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.KeyListener;

public class Bomb 
{
    public int xPos;
    public int yPos;
    public boolean hit = false;

    ImageIcon newImageSprite = new ImageIcon("goomba.png");
    Image representation = newImageSprite.getImage(); 

    Bomb(Invader inv){
        xPos = inv.xPos;
        yPos = inv.yPos+30;
    }
}
