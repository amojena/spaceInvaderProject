import java.awt.event.KeyEvent;
import java.security.Key;
import javax.swing.*;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.KeyListener;

public class Player 
{
    public int xPos = 400;
    public static int yPos = 600;
    public static int lives = 1;
    public static boolean hit = false;

    ImageIcon newImageSprite = new ImageIcon("ship.png");
    Image representation = newImageSprite.getImage(); 

    Player(){}
}
