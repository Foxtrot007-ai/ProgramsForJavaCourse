package zad11graf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Graf implements Serializable {
	 private List<Wierzcho³ek> wierzch = new ArrayList<>();
	 private Set<KrawêdŸ> kraw = new TreeSet<>();
	 
	 public void zmienWierzcho³ek(int n,int x, int y)
	 {
		 for(Wierzcho³ek w : wierzch)
		 if(w.get_name() == n)
		 {
			 w.set_x(x);
			 w.set_y(y);
			 return;
		 }
	 }
	 public boolean czy_krawedz_podwojna(KrawêdŸ k1)
	 {
		 int pocz_n = k1.get_poczatek().get_name();
		 int kon_n = k1.get_koniec().get_name();
		 for(KrawêdŸ k2 : kraw)
			 if(k2.get_poczatek().get_name() == kon_n 
			 &&  k2.get_koniec().get_name() == pocz_n)
				 return true;
		 return false;
	 }
	 public void wstawWierzcho³ek(int x, int y) 
	 {
		 if(czy_wierzcho³ek_istnieje(x,y) == -1)
			 wierzch.add(new Wierzcho³ek(wierzch.size(),x,y));
	 }
	 
	 public void usuñWierzcho³ek(int n)
	 {
		 if(czy_wierzcho³ek_istnieje(n))
		 {
			//usuwamy wierzcholek
			Wierzcho³ek temp = znajdz_wierzcho³ek(n);
			
			//usuwamy krawedzie
			Set<KrawêdŸ> temp_set = new TreeSet<>();
			
			//szukamy krawedzi
			for(KrawêdŸ k : kraw)
				if(k.get_poczatek().compareTo(temp) == 0 
				|| k.get_koniec().compareTo(temp) == 0)
					temp_set.add(k);
			//usuwamy je
			for(KrawêdŸ k : temp_set)
				kraw.remove(k);
			/*//zmieniamy nazwy krawedzi
			for(KrawêdŸ k : kraw) {
				k.rename_KrawêdŸ(temp);
			}	*/	
			wierzch.remove(temp);
			//zmieniamy nazwy wierzcholkow
			for(Wierzcho³ek w : wierzch)
			{
				if(w.compareTo(temp) == 1)
				w.rename();
			}
		 }
	 }
	 
	 public void wstawKrawêdŸ(int u, int v) 
	 {
		 if(czy_wierzcho³ek_istnieje(u) 
		 && czy_wierzcho³ek_istnieje(v))
		 kraw.add(new KrawêdŸ(znajdz_wierzcho³ek(u),znajdz_wierzcho³ek(v)));
	 }
	 
	 public void usuñKrawêdŸ(int u, int v)
	 {
		 if(czy_wierzcho³ek_istnieje(u) 
		 && czy_wierzcho³ek_istnieje(v))
		 kraw.remove(new KrawêdŸ(znajdz_wierzcho³ek(u),znajdz_wierzcho³ek(v)));
	 }
	
	 public boolean czy_wierzcho³ek_istnieje(int n)
	 {
		 for(Wierzcho³ek w : wierzch)
			 if(w.get_name() == n)
				 return true;
		 return false;
	 }
	 
	 public boolean czy_krawêdŸ_istnieje(int name_p, int name_k)
	 {
		 for(KrawêdŸ k : kraw)
			 if(k.get_poczatek().get_name() == name_p 
			 && k.get_koniec().get_name() == name_k)
				 return true;
		 return false;
	 }
	 
	 
	 public int czy_wierzcho³ek_istnieje(int x, int y)
	 {
		 for(Wierzcho³ek w : wierzch)
			 if(w.get_x() == x && w.get_y() == y)
				 return w.get_name();
		 return -1;
	 }
	  
	 public Wierzcho³ek znajdz_wierzcho³ek(int n)
	 {
		 for(Wierzcho³ek w : wierzch)
			 if(w.get_name() == n)
				 return w;
		 return null;
	 }
	 
	 public String toString()
	 {
		 String temp = Integer.toString(wierzch.size()) + "\n";
		 for(Wierzcho³ek w : wierzch)
		 {
			 for(KrawêdŸ k : kraw)
				 if(k.get_poczatek().compareTo(w) == 0)
					 temp += k.get_koniec().get_name();
			 temp += "\n";
		 }
		 return temp;	 
	 }
	 
	 public List<Wierzcho³ek> get_list()
	 {
		 return wierzch;
	 }
	 
	 public Set<KrawêdŸ> get_set()
	 {
		 return kraw;
	 }
}
