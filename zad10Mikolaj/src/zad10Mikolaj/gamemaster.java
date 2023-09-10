package zad10Mikolaj;

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
	private Plansza plansza;

	private final int szerokosc_planszy = 200;
	private final int wysokosc_planszy = 200;
	
	private Image mikolaj_img;
	private Image dziecko_img;
	private Image tlo_img;
	private Image dziecko_prezent_img;
	private Image dziecko_œpi_img;
	private Image prezent_img; 
	
	
	private Stan_gry stan;
	
	private JButton kontroler;
	
	private JTextField info;
	
	private JPanel ekran;
	
	private JFrame frame;

	
	public gamemaster()
	{
		stan = new Stan_gry();
		g = new gra(wysokosc_planszy, szerokosc_planszy, stan);

		ImageIcon image1 = new ImageIcon("C:\\Users\\kaziu\\eclipse-workspace\\zad10Mikolaj\\src\\zad10Mikolaj\\mikolaj.jpg");
		mikolaj_img = image1.getImage();
		
		ImageIcon image2 = new ImageIcon("C:\\Users\\kaziu\\eclipse-workspace\\zad10Mikolaj\\src\\zad10Mikolaj\\dziecko.jpg");
		dziecko_img = image2.getImage();
		
		ImageIcon image3 = new ImageIcon("C:\\Users\\kaziu\\eclipse-workspace\\zad10Mikolaj\\src\\zad10Mikolaj\\snieg.jpg");
		tlo_img = image3.getImage();
		
		ImageIcon image4 = new ImageIcon("C:\\Users\\kaziu\\eclipse-workspace\\zad10Mikolaj\\src\\zad10Mikolaj\\dziecko_prezent.jpg");
		dziecko_prezent_img = image4.getImage();
		
		ImageIcon image5 = new ImageIcon("C:\\Users\\kaziu\\eclipse-workspace\\zad10Mikolaj\\src\\zad10Mikolaj\\dziecko_œpi.jpg");
		dziecko_œpi_img = image5.getImage();
		
		ImageIcon image6 = new ImageIcon("C:\\Users\\kaziu\\eclipse-workspace\\zad10Mikolaj\\src\\zad10Mikolaj\\prezent.jpg");
		prezent_img = image6.getImage();
		
		
		
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		
		info = new JTextField("Startujemy?");
		

		ekran = new JPanel() {
	
	        @Override
	        public void paintComponent(Graphics gr) {
	            super.paintComponent(gr);
	            if(stan.get_stan() != stany_gry.startowy) {
	            	
	            int szerokosc_widoku = 14;
		        int wysokosc_widoku = 12;
	            int width = frame.getWidth()/szerokosc_widoku;
	            int height = frame.getHeight()/wysokosc_widoku;
	          
	            int x = 0;
	            int y = 0;
	            
	            gr.clearRect(0, 0, this.getWidth() , this.getHeight());
	   
	            plansza = g.get_plansza();
	            for(int i = 0; i < wysokosc_widoku; i++)
	            {
	            	for(int j = 0; j < szerokosc_widoku; j++)
		            {	
	            		if(plansza.get_pole(i, j) == pola.snieg)
	            			gr.drawImage(tlo_img, x, y, width,height,null);
	            		else if(plansza.get_pole(i, j)  == pola.mikolaj)
		            		gr.drawImage(mikolaj_img, x, y, width,height,null);
		            	else if(plansza.get_pole(i, j)  == pola.dziecko)
		            		gr.drawImage(dziecko_img, x, y, width,height,null);
		            	else if(plansza.get_pole(i, j)  == pola.dziecko_obdarowane)
		            		gr.drawImage(dziecko_prezent_img, x, y, width,height,null);
		            	else if(plansza.get_pole(i, j)  == pola.dziecko_sen)
		            		gr.drawImage(dziecko_œpi_img, x, y, width,height,null);
		            	else if(plansza.get_pole(i, j)  == pola.prezent)
		            		gr.drawImage(prezent_img, x, y, width,height,null);
		            
		            	x += width;
		            }
	            	x = 0;
	            	y += height;
	            } 
	        }
	        }
	    };
	    
	    info.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                    	g.ruch_graczem('W');
                        break;
                    case KeyEvent.VK_A:
                    	g.ruch_graczem('A');
                    	break;
                    case KeyEvent.VK_S:
                    	g.ruch_graczem('S');
                        break;
                    case KeyEvent.VK_D:
                    	g.ruch_graczem('D');
                        break;
                }
            }
        });
	 
	
		
		//Button
	    kontroler = new JButton();
	    kontroler.setText("Start");
	    kontroler.setEnabled(true);
	    kontroler.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt) {
					
					stan.ustaw_stan(stany_gry.w_trakcie_gry);
					g.restart();
					kontroler.setEnabled(false);			
			}
			
		});
	    frame.add(kontroler, BorderLayout.NORTH);
	  
	    //timer
	    Timer timer = new Timer(40, new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	        	ekran.repaint();
	        	switch(stan.get_stan())
	        	{
	        	case w_trakcie_gry:
	        		
	        		if(g.czy_zwyciestwo() == 0) {
	        			info.setText("W trakcie");
	        		}else if(g.czy_zwyciestwo() == 1) {
	        			info.setText("Wygra³eœ");
	        			stan.ustaw_stan(stany_gry.startowy);
		        		kontroler.setEnabled(true); 
	        		}else {
	        			info.setText("Przegra³eœ");
	        			stan.ustaw_stan(stany_gry.startowy);
		        		kontroler.setEnabled(true); 
	        		}    
	        		break;
	        	case startowy:
	        		info.setText("Powodzenia");
	        		break;
	        	default:
	        		 		
	        		break;
	        	}
	        	
	        }    
	    });
	    
	   
	    timer.start();

		//Jpanel
		ekran.setVisible(true);
		
		frame.add(ekran,BorderLayout.CENTER);
			
		//info
		info.setHorizontalAlignment(JTextField.CENTER);
		info.setEditable(false);
		frame.add(info, BorderLayout.SOUTH);
		
		frame.setMinimumSize(new Dimension(700, 700));
		frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.pack();
        frame.setVisible(true);  
        frame.setFocusable(true);
        frame.repaint();
        
	}


	
	
}
