import java.awt.event.KeyEvent;
import java.security.Key;
import javax.swing.*;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.KeyListener;

public class BigBarrier 
{
    Barrier[] barrier = new Barrier[12];

    BigBarrier(int yPos)
    {   
        int xChange = 0;
        for(int i = 0; i < 12; i++)
        {
            barrier[i] = new Barrier();
            barrier[i].xPos += xChange;
            barrier[i].yPos -= yPos;
            if(i % 4 == 3) {xChange += 160;}
            else {xChange += 30;}
        }
    }
}
