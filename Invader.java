import java.awt.event.KeyEvent;
import java.security.Key;
import javax.swing.*;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.KeyListener;

public class Invader 
{
    public int xPos;
    public int yPos;
    public boolean hit = false;

    ImageIcon newImageSprite = new ImageIcon("cloudGuy.png");
    Image representation = newImageSprite.getImage(); 

    Invader(){}
}
