package zad11graf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class App_graf extends JFrame{
	 	private Rysunek kartka;
	    private Graf g;
	    private JMenuBar menubar;
	    private JMenu opcje;
	    private JMenuItem zapisz;
	    private JMenuItem plik_txt;
	    private JScrollPane scrollpane;
	    private boolean wewnatrz = false;
	    private boolean przeciaganie = false;
	    private Point start;
	    private Point koniec;
	    private FileOutputStream fileOutputStream;
	    private FileInputStream fileInputStream; 
	    private ObjectOutputStream objectOutputStream;
	    private ObjectInputStream objectInputStream; 
	    private JFrame Zapis_txt;
	 
	    public Point zaokr¹glanie_do_64(Point p)
	    {
	    	Point temp = new Point();
	    	temp = p;
	    	
	    	float temp_x = (float)p.x / (float)64;
	    	int x;
	    	if(temp_x - Math.floor(temp_x) > 0.5)
	    		 x = (int)Math.ceil(temp_x);
	    	else x = (int)Math.floor(temp_x);
	    	
	    	float temp_y = (float)p.y / (float)64;
	    	int y;
	    	if(temp_y - Math.floor(temp_y) > 0.5)
	    		 y = (int)Math.ceil(temp_y);
	    	else y = (int)Math.floor(temp_y);
	    	
	    	temp.setLocation(new Point(x * 64,y * 64));
	    	return temp;
	    }

	    private Action zapisz_action = new AbstractAction("Zapisz") {
			 
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	try {
					fileOutputStream = new FileOutputStream("graf.ser");
				} catch (FileNotFoundException e2) {
					System.out.println("Nie znaleziono pliku");
					e2.printStackTrace();
				}
		        try {
					objectOutputStream = new ObjectOutputStream(fileOutputStream);
				} catch (IOException e2) {
					System.out.println("Coœ posz³o nie tak");
					e2.printStackTrace();
				}
		        
		      try {
				objectOutputStream.writeObject(g);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		      try {
				objectOutputStream.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      try {
				objectOutputStream.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    }
		};
		
		private Action otworz_zapis_txt = new AbstractAction("Zapis txt") {
			 
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	Zapis_txt.setVisible(true);
		    }
		};
		
	    public App_graf() {
	        super("Graf Paint");
	        setSize(1024, 800);
	        setResizable(false);
	        setBackground(Color.LIGHT_GRAY);
	        setLocationRelativeTo(null);
	        setLayout(new BorderLayout());
	        
	        try {
				fileInputStream = new FileInputStream("graf.ser");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        try {
				objectInputStream = new ObjectInputStream(fileInputStream);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        
	        try {
				g = (Graf)objectInputStream.readObject();
			} catch (ClassNotFoundException e1) {
				g = new Graf();
				e1.printStackTrace();
			} catch (IOException e1) {
				g = new Graf();
				e1.printStackTrace();
			}
	        
	        try {
				objectInputStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	        
	        
	        kartka = new Rysunek(g);
	        
			
	        
	        scrollpane = new JScrollPane(kartka);
	        add(scrollpane, BorderLayout.CENTER);
	        
	        stworz_menu();
	        
	        addListeners();
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        setVisible(true);
	        kartka.repaint();
	        kartka.aktualizuj_obraz();
	    }

	    private void stworz_menu() 
	    {
	    
	      zapisz_do_txt();
		     
	      menubar = new JMenuBar();
	      
	      opcje = new JMenu("Opcje");
	      
	      zapisz = new JMenuItem("Zapisz");
	      zapisz.setAction(zapisz_action);
	      
	      plik_txt = new JMenuItem("Zapisz plik do txt");
	      plik_txt.setAction(otworz_zapis_txt);
	      
	      opcje.add(zapisz);
	      opcje.add(plik_txt);
	      
	      menubar.add(opcje);
	      
	      setJMenuBar(menubar);
	    }
	    
	    public void zapisz_do_txt()
		{
			Zapis_txt = new JFrame();
			JButton b_zapisz;
			JLabel info;
			JTextField nazwa;
		          
			info = new JLabel();  
			info.setBounds(70,50, 300,20);  
			info.setText("Podaj nazwe pliku (*.txt)");
								    
			nazwa = new JTextField();  
			nazwa.setBounds(100,100, 100,20);  
			nazwa.setEditable(true);
					    
			b_zapisz = new JButton("Zapisz");
			b_zapisz.setBounds(100,150,100, 40);
			b_zapisz.setEnabled(true);
			b_zapisz.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent evt) {				
					try (PrintWriter out = new PrintWriter(nazwa.getText()+".txt")) {
			    	    out.print(g.toString());
			    	} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}					
					Zapis_txt.setVisible(false);
				}
			});
								
			Zapis_txt.add(b_zapisz);
			Zapis_txt.add(info);
			Zapis_txt.add(nazwa);
				          
			Zapis_txt.setSize(300,300);
			Zapis_txt.setLayout(null); 
			Zapis_txt.setVisible(false);
		}

	    private void addListeners() {
	        // request focus for key listeners
	        requestFocusInWindow();
	        kartka.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent e) {
	                if (e.getComponent() == kartka && e.getButton() == MouseEvent.BUTTON1) {
	                	Point pocz = zaokr¹glanie_do_64(e.getPoint());
	                	if(e.getClickCount() == 2)
	                	{
	                		przeciaganie = false;
	                		if(pocz.x != 0 && pocz.y != 0 && pocz.x != 1024 && pocz.y != 1024) {
	                			System.out.println(pocz);
	                		int sprawdz = g.czy_wierzcho³ek_istnieje(pocz.x, pocz.y);
	                		if(sprawdz == -1)
	                			g.wstawWierzcho³ek(pocz.x, pocz.y);
	                		else g.usuñWierzcho³ek(sprawdz);
	                		kartka.aktualizuj_obraz();
	                		}
	                	}else {
	                		przeciaganie = true;
	                		start = pocz;
	                	}
	                }
	            }

	            @Override
	            public void mouseReleased(MouseEvent e) {
	                if(e.getComponent() == kartka && e.getButton() == MouseEvent.BUTTON1) {     
	                   Point kon = zaokr¹glanie_do_64(e.getPoint());
	                   if(przeciaganie) {
	                	  koniec = kon;
	                   decyduj();
	                   }
	                }
	            }

				private void decyduj() {
					int name_p = g.czy_wierzcho³ek_istnieje(start.x, start.y);
					int name_k = g.czy_wierzcho³ek_istnieje(koniec.x, koniec.y);
					if(name_p != -1)
					{
						if(name_k == -1)
							g.zmienWierzcho³ek(name_p, koniec.x, koniec.y);
						else {
							if(g.czy_krawêdŸ_istnieje(name_p, name_k))
								g.usuñKrawêdŸ(name_p, name_k);
							else g.wstawKrawêdŸ(name_p, name_k);
						}
					}
					kartka.aktualizuj_obraz();
				} 
	        });

	    }
}
