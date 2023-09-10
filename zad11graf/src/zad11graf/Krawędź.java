package zad11graf;

import java.io.Serializable;

class KrawêdŸ implements Serializable, Comparable<KrawêdŸ> {
	 Wierzcho³ek pocz, kon;
	 public KrawêdŸ(Wierzcho³ek p,Wierzcho³ek k)
	 {
		 pocz = p;
		 kon = k;
	 }
	 
	 Wierzcho³ek get_poczatek()
	 {
		 return pocz;
	 }
	 
	 Wierzcho³ek get_koniec()
	 {
		 return kon;
	 }
	 
	 void set_poczatek(Wierzcho³ek w)
	 {
		 pocz = w;
	 }
	 
	 void set_koniec(Wierzcho³ek w)
	 {
		 kon = w;
	 }
	 
	 void rename_KrawêdŸ(Wierzcho³ek w)
	 {
		 if(pocz.compareTo(w) == 1)
				pocz.rename();
		 if(kon.compareTo(w) == 1)
				kon.rename();
	 }
	
	 public int compareTo(KrawêdŸ k) {
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
