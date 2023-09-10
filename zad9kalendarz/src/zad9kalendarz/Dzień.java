package zad9kalendarz;

import javax.swing.*;
import java.awt.*;

public class Dzie� extends JPanel{
    JLabel liczba;
    JLabel nazwa;

    String tygodnie[] = {"pon.", "wt.", "�r.", "czw.", "pt.", "sob.", "niedz."};

    public Dzie�(int dzie�_tygodnia) {
    
        setSize(25, 10);
       	setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        if (dzie�_tygodnia == 6)
            setBackground(Color.RED);

        liczba = new JLabel();    
        
        nazwa = new JLabel(tygodnie[dzie�_tygodnia]);
 
        add(liczba);
        add(nazwa);
    }

    public void ustaw_Liczbe(int n) {
        liczba.setText("" + n);
    }
}