package zad10Mikolaj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Timer;

public class gra {
	private Plansza plansza;
	private	int szerokosc;
	private int wysokosc;
	private mikolaj gracz;
	private dziecko[] dzieci;
	private int ilosc_dzieci = 20;
	private Stan_gry stan;
	
	public void ruch_graczem(char key)
	{
		gracz.zmien_ruch(key);
	}
	
	public gra(int wysokosc_planszy, int szerokosc_planszy, Stan_gry s) {
		szerokosc = szerokosc_planszy;
		wysokosc = wysokosc_planszy;
		plansza = new Plansza(wysokosc,szerokosc);
		stan = s;
		gracz = new mikolaj(stan, plansza,szerokosc,wysokosc);
		
		dzieci = new dziecko[ilosc_dzieci];
		for(int i = 0; i < ilosc_dzieci; i++)
		{
			dzieci[i] = new dziecko(plansza,szerokosc,wysokosc,stan);
		}
		
		 Timer timer = new Timer(15000, new ActionListener() {
		        public void actionPerformed(ActionEvent evt) {
		        	synchronized(plansza) {
		        	for(int i = 0; i < wysokosc; i++)
		        		for(int j = 0; j < szerokosc; j++)
		        			if(plansza.get_pole(i, j) == pola.puste)
		        				plansza.set_pole(i, j, pola.snieg);
		        	}
		        }    
		 });
		 timer.start();
		 
		 
	}

	public void restart() {
		//stopujemy watki
		gracz.stop();
		for(int i = 0; i < ilosc_dzieci; i++)
		dzieci[i].stop();
		//startujemy je spowrotem
		plansza.reset();
		//losowanie x
		List<Integer> numbers1 = new ArrayList<Integer>(szerokosc); 
		for (int i = 0; i < szerokosc; i+=5) 
		  numbers1.add(i); 
		Collections.shuffle(numbers1); 
		
		//losowanie y
		List<Integer> numbers2 = new ArrayList<Integer>(wysokosc); 
		for (int i = 0; i < wysokosc; i+=5) 
		  numbers2.add(i); 
		Collections.shuffle(numbers2); 
		
		gracz.ustaw_miejsce(numbers2.get(0), numbers1.get(0));
		plansza.set_pole(gracz.get_x(),gracz.get_y(),pola.mikolaj);
		
		for(int i = 0; i < ilosc_dzieci; i++)
		{
			dzieci[i].ustaw_miejsce(numbers2.get(i+1), numbers1.get(i+1));
			//dzieci[i].ustaw_miejsce(gracz.get_x() + 4, gracz.get_y() + 4);
		}
		
		
		
		Thread t0 = new Thread(gracz);
		t0.start();
	
		Thread[] td = new Thread[ilosc_dzieci];
		
		/*Thread t1 = new Thread(dzieci[0]);
		t1.start();*/
		
		
		for(int i = 0; i < ilosc_dzieci; i++)
		{
			td[i] = new Thread(dzieci[i]);
		}
		
		for(int i = 0; i < ilosc_dzieci; i++)
		{
			td[i].start();
		}
		
	}

	public int czy_zwyciestwo() {
		if(!gracz.czy_zyje_gracz()) return 2;
			for(int i = 0; i < ilosc_dzieci; i++) {
				if(!dzieci[i].czy_ma_prezent()) return 0;
			}
		return 1;
	}



	public Plansza get_plansza() {
		synchronized(plansza) {
		Plansza temp = new Plansza(12,14);
		for(int i = 0; i < 12; i++)
			for(int j = 0; j < 14; j++) 
				temp.set_pole(i, j,  plansza.get_pole(Math.floorMod(gracz.get_x() - 5 + i, wysokosc), Math.floorMod(gracz.get_y() - 6 + j, szerokosc)));
		return temp;
		}
	}

}
