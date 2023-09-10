package zad11graf;

import java.io.Serializable;

class Wierzcho�ek implements Serializable, Comparable<Wierzcho�ek> {
	 private int nr, x, y;
	 
	 public Wierzcho�ek(int n, int x, int y)
	 {
		 nr = n;
		 this.x = x;
		 this.y = y;
	 }
	 
	 int get_x()
	 {
		 return x;
	 }
	 
	 int get_y()
	 {
		 return y;
	 }
	 
	 int get_name()
	 {
		 return nr;
	 }
	 
	 void set_x(int x)
	 {
		 this.x = x;
	 }
	 
	 void set_y(int y)
	 {
		 this.y = y;
	 }
	 
	 void rename()
	 {
		 nr--;
	 }
	 
	 public int compareTo(Wierzcho�ek w) 
	 {
		 return nr < w.get_name() ? -1 : nr > w.get_name() ? 1 : 0;
	 }
}