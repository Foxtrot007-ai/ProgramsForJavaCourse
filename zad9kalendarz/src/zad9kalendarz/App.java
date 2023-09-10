package zad9kalendarz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class App{
	private JFrame f;
    private JTabbedPane tabbedPane;
    private JPanel miesi¹ceRoku;
    private JPanel dniMiesi¹ca;
    private JList nastêpnyMiesi¹c;
    private JList aktualnyMiesi¹c;
    private JList poprzedniMiesi¹c;
    private JSpinner spinner_Rok;
    private JSlider slider_Miesi¹c;
    private JToolBar toolbar;
    private JPanel aktualny_rok;

    private GregorianCalendar kalendarz;

    private Miesi¹c miesi¹ce[];

    public App() {
       
    	f = new JFrame("Kalendarz");
     
        kalendarz = (GregorianCalendar) GregorianCalendar.getInstance();
       
        tabbedPane = new JTabbedPane();
        dniMiesi¹ca = new JPanel();
        miesi¹ceRoku = new JPanel();

        toolbar = new JToolBar();
       
        //konstruktory
        aktualny_rok = new JPanel(new GridLayout(6, 2, 1, 1));
        
        poprzedniMiesi¹c = new JList(new ListaMiesiêcy());
        poprzedniMiesi¹c.setCellRenderer(new MonthCellRenderer());
        poprzedniMiesi¹c.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        aktualnyMiesi¹c = new JList(new ListaMiesiêcy());
        aktualnyMiesi¹c.setCellRenderer(new MonthCellRenderer());
        aktualnyMiesi¹c.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        nastêpnyMiesi¹c = new JList(new ListaMiesiêcy());
        nastêpnyMiesi¹c.setCellRenderer(new MonthCellRenderer());
        nastêpnyMiesi¹c.setBorder(BorderFactory.createLineBorder(Color.black, 2));
      
        spinner_Rok = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
        spinner_Rok.setValue(kalendarz.getTime().getYear() + 1900);
        
        slider_Miesi¹c = new JSlider(1,12,kalendarz.getTime().getMonth() + 1);
       
        toolbar.add(spinner_Rok);
        toolbar.add(slider_Miesi¹c);
        
        miesi¹ce = new Miesi¹c[12];
        
        //przekazywanie jpaneli miesiecy
        Date temp = kalendarz.getTime();
        kalendarz.set(kalendarz.get(Calendar.YEAR), Calendar.JANUARY, 1);
        for (int i = 0; i < 12; i++) {
        	miesi¹ce[i] = new Miesi¹c(kalendarz, this);
            aktualny_rok.add(miesi¹ce[i]);
            kalendarz.add(Calendar.MONTH, 1);
        }

        kalendarz.setTime(temp);

        
        //ustawianie kontrolek
        spinner_Rok.addChangeListener(e -> {
            if ((int) spinner_Rok.getValue() < 1)
                return;
            kalendarz.set(Calendar.YEAR, (int) spinner_Rok.getValue());
            fireContentsChanged();
        });

        slider_Miesi¹c.addChangeListener(e -> {
        	kalendarz.set(Calendar.MONTH, slider_Miesi¹c.getValue() - 1);
            fireContentsChanged();
        });

        poprzedniMiesi¹c.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (kalendarz.get(Calendar.YEAR) == 1 && kalendarz.get(Calendar.MONTH) == Calendar.JANUARY)
                    return;
                kalendarz.add(Calendar.MONTH, -1);
                fireContentsChanged();
            }
        });

        nastêpnyMiesi¹c.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
            	kalendarz.add(Calendar.MONTH, 1);
                fireContentsChanged();
            }
        });
        
       
        
        miesi¹ceRoku.add(aktualny_rok);
        
        dniMiesi¹ca.add(poprzedniMiesi¹c);
        dniMiesi¹ca.add(aktualnyMiesi¹c);
        dniMiesi¹ca.add(nastêpnyMiesi¹c);
        
        tabbedPane.add(new JScrollPane(miesi¹ceRoku), "");
        tabbedPane.addTab(null, dniMiesi¹ca);
        
       
        f.add(tabbedPane,BorderLayout.CENTER);
        f.add(toolbar,BorderLayout.SOUTH);
        f.setSize(new Dimension(1200, 800));
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        fireContentsChanged();
    }


    private void uaktualnij_dane() {
        Date temp = kalendarz.getTime();
        kalendarz.set(kalendarz.get(Calendar.YEAR), Calendar.JANUARY, 1);

        for (Miesi¹c m : miesi¹ce) {
            m.uaktualnij(kalendarz);
            kalendarz.add(Calendar.MONTH, 1);
        }
        kalendarz.setTime(temp);
        
        ((ListaMiesiêcy) aktualnyMiesi¹c.getModel()).zmieñ_date(temp);
        
        if (temp.getYear() + 1900 != 1 || temp.getMonth() != 0) {
        	temp.setMonth(temp.getMonth() - 1);
            ((ListaMiesiêcy) poprzedniMiesi¹c.getModel()).zmieñ_date(temp);
        } else {
        	temp.setMonth(temp.getMonth() - 1);
        	((ListaMiesiêcy) poprzedniMiesi¹c.getModel()).zmieñ_date(null);
        }
            
        
        	
        temp.setMonth(temp.getMonth() + 2);
        ((ListaMiesiêcy) nastêpnyMiesi¹c.getModel()).zmieñ_date(temp);
        
       
    }

    public void fireContentsChanged() {
        uaktualnij_dane();

        tabbedPane.setTitleAt(0, "" + spinner_Rok.getValue());
        tabbedPane.setTitleAt(1, new SimpleDateFormat("MMMM").format(kalendarz.getTime()));
        spinner_Rok.setValue(kalendarz.get(Calendar.YEAR));
        slider_Miesi¹c.setValue(kalendarz.get(Calendar.MONTH) + 1);

    }

    public void zmieñ_strone(int i) {
        tabbedPane.setSelectedIndex(i);
    }

    class MonthCellRenderer extends JLabel implements ListCellRenderer {
        public MonthCellRenderer() {
            setHorizontalAlignment(CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            String string = (String) value;
            if (string.contains("niedziela"))
                setForeground(Color.RED);
            else
                setForeground(Color.BLACK);
            setText(string);
            return this;
        }
    }

}
