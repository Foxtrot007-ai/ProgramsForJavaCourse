package zad11graf;

import java.io.Serializable;

class Wierzcho쿮k implements Serializable, Comparable<Wierzcho쿮k> {
	 private int nr, x, y;
	 
	 public Wierzcho쿮k(int n, int x, int y)
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
	 
	 public int compareTo(Wierzcho쿮k w) 
	 {
		 return nr < w.get_name() ? -1 : nr > w.get_name() ? 1 : 0;
	 }
}