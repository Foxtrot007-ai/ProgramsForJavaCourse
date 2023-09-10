package zad7rysunek;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class Aplikacja extends Frame {
   


    private Powierzchnia kartka;
    private ArrayList<Kreska> kreski; // contains strokes
    private Panel menu;
    private Graphics2D offscreenGraphics; // graphics for buffer image offscreen
    private Color wybrany;
    private Point pocz = new Point();
    private boolean wewnatrz = false;
 
    

    public Aplikacja() {
        super("Kolorowe kreski");
        setSize(1200, 800);
        setResizable(false);
        setBackground(Color.LIGHT_GRAY);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        wybrany = Color.black;
        kreski = new ArrayList<>();
        kartka = new Powierzchnia(kreski);
       
        stworz_menu();
        

        
        add(kartka, BorderLayout.CENTER);
        add(menu, BorderLayout.LINE_END);

        addListeners();
        setVisible(true);
        kartka.repaint();
    }

    private void stworz_menu() {
        menu = new Panel(new BorderLayout());
        menu.setBackground(Color.gray);

        Panel panel_kolorow = new Panel(new GridLayout(5, 1, 3, 3));

        CheckboxGroup wybierz_kolory = new CheckboxGroup();

        Checkbox wybierz_kolor[] = new Checkbox[5];
        Color[] kolory = new Color[5];
        kolory[0] = Color.black;
        kolory[1] = Color.red;
        kolory[2] = Color.blue;
        kolory[3] = Color.white;
        kolory[4] = Color.green;
        
        for (int i = 0; i < wybierz_kolor.length; i++) {
            if (i == 0)
            	wybierz_kolor[i] = new Checkbox("", wybierz_kolory, true);
            else
            	wybierz_kolor[i] = new Checkbox("", wybierz_kolory, false);
            
            wybierz_kolor[i].setBackground(kolory[i]);
            
            zmiana_koloru l1 = new zmiana_koloru(kolory[i]);
            wybierz_kolor[i].addItemListener(l1);
            
            panel_kolorow.add(wybierz_kolor[i]);
        }


        menu.add(panel_kolorow, BorderLayout.NORTH);
    }

    private void addListeners() {
        // request focus for key listeners
        requestFocusInWindow();
      
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	Aplikacja.this.dispose();
            }
            
            
        });

        kartka.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getComponent() == kartka && e.getButton() == MouseEvent.BUTTON1) {
                	pocz = e.getPoint();
                    kartka.set_poczatek(pocz);
                    kartka.repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getComponent() == kartka && e.getButton() == MouseEvent.BUTTON1) {     
                    if(wewnatrz)kreski.add(new Kreska(pocz, e.getPoint(), wybrany));
                    kartka.set_bool(false);
                    kartka.repaint();
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getComponent() == kartka)
                {
                	wewnatrz = false;
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getComponent() == kartka)
                {
                	wewnatrz = true;
                }
            }
            
        });

        kartka.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
            	System.out.println(wewnatrz);
                if (e.getComponent() == kartka) {
                    kartka.set_koniec(e.getPoint());
                    kartka.set_bool(true);
                    kartka.repaint();
                }
            }
        });

        kartka.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_BACK_SPACE:
                        kreski.clear();
                        break;
                    case KeyEvent.VK_L:
                    case KeyEvent.VK_B:
                        if (!kreski.isEmpty())
                            kreski.remove(kreski.size() - 1);
                        break;
                    case KeyEvent.VK_F:
                        if (!kreski.isEmpty())
                            kreski.remove(0);
                        break;
                }
                kartka.repaint();
            }
        });
    }

   

    class zmiana_koloru implements ItemListener {
        final Color color;

        zmiana_koloru(Color color) {
            this.color = color;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            wybrany = color;
        }
    }


}

