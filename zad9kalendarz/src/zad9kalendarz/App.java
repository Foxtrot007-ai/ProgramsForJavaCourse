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
    private JPanel miesi�ceRoku;
    private JPanel dniMiesi�ca;
    private JList nast�pnyMiesi�c;
    private JList aktualnyMiesi�c;
    private JList poprzedniMiesi�c;
    private JSpinner spinner_Rok;
    private JSlider slider_Miesi�c;
    private JToolBar toolbar;
    private JPanel aktualny_rok;

    private GregorianCalendar kalendarz;

    private Miesi�c miesi�ce[];

    public App() {
       
    	f = new JFrame("Kalendarz");
     
        kalendarz = (GregorianCalendar) GregorianCalendar.getInstance();
       
        tabbedPane = new JTabbedPane();
        dniMiesi�ca = new JPanel();
        miesi�ceRoku = new JPanel();

        toolbar = new JToolBar();
       
        //konstruktory
        aktualny_rok = new JPanel(new GridLayout(6, 2, 1, 1));
        
        poprzedniMiesi�c = new JList(new ListaMiesi�cy());
        poprzedniMiesi�c.setCellRenderer(new MonthCellRenderer());
        poprzedniMiesi�c.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        aktualnyMiesi�c = new JList(new ListaMiesi�cy());
        aktualnyMiesi�c.setCellRenderer(new MonthCellRenderer());
        aktualnyMiesi�c.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        nast�pnyMiesi�c = new JList(new ListaMiesi�cy());
        nast�pnyMiesi�c.setCellRenderer(new MonthCellRenderer());
        nast�pnyMiesi�c.setBorder(BorderFactory.createLineBorder(Color.black, 2));
      
        spinner_Rok = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
        spinner_Rok.setValue(kalendarz.getTime().getYear() + 1900);
        
        slider_Miesi�c = new JSlider(1,12,kalendarz.getTime().getMonth() + 1);
       
        toolbar.add(spinner_Rok);
        toolbar.add(slider_Miesi�c);
        
        miesi�ce = new Miesi�c[12];
        
        //przekazywanie jpaneli miesiecy
        Date temp = kalendarz.getTime();
        kalendarz.set(kalendarz.get(Calendar.YEAR), Calendar.JANUARY, 1);
        for (int i = 0; i < 12; i++) {
        	miesi�ce[i] = new Miesi�c(kalendarz, this);
            aktualny_rok.add(miesi�ce[i]);
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

        slider_Miesi�c.addChangeListener(e -> {
        	kalendarz.set(Calendar.MONTH, slider_Miesi�c.getValue() - 1);
            fireContentsChanged();
        });

        poprzedniMiesi�c.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (kalendarz.get(Calendar.YEAR) == 1 && kalendarz.get(Calendar.MONTH) == Calendar.JANUARY)
                    return;
                kalendarz.add(Calendar.MONTH, -1);
                fireContentsChanged();
            }
        });

        nast�pnyMiesi�c.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
            	kalendarz.add(Calendar.MONTH, 1);
                fireContentsChanged();
            }
        });
        
       
        
        miesi�ceRoku.add(aktualny_rok);
        
        dniMiesi�ca.add(poprzedniMiesi�c);
        dniMiesi�ca.add(aktualnyMiesi�c);
        dniMiesi�ca.add(nast�pnyMiesi�c);
        
        tabbedPane.add(new JScrollPane(miesi�ceRoku), "");
        tabbedPane.addTab(null, dniMiesi�ca);
        
       
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

        for (Miesi�c m : miesi�ce) {
            m.uaktualnij(kalendarz);
            kalendarz.add(Calendar.MONTH, 1);
        }
        kalendarz.setTime(temp);
        
        ((ListaMiesi�cy) aktualnyMiesi�c.getModel()).zmie�_date(temp);
        
        if (temp.getYear() + 1900 != 1 || temp.getMonth() != 0) {
        	temp.setMonth(temp.getMonth() - 1);
            ((ListaMiesi�cy) poprzedniMiesi�c.getModel()).zmie�_date(temp);
        } else {
        	temp.setMonth(temp.getMonth() - 1);
        	((ListaMiesi�cy) poprzedniMiesi�c.getModel()).zmie�_date(null);
        }
            
        
        	
        temp.setMonth(temp.getMonth() + 2);
        ((ListaMiesi�cy) nast�pnyMiesi�c.getModel()).zmie�_date(temp);
        
       
    }

    public void fireContentsChanged() {
        uaktualnij_dane();

        tabbedPane.setTitleAt(0, "" + spinner_Rok.getValue());
        tabbedPane.setTitleAt(1, new SimpleDateFormat("MMMM").format(kalendarz.getTime()));
        spinner_Rok.setValue(kalendarz.get(Calendar.YEAR));
        slider_Miesi�c.setValue(kalendarz.get(Calendar.MONTH) + 1);

    }

    public void zmie�_strone(int i) {
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
