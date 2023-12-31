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
    private JPanel miesiąceRoku;
    private JPanel dniMiesiąca;
    private JList następnyMiesiąc;
    private JList aktualnyMiesiąc;
    private JList poprzedniMiesiąc;
    private JSpinner spinner_Rok;
    private JSlider slider_Miesiąc;
    private JToolBar toolbar;
    private JPanel aktualny_rok;

    private GregorianCalendar kalendarz;

    private Miesiąc miesiące[];

    public App() {
       
    	f = new JFrame("Kalendarz");
     
        kalendarz = (GregorianCalendar) GregorianCalendar.getInstance();
       
        tabbedPane = new JTabbedPane();
        dniMiesiąca = new JPanel();
        miesiąceRoku = new JPanel();

        toolbar = new JToolBar();
       
        //konstruktory
        aktualny_rok = new JPanel(new GridLayout(6, 2, 1, 1));
        
        poprzedniMiesiąc = new JList(new ListaMiesięcy());
        poprzedniMiesiąc.setCellRenderer(new MonthCellRenderer());
        poprzedniMiesiąc.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        aktualnyMiesiąc = new JList(new ListaMiesięcy());
        aktualnyMiesiąc.setCellRenderer(new MonthCellRenderer());
        aktualnyMiesiąc.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        następnyMiesiąc = new JList(new ListaMiesięcy());
        następnyMiesiąc.setCellRenderer(new MonthCellRenderer());
        następnyMiesiąc.setBorder(BorderFactory.createLineBorder(Color.black, 2));
      
        spinner_Rok = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
        spinner_Rok.setValue(kalendarz.getTime().getYear() + 1900);
        
        slider_Miesiąc = new JSlider(1,12,kalendarz.getTime().getMonth() + 1);
       
        toolbar.add(spinner_Rok);
        toolbar.add(slider_Miesiąc);
        
        miesiące = new Miesiąc[12];
        
        //przekazywanie jpaneli miesiecy
        Date temp = kalendarz.getTime();
        kalendarz.set(kalendarz.get(Calendar.YEAR), Calendar.JANUARY, 1);
        for (int i = 0; i < 12; i++) {
        	miesiące[i] = new Miesiąc(kalendarz, this);
            aktualny_rok.add(miesiące[i]);
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

        slider_Miesiąc.addChangeListener(e -> {
        	kalendarz.set(Calendar.MONTH, slider_Miesiąc.getValue() - 1);
            fireContentsChanged();
        });

        poprzedniMiesiąc.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (kalendarz.get(Calendar.YEAR) == 1 && kalendarz.get(Calendar.MONTH) == Calendar.JANUARY)
                    return;
                kalendarz.add(Calendar.MONTH, -1);
                fireContentsChanged();
            }
        });

        następnyMiesiąc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
            	kalendarz.add(Calendar.MONTH, 1);
                fireContentsChanged();
            }
        });
        
       
        
        miesiąceRoku.add(aktualny_rok);
        
        dniMiesiąca.add(poprzedniMiesiąc);
        dniMiesiąca.add(aktualnyMiesiąc);
        dniMiesiąca.add(następnyMiesiąc);
        
        tabbedPane.add(new JScrollPane(miesiąceRoku), "");
        tabbedPane.addTab(null, dniMiesiąca);
        
       
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

        for (Miesiąc m : miesiące) {
            m.uaktualnij(kalendarz);
            kalendarz.add(Calendar.MONTH, 1);
        }
        kalendarz.setTime(temp);
        
        ((ListaMiesięcy) aktualnyMiesiąc.getModel()).zmień_date(temp);
        
        if (temp.getYear() + 1900 != 1 || temp.getMonth() != 0) {
        	temp.setMonth(temp.getMonth() - 1);
            ((ListaMiesięcy) poprzedniMiesiąc.getModel()).zmień_date(temp);
        } else {
        	temp.setMonth(temp.getMonth() - 1);
        	((ListaMiesięcy) poprzedniMiesiąc.getModel()).zmień_date(null);
        }
            
        
        	
        temp.setMonth(temp.getMonth() + 2);
        ((ListaMiesięcy) następnyMiesiąc.getModel()).zmień_date(temp);
        
       
    }

    public void fireContentsChanged() {
        uaktualnij_dane();

        tabbedPane.setTitleAt(0, "" + spinner_Rok.getValue());
        tabbedPane.setTitleAt(1, new SimpleDateFormat("MMMM").format(kalendarz.getTime()));
        spinner_Rok.setValue(kalendarz.get(Calendar.YEAR));
        slider_Miesiąc.setValue(kalendarz.get(Calendar.MONTH) + 1);

    }

    public void zmień_strone(int i) {
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
