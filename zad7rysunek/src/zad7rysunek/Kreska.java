package zad7rysunek;

import java.awt.Color;
import java.awt.Point;

public class Kreska {
	 protected Point pocz¹tek, koniec;
	 protected Color kolor;
	 public Kreska(Point pocz, Point kon, Color kol) { 
		 pocz¹tek = pocz;
		 koniec = kon;
		 kolor = kol;
	 }
	 
	 public Point get_pocz¹tek()
	 {
		 return pocz¹tek;
	 }
	 
	 public Point get_koniec()
	 {
		 return koniec;
	 }
	 
	 public Color get_kolor()
	 {
		 return kolor;
	 }
	 
	 
	}
