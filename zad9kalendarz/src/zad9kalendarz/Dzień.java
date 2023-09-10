package zad9kalendarz;

import javax.swing.*;
import java.awt.*;

public class Dzieñ extends JPanel{
    JLabel liczba;
    JLabel nazwa;

    String tygodnie[] = {"pon.", "wt.", "œr.", "czw.", "pt.", "sob.", "niedz."};

    public Dzieñ(int dzieñ_tygodnia) {
    
        setSize(25, 10);
       	setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        if (dzieñ_tygodnia == 6)
            setBackground(Color.RED);

        liczba = new JLabel();    
        
        nazwa = new JLabel(tygodnie[dzieñ_tygodnia]);
 
        add(liczba);
        add(nazwa);
    }

    public void ustaw_Liczbe(int n) {
        liczba.setText("" + n);
    }
}