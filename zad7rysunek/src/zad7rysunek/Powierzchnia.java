package zad7rysunek;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Powierzchnia extends Canvas {
	
		
		private ArrayList<Kreska> rysunek;
		private Boolean czy_rysujemy = false;
		private Point pocz¹tek = null;
		private Point koniec = null;
		
		public void set_bool(Boolean b)
		{
			czy_rysujemy = b;
		}
		
		public void set_poczatek(Point p)
		{
			pocz¹tek = p;
		}
		
		public void set_koniec(Point k)
		{
			koniec = k;
		}
		
		public Powierzchnia(ArrayList<Kreska> r)
		{
			rysunek = r;	
		}
		public void paint(Graphics gr) {
			setBounds(250, 100, 800, 600);
			setBackground(Color.white);
			for(Kreska k : rysunek)
			{
				gr.setColor(k.get_kolor());
				Point begin = k.get_pocz¹tek();
				Point end = k.get_koniec();
				gr.drawLine(begin.x, begin.y, end.x, end.y);
			}
			
			if(czy_rysujemy && pocz¹tek != null && koniec != null)
			{
				gr.setColor(Color.gray);
				gr.drawLine(pocz¹tek.x, pocz¹tek.y, koniec.x, koniec.y);
			}
		}
		 
}


