package zad9kalendarz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Miesi�c extends JPanel {
    App app;
    JLabel nazwa;
    JPanel tabelaDni;
    Date data;

    public Miesi�c(GregorianCalendar kalendarz, App a) {
        
        this.app = a;
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        this.setBackground(Color.white);
        data = kalendarz.getTime();

        nazwa = new JLabel(new SimpleDateFormat("MMMM").format(kalendarz.getTime()));
        

        tabelaDni = new JPanel(new GridLayout(6,7,1,1));

        for (int i = 0; i < 6 * 7; i++) {
            Dzie� day = new Dzie�(i % 7);
            tabelaDni.add(day);
        }

        uaktualnij(kalendarz);

        add(nazwa);
        add(tabelaDni);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                kalendarz.set(Calendar.MONTH, data.getMonth());
                app.fireContentsChanged();
                app.zmie�_strone(1);
            }   
        });
    }

    public void uaktualnij(GregorianCalendar kalendarz) {
       
    	for (int i = 0; i < 6 * 7; i++)
    		 tabelaDni.getComponent(i).setVisible(false);
        Date temp = kalendarz.getTime();

        int iter = (kalendarz.get(Calendar.DAY_OF_WEEK) + 5) % 7;
        while (kalendarz.get(Calendar.MONTH) == temp.getMonth()) {
        	tabelaDni.getComponent(iter).setVisible(true);
            ((Dzie�) tabelaDni.getComponent(iter)).ustaw_Liczbe(kalendarz.get(Calendar.DAY_OF_MONTH));
            kalendarz.add(Calendar.DAY_OF_MONTH, 1);
            iter++;
        }   
        kalendarz.setTime(temp);
    }
}
