package zad11graf;

import java.io.Serializable;

class Kraw�d� implements Serializable, Comparable<Kraw�d�> {
	 Wierzcho�ek pocz, kon;
	 public Kraw�d�(Wierzcho�ek p,Wierzcho�ek k)
	 {
		 pocz = p;
		 kon = k;
	 }
	 
	 Wierzcho�ek get_poczatek()
	 {
		 return pocz;
	 }
	 
	 Wierzcho�ek get_koniec()
	 {
		 return kon;
	 }
	 
	 void set_poczatek(Wierzcho�ek w)
	 {
		 pocz = w;
	 }
	 
	 void set_koniec(Wierzcho�ek w)
	 {
		 kon = w;
	 }
	 
	 void rename_Kraw�d�(Wierzcho�ek w)
	 {
		 if(pocz.compareTo(w) == 1)
				pocz.rename();
		 if(kon.compareTo(w) == 1)
				kon.rename();
	 }
	
	 public int compareTo(Kraw�d� k) {
		 switch (pocz.compareTo(k.pocz))
		 {
		 case -1:
			 return -1;
		 case 1:
			 return 1;
		 default: 
			 return kon.compareTo(k.kon);
		 }
	 }
}
