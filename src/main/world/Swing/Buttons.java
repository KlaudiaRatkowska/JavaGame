package main.world.Swing;

import javax.swing.*;
import java.awt.*;

public class Buttons extends JButton{

    private final int LENGTH = 50;
    private final int WIDTH = 50;
    private final int HorlDist = 10;
    private final int VerlDist = 10;

    public Buttons(int col, int row, char gatunek) {

        setPreferredSize(new Dimension(WIDTH, LENGTH));

        setText(Character.toString(gatunek));
        setBounds(col*(HorlDist + WIDTH),row*(VerlDist+LENGTH),WIDTH,LENGTH);
    }


}