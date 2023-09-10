package zad8labirynt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class gamemaster {

	private gra g;
	private long startTime;
	private long endTime;
	private int szerokosc_planszy;
	private int wysokosc_planszy;
	private Image postac;
	private Color kolor_scian = Color.black;
	private Color kolor_tla = Color.white;
	private stan_gry stan;
	private int ilosc_ruchow;
	private boolean znaczki;
	
	private JTextField info;
	
	private JPanel ekran;
	private JFrame zmien_kolor_tla;
	private JFrame zmien_kolor_scian;
	private JFrame zmien_szerokosc;
	private JFrame zmien_wysokosc;
	
	private JFrame frame;
	private JMenuBar menu;
	private JMenu Gra;
	private JMenu Ustawienia;
	private JMenu Ruchy;
	private JMenu Pomoc;
	
	private JMenuItem O_aplikacji;
	private JMenuItem O_autorze;
	
	private JMenu Kolory;
	private JMenuItem Tlo;
	private JMenuItem Sciana;
	private JMenu Rozmiary;
	private JMenuItem Szerokosc;
	private JMenuItem Wysokosc;
	
	private JMenuItem Start;
	private JMenuItem Rezygnacja;
	private JMenuItem Koniec;
	private JMenuItem Oznaczanie_pol;
	
	private JMenuItem góra;
	private JMenuItem dó³;
	private JMenuItem lewo;
	private JMenuItem prawo;
	
	private void sprawdz_czy_wygral()
	{
		if(g.czy_zwyciestwo()) {
			stan = stan_gry.koniec_z_sukcesem;
			endTime   = System.nanoTime();
			long totalTime = endTime - startTime;
			info.setText("Wygrales, ilosc ruchow: " + ilosc_ruchow + " w " + (double) totalTime / 1_000_000_000 + "s");
		}
	}
	
	private Action startAction = new AbstractAction("Start") {
		 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	ilosc_ruchow = 0;
	    	startTime = System.nanoTime();
	        stan = stan_gry.w_trakcie_gry;
	        info.setText("W trakcie gry");
	        g.zmien_wielkosc(szerokosc_planszy, wysokosc_planszy);
	        g.restart();
	        ekran.repaint();
	    }
	};
	
	private Action rezyAction = new AbstractAction("Rezygnacja") {
		 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        stan = stan_gry.koniec_z_pora¿k¹;
	        info.setText("Pora¿ka");
	    }
	};
	
	private Action endAction = new AbstractAction("Koniec") {
		 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	frame.dispose();
	    }
	};
	
	private Action ruch_lewo = new AbstractAction("Lewo") {
		 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	
	    	if(stan == stan_gry.w_trakcie_gry)
	    	{
	    		g.ruch_gracza(side.left);
	    		ilosc_ruchow++;
	    	}
	    	sprawdz_czy_wygral();
	    	ekran.repaint();
	    	
	    }
	};
	
	private Action ruch_prawo = new AbstractAction("Prawo") {
		 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	if(stan == stan_gry.w_trakcie_gry) {
	    	g.ruch_gracza(side.right);
	    	ilosc_ruchow++;
    	}
	    	sprawdz_czy_wygral();
	    	ekran.repaint();
	    }
	};
	
	private Action ruch_gora = new AbstractAction("Góra") {
		 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	if(stan == stan_gry.w_trakcie_gry){
		    	g.ruch_gracza(side.up);
		    	ilosc_ruchow++;
	    	}
	    	sprawdz_czy_wygral();
	    	ekran.repaint();
	    }
	};
	
	private Action ruch_dol = new AbstractAction("Dó³") {
		 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	if(stan == stan_gry.w_trakcie_gry){
		    	g.ruch_gracza(side.down);
		    	ilosc_ruchow++;
	    	}
	    	sprawdz_czy_wygral();
	    	ekran.repaint();
	    }
	};
	
	private Action menu_szerokosc = new AbstractAction("Szerokoœæ") {
		 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	zmien_szerokosc.setVisible(true);
	    }
	};
	
	private Action menu_wysokosc = new AbstractAction("Wysokoœæ") {
		 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	zmien_wysokosc.setVisible(true);
	    }
	};
	
	private Action menu_tlo = new AbstractAction("T³o") {
		 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	zmien_kolor_tla.setVisible(true);
	    }
	};
	
	private Action menu_sciany = new AbstractAction("Œciany") {
		 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	zmien_kolor_scian.setVisible(true);
	    }
	};
	
	private Action o_aplikacji = new AbstractAction("O aplikacji") {
		 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	JOptionPane.showMessageDialog(frame, "Sterowanie strza³kami, cel gry to dojscie do czerwonego punktu Powodzenia");
	    }
	};
	
	private Action o_autorze = new AbstractAction("O autorze") {
		 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	JOptionPane.showMessageDialog(frame, "Dominik Baziuk, Zad 8 labirynt");
	    }
	};
	
	private Action oznacz = new AbstractAction("Oznaczanie_pol") {
		 
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	znaczki = !znaczki;
	    	ekran.repaint();
	    }
	};
	public void ustaw_szerokosc()
	{
		zmien_szerokosc = new JFrame();
		JButton b3;
		JButton b4;
		JLabel info3;
		JTextField wybor2;
	          
					info3 = new JLabel();  
					info3.setBounds(50,50, 300,20);  
					info3.setText("Mo¿liwe wielkosci od 4 do 24");
					
				    
				    wybor2 = new JTextField();  
				    wybor2.setBounds(100,100, 100,20);  
				    wybor2.setEditable(true);
				    
					b3 = new JButton("Anuluj");
					b3.setBounds(50,150,100, 40);
					b3.setEnabled(true);
					b3.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt) {
								
							zmien_szerokosc.setVisible(false);
									
						}
					});
					
					b4 = new JButton("Zmieñ");
					b4.setBounds(150,150,100, 40);
					b4.setEnabled(true);
					b4.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt) {
								
							int temp = Integer.parseInt(wybor2.getText());
							System.out.print(temp);
							if(temp > 3 && temp < 25 )
								szerokosc_planszy = temp;
							
							zmien_szerokosc.setVisible(false);
						
							startAction.actionPerformed(null);
									
						}
					});
					
				
					
					zmien_szerokosc.add(b3);
					zmien_szerokosc.add(b4);
					zmien_szerokosc.add(info3);
					zmien_szerokosc.add(wybor2);
			          
					zmien_szerokosc.setSize(300,300);
					zmien_szerokosc.setLayout(null); 
					zmien_szerokosc.setVisible(false);

	

	}
	
	public void ustaw_wysokosc()
	{
		zmien_wysokosc = new JFrame();
		JButton b3;
		JButton b4;
		JLabel info3;
		JTextField wybor2;
	          
					info3 = new JLabel();  
					info3.setBounds(50,50, 300,20);  
					info3.setText("Mo¿liwe wielkosci od 4 do 24");
					
				    
				    wybor2 = new JTextField();  
				    wybor2.setBounds(100,100, 100,20);  
				    wybor2.setEditable(true);
				    
					b3 = new JButton("Anuluj");
					b3.setBounds(50,150,100, 40);
					b3.setEnabled(true);
					b3.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt) {
								
							zmien_wysokosc.setVisible(false);
									
						}
					});
					
					b4 = new JButton("Zmieñ");
					b4.setBounds(150,150,100, 40);
					b4.setEnabled(true);
					b4.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt) {
								
							int temp = Integer.parseInt(wybor2.getText());
							System.out.print(temp);
							if(temp > 3 && temp < 25 )
								wysokosc_planszy = temp;
							
							zmien_wysokosc.setVisible(false);
						
							startAction.actionPerformed(null);
									
						}
					});
					
				
					
					zmien_wysokosc.add(b3);
					zmien_wysokosc.add(b4);
					zmien_wysokosc.add(info3);
					zmien_wysokosc.add(wybor2);
			          
					zmien_wysokosc.setSize(300,300);
					zmien_wysokosc.setLayout(null); 
					zmien_wysokosc.setVisible(false);

	

	}
	public void ustaw_sciany()
	{
		zmien_kolor_scian = new JFrame();
		JButton b3;
		JButton b4;
		JLabel info3;
		JTextField wybor2;
	          
					info3 = new JLabel();  
					info3.setBounds(50,50, 300,20);  
					info3.setText("Mo¿liwe kolory to blue, black, green");
					
				    
				    wybor2 = new JTextField();  
				    wybor2.setBounds(100,100, 100,20);  
				    wybor2.setEditable(true);
				    
					b3 = new JButton("Anuluj");
					b3.setBounds(50,150,100, 40);
					b3.setEnabled(true);
					b3.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt) {
								
							zmien_kolor_scian.setVisible(false);
									
						}
					});
					
					b4 = new JButton("Zmieñ");
					b4.setBounds(150,150,100, 40);
					b4.setEnabled(true);
					b4.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt) {
								
							String temp = wybor2.getText();
							System.out.print(temp);
							if(temp.compareTo("blue") == 0)
								kolor_scian = Color.BLUE;
							else if(temp.compareTo("black") == 0)
								kolor_scian = Color.black;
							else if(temp.compareTo("green") == 0)
								kolor_scian = Color.green;
							
							zmien_kolor_scian.setVisible(false);
							ekran.repaint();
									
						}
					});
					
				
					
					zmien_kolor_scian.add(b3);
					zmien_kolor_scian.add(b4);
					zmien_kolor_scian.add(info3);
					zmien_kolor_scian.add(wybor2);
			          
					zmien_kolor_scian.setSize(300,300);
					zmien_kolor_scian.setLayout(null); 
					zmien_kolor_scian.setVisible(false);

	

	}
	
	public void ustaw_tlo()
	{
		zmien_kolor_tla = new JFrame();
		JButton b1;
		JButton b2;
		JLabel info2;
		JTextField wybor;
	          
		info2 = new JLabel();  
		info2.setBounds(50,50, 300,20);  
		info2.setText("Mo¿liwe kolory to blue, white, green");
					
				    
		wybor = new JTextField();  
		wybor.setBounds(100,100, 100,20);  
		wybor.setEditable(true);
				    
		b1 = new JButton("Anuluj");
		b1.setBounds(50,150,100, 40);
		b1.setEnabled(true);
		b1.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt) {
								
							zmien_kolor_tla.setVisible(false);
									
						}
					});
					
					b2 = new JButton("Zmieñ");
					b2.setBounds(150,150,100, 40);
					b2.setEnabled(true);
					b2.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt) {
								
							String temp = wybor.getText();
							System.out.print(temp);
							if(temp.compareTo("blue") == 0)
								kolor_tla = Color.BLUE;
							else if(temp.compareTo("white") == 0)
								kolor_tla = Color.white;
							else if(temp.compareTo("green") == 0)
								kolor_tla = Color.green;
							
							zmien_kolor_tla.setVisible(false);
							ekran.repaint();
									
						}
					});
					
				
					
					zmien_kolor_tla.add(b1);
					zmien_kolor_tla.add(b2);
					zmien_kolor_tla.add(info2);
					zmien_kolor_tla.add(wybor);
			          
					zmien_kolor_tla.setSize(300,300);
					zmien_kolor_tla.setLayout(null); 
					zmien_kolor_tla.setVisible(false);
					
		
	}
	
	public gamemaster()
	{
		g = new gra();
		wysokosc_planszy = 24;
		szerokosc_planszy = 24;
		g.zmien_wielkosc(24,24);
		
		
		ImageIcon image = new ImageIcon("C:\\Users\\kaziu\\eclipse-workspace\\zad8labirynt\\src\\zad8labirynt\\postac.jpg");
		postac = image.getImage();
		stan = stan_gry.startowy;
		
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		menu = new JMenuBar();
		Gra = new JMenu("Gra");    
		Ustawienia = new JMenu("Ustawienia");    
		Ruchy = new JMenu("Ruchy");     
		Pomoc = new JMenu("Pomoc");
		Kolory = new JMenu("Kolory");     
		Tlo = new JMenuItem("Tlo");
		Sciana = new JMenuItem("Sciana");     
		Rozmiary = new JMenu("Rozmiary");
		Wysokosc = new JMenuItem("Wysokosc");     
		Szerokosc = new JMenuItem("Szerokosc");
		Start = new JMenuItem("Start");
		Rezygnacja = new JMenuItem("Rezygnacja");
		Koniec = new JMenuItem("Koniec");
		Oznaczanie_pol = new JMenuItem("Oznaczanie_pol");
		góra = new JMenuItem("góra");
		dó³ = new JMenuItem("dó³");
		lewo = new JMenuItem("lewo");
		prawo = new JMenuItem("prawo");
		O_aplikacji = new JMenuItem("O aplikacji");
		O_autorze = new JMenuItem("O autorze");
		info = new JTextField("Startujemy?");
		
		
		//zmien_kolor_tla
		ustaw_tlo();
		
		//zmien_kolor_scian
		ustaw_sciany();
		
		
		//ustaw_szerokosc
		ustaw_szerokosc();
		
		//ustaw_wysokosc
		ustaw_wysokosc();
		ekran = new JPanel() {
	
	        @Override
	        public void paintComponent(Graphics gr) {
	      
	            super.paintComponent(gr);
	            if(stan != stan_gry.startowy) {
	            droga[][] temp = g.plansza(); 
	            int width = frame.getWidth()/(szerokosc_planszy+4);
	            int height = frame.getHeight()/(wysokosc_planszy+5);
	            
	            int x = 0;
	            int y = 0;
	            String alfabet = "abcdefghijklmnoprstuvwxyz";
	            gr.clearRect(0, 0, this.getWidth() , this.getHeight());
	            gr.setColor(kolor_tla);
	            gr.fillRect(0, 0, this.getWidth(), this.getHeight());
	            
	            for(int i = 0; i < wysokosc_planszy+4; i++)
	            {
	            	
	            	
	            	for(int j = 0; j < szerokosc_planszy+4; j++)
		            {
	            		if(i >= 2 && j >= 2 && j <= szerokosc_planszy+2 && i <= wysokosc_planszy+2 && znaczki) {
	            		gr.setColor(Color.BLUE);
	            		String s = alfabet.charAt(i-2) + "" + (j-2);
	            		gr.drawString(s, x, y -1 + height);
	            		}
	            		if(i == wysokosc_planszy+1 && j == szerokosc_planszy+1)
	            		{
	            			gr.setColor(Color.red);
		            		gr.fillRect(x, y, width, height);
	            		}
	            		
		            	if(temp[j][i] == droga.wall) {
		            		gr.setColor(kolor_scian);
		            		gr.fillRect(x, y, width, height);
		            	}else if(temp[j][i] == droga.gracz)
		            	{
		            		gr.drawImage(postac, x, y, width,height,null);
		            	}
		            	x += width;
		            }
	            	x = 0;
	            	y += height;
	            } 
	        }
	        }
	    };
		
	
		//menu
		menu.add(Gra);
		menu.add(Ustawienia);
		menu.add(Ruchy);
		menu.add(Box.createHorizontalGlue());
		menu.add(Pomoc);
		
		
		
		//start menu
		startAction.putValue(Action.ACCELERATOR_KEY,
		        KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		 
		Start.setAction(startAction);
		Gra.add(Start);
		
		
		rezyAction.putValue(Action.ACCELERATOR_KEY,
		        KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
		 
		Rezygnacja.setAction(rezyAction);
		Gra.add(Rezygnacja);
		
		endAction.putValue(Action.ACCELERATOR_KEY,
		        KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_DOWN_MASK));
		 
		Koniec.setAction(endAction);
		Gra.add(Koniec);
	
		
		//ustawienia menu
		Szerokosc.setAction(menu_szerokosc);
		Wysokosc.setAction(menu_wysokosc);
		Rozmiary.add(Szerokosc);
		Rozmiary.add(Wysokosc);
		
		Tlo.setAction(menu_tlo);
		Sciana.setAction(menu_sciany);
		Kolory.add(Tlo);
		Kolory.add(Sciana);
		Oznaczanie_pol.setAction(oznacz);
		
		Ustawienia.add(Oznaczanie_pol);
		Ustawienia.add(Kolory);
		Ustawienia.add(Rozmiary);
		
		
		
		//ruchy menu
		ruch_lewo.putValue(Action.ACCELERATOR_KEY,
		        KeyStroke.getKeyStroke(KeyEvent.VK_A, 0));
		lewo.setAction(ruch_lewo);
		Ruchy.add(lewo);
		
		ruch_prawo.putValue(Action.ACCELERATOR_KEY,
		        KeyStroke.getKeyStroke(KeyEvent.VK_D, 0));
		prawo.setAction(ruch_prawo);
		Ruchy.add(prawo);
		
		ruch_gora.putValue(Action.ACCELERATOR_KEY,
		        KeyStroke.getKeyStroke(KeyEvent.VK_W, 0));
		góra.setAction(ruch_gora);
		Ruchy.add(góra);
		
		ruch_dol.putValue(Action.ACCELERATOR_KEY,
		        KeyStroke.getKeyStroke(KeyEvent.VK_S, 0));
		dó³.setAction(ruch_dol);
		Ruchy.add(dó³);
		
		
		//Pomoc menu
		O_aplikacji.setAction(o_aplikacji);
		O_autorze.setAction(o_autorze);
		
		Pomoc.add(O_aplikacji);
		Pomoc.add(O_autorze);
		
		//Jpanel

		
		ekran.setVisible(true);
		
		frame.add(ekran,BorderLayout.CENTER);
		
		ekran.requestFocus();
		//info
		info.setHorizontalAlignment(JTextField.CENTER);
		info.setEditable(false);
		frame.add(info, BorderLayout.SOUTH);
		
		
		
		frame.setJMenuBar(menu);
		frame.setMinimumSize(new Dimension(700, 700));
		frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.pack();
        frame.setVisible(true);  
        frame.repaint();
	}


	
	
}
