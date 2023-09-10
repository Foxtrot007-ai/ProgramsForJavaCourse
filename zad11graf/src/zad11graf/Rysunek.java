package zad11graf;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Rysunek extends JLabel {
	
		
		private BufferedImage obraz = new BufferedImage(1024,1024,BufferedImage.TYPE_INT_RGB);
		private Graf graf;
		private Stroke base = new BasicStroke(3);
		private Stroke dashed = new BasicStroke(3,BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9,5}, 0);
		public Rysunek(Graf g) {
			setSize(1024, 1024);
			setIcon(new ImageIcon(obraz));
			graf = g;
			
		}
		
		public void aktualizuj_obraz(){
			setSize(1024, 1024);
			Graphics2D g = (Graphics2D)obraz.getGraphics();
			//Stroke defaultStroke = g.getStroke();
			/*if(base == null)
			{
				base = new BasicStroke();
				base = defaultStroke;
			}*/
			g.setColor(Color.white);
			g.fillRect(0, 0, 1024, 1024);
			
			g.setColor(Color.black);
			g.setStroke(base);
			for(Wierzcho³ek w : graf.get_list())
			{
				g.drawOval(w.get_x() -16 , w.get_y() - 16, 32, 32);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
				g.drawString(Integer.toString(w.get_name()), w.get_x() - 4, w.get_y() + 4);
			}
			
			
			for(KrawêdŸ k : graf.get_set())
			{
				if(graf.czy_krawedz_podwojna(k)){
					g.setColor(Color.black);
					g.setStroke(base);
				}else if(k.get_poczatek().compareTo(k.kon) == -1) {
					g.setColor(Color.BLUE);
					g.setStroke(dashed);
				}
				else { 
					g.setColor(Color.RED);
					g.setStroke(dashed);
				}
				int xp = k.get_poczatek().get_x();
				int yp = k.get_poczatek().get_y();
				int xk = k.get_koniec().get_x();
				int yk = k.get_koniec().get_y();
				
				int x1 = xp + ((xp != xk) ? ((xp < xk) ?  16 : -16) : 0);
				int y1 = yp + ((yp != yk) ? ((yp < yk) ?  16 : -16) : 0);
				int x2 = xk + ((xk != xp) ? ((xk < xp) ?  16 : -16) : 0);
				int y2 = yk + ((yk != yp) ? ((yk < yp) ?  16 : -16) : 0);
				
				g.drawLine(x1,y1,x2,y2);
				//g.drawLine(xp,yp,xk,yk);
			}
			
			setIcon(new ImageIcon(obraz));
			repaint();
		}
		 
}


