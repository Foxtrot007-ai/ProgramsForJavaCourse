package zad11graf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Graf implements Serializable {
	 private List<Wierzcho�ek> wierzch = new ArrayList<>();
	 private Set<Kraw�d�> kraw = new TreeSet<>();
	 
	 public void zmienWierzcho�ek(int n,int x, int y)
	 {
		 for(Wierzcho�ek w : wierzch)
		 if(w.get_name() == n)
		 {
			 w.set_x(x);
			 w.set_y(y);
			 return;
		 }
	 }
	 public boolean czy_krawedz_podwojna(Kraw�d� k1)
	 {
		 int pocz_n = k1.get_poczatek().get_name();
		 int kon_n = k1.get_koniec().get_name();
		 for(Kraw�d� k2 : kraw)
			 if(k2.get_poczatek().get_name() == kon_n 
			 &&  k2.get_koniec().get_name() == pocz_n)
				 return true;
		 return false;
	 }
	 public void wstawWierzcho�ek(int x, int y) 
	 {
		 if(czy_wierzcho�ek_istnieje(x,y) == -1)
			 wierzch.add(new Wierzcho�ek(wierzch.size(),x,y));
	 }
	 
	 public void usu�Wierzcho�ek(int n)
	 {
		 if(czy_wierzcho�ek_istnieje(n))
		 {
			//usuwamy wierzcholek
			Wierzcho�ek temp = znajdz_wierzcho�ek(n);
			
			//usuwamy krawedzie
			Set<Kraw�d�> temp_set = new TreeSet<>();
			
			//szukamy krawedzi
			for(Kraw�d� k : kraw)
				if(k.get_poczatek().compareTo(temp) == 0 
				|| k.get_koniec().compareTo(temp) == 0)
					temp_set.add(k);
			//usuwamy je
			for(Kraw�d� k : temp_set)
				kraw.remove(k);
			/*//zmieniamy nazwy krawedzi
			for(Kraw�d� k : kraw) {
				k.rename_Kraw�d�(temp);
			}	*/	
			wierzch.remove(temp);
			//zmieniamy nazwy wierzcholkow
			for(Wierzcho�ek w : wierzch)
			{
				if(w.compareTo(temp) == 1)
				w.rename();
			}
		 }
	 }
	 
	 public void wstawKraw�d�(int u, int v) 
	 {
		 if(czy_wierzcho�ek_istnieje(u) 
		 && czy_wierzcho�ek_istnieje(v))
		 kraw.add(new Kraw�d�(znajdz_wierzcho�ek(u),znajdz_wierzcho�ek(v)));
	 }
	 
	 public void usu�Kraw�d�(int u, int v)
	 {
		 if(czy_wierzcho�ek_istnieje(u) 
		 && czy_wierzcho�ek_istnieje(v))
		 kraw.remove(new Kraw�d�(znajdz_wierzcho�ek(u),znajdz_wierzcho�ek(v)));
	 }
	
	 public boolean czy_wierzcho�ek_istnieje(int n)
	 {
		 for(Wierzcho�ek w : wierzch)
			 if(w.get_name() == n)
				 return true;
		 return false;
	 }
	 
	 public boolean czy_kraw�d�_istnieje(int name_p, int name_k)
	 {
		 for(Kraw�d� k : kraw)
			 if(k.get_poczatek().get_name() == name_p 
			 && k.get_koniec().get_name() == name_k)
				 return true;
		 return false;
	 }
	 
	 
	 public int czy_wierzcho�ek_istnieje(int x, int y)
	 {
		 for(Wierzcho�ek w : wierzch)
			 if(w.get_x() == x && w.get_y() == y)
				 return w.get_name();
		 return -1;
	 }
	  
	 public Wierzcho�ek znajdz_wierzcho�ek(int n)
	 {
		 for(Wierzcho�ek w : wierzch)
			 if(w.get_name() == n)
				 return w;
		 return null;
	 }
	 
	 public String toString()
	 {
		 String temp = Integer.toString(wierzch.size()) + "\n";
		 for(Wierzcho�ek w : wierzch)
		 {
			 for(Kraw�d� k : kraw)
				 if(k.get_poczatek().compareTo(w) == 0)
					 temp += k.get_koniec().get_name();
			 temp += "\n";
		 }
		 return temp;	 
	 }
	 
	 public List<Wierzcho�ek> get_list()
	 {
		 return wierzch;
	 }
	 
	 public Set<Kraw�d�> get_set()
	 {
		 return kraw;
	 }
}
