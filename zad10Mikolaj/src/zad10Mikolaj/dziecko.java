package zad10Mikolaj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;






public class dziecko implements Runnable{
	
	private Plansza plansza;
	private boolean stopWork;
	private Stan_gry stan;
	private pola stan_d;
	private int x;
	private int y;
	private int n;
	private int m;
	private int mikolaj_x;
	private int mikolaj_y;
	private int ruchy;
	private boolean mikolaj_zobaczony;
	private int paczka_zobaczona;
	private boolean trzymac_sie_drogi;
	private Queue<Pair<Integer,Integer>> droga;
	
	public dziecko(Plansza p, int szerokosc, int wysokosc, Stan_gry s) {
		plansza = p;
		n = szerokosc;
		m = wysokosc; 
		stan = s;
		droga = new LinkedList<>();
	}

	@Override
	public void run() {
		stopWork = false;
		stan_d = pola.dziecko_sen;
		ruchy = 0;
		plansza.set_pole(x,y,pola.dziecko_sen);
		while(!stopWork && stan.get_stan() == stany_gry.w_trakcie_gry && stan_d != pola.dziecko_obdarowane)
		{
			if(stan_d == pola.dziecko_sen) {
				System.out.println("œpie");
				try {
					Thread.sleep(1000*10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				stan_d = pola.dziecko;
				System.out.println("wstaje");
				trzymac_sie_drogi = false;
				droga.clear();
				synchronized(plansza)
				{
					plansza.set_pole(x, y, pola.dziecko,"dziecko");
					paczka_zobaczona = szukaj_paczki();
					System.out.println("szukam paczki: " + paczka_zobaczona);
					
				}
			}else if(stan_d == pola.dziecko)
			{
				if(!trzymac_sie_drogi && paczka_zobaczona == 0)
				{
					synchronized(plansza)
					{
					trzymac_sie_drogi = szukaj_mikolaja();
					oblicz_droge_do_mikolaja();
					System.out.println("szukam mikolaja: " + trzymac_sie_drogi);
					}
				}
				if(ruchy < 7) {
					try {
						Thread.sleep(1000 * 2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized(plansza)
					{
						System.out.println("robie ruch: x = " + x + ", y = " + y);
						zrób_ruch();
						System.out.println("nowa pozycja: x = " + x + ", y = " + y);
					}
				}else {
					synchronized(plansza)
					{
					ruchy = 0;
					stan_d = pola.dziecko_sen;
					
						plansza.set_pole(x, y, pola.dziecko_sen);
					}
				}
			}
		}
	}
	
	private void oblicz_droge_do_mikolaja() {
		x = Math.floorMod(x, m);
		y = Math.floorMod(y, n);
		int temp_x = Math.abs(mikolaj_x - x);
		int temp_y = Math.abs(mikolaj_y - y);
		int znak_x = (mikolaj_x < x) ? -1 : 1;
		int znak_y = (mikolaj_y < y) ? -1 : 1;
		
		droga.clear();
		
		for(int i = 0; i < temp_x ; i++)
			droga.add(new Pair(znak_x,0));
		
		for(int i = 0; i < temp_y ; i++)
			droga.add(new Pair(0,znak_y));
		
	}

	private int szukaj_paczki() {
		if(plansza.get_pole(Math.floorMod(x - 1, m), Math.floorMod(y, n)) == pola.prezent)
			return 1;
		else if(plansza.get_pole(Math.floorMod(x, m), Math.floorMod(y-1, n)) == pola.prezent)
			return 2;
		else if(plansza.get_pole(Math.floorMod(x, m),Math.floorMod(y+1, n)) == pola.prezent)
			return 3;
		else if(plansza.get_pole(Math.floorMod(x + 1, m), Math.floorMod(y, n)) == pola.prezent)
			return 4;
		
		return 0;
		
	}

	private boolean szukaj_mikolaja(){
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 10; j++) 
				if(plansza.get_pole(Math.floorMod(x - 4 + i, m), Math.floorMod(y - 4 + j,n)) == pola.mikolaj) {
					mikolaj_x = Math.floorMod(x - 4 + i, m);
					mikolaj_y =  Math.floorMod(y - 4 + j,n);
					return true;
				}
		return false;
	}
	

	private void zrób_ruch() {
		if(trzymac_sie_drogi) {
			System.out.println("Trzymam sie drogi");
			
			trzymac_sie_drogi = szukaj_mikolaja();
			oblicz_droge_do_mikolaja();
			System.out.println("szukam mikolaja: " + trzymac_sie_drogi);
			
			
			if(plansza.get_pole(Math.floorMod(x + droga.element().get_L(),m), Math.floorMod(y + droga.element().get_R(),n)) != pola.snieg
			&& plansza.get_pole(Math.floorMod(x + droga.element().get_L(),m), Math.floorMod(y + droga.element().get_R(),n)) != pola.puste)
				return;
			
			x = Math.floorMod(x, m);
			y = Math.floorMod(y, n);
			
			plansza.set_pole(x, y, pola.puste);
			x += droga.element().get_L();
			y += droga.element().get_R();
			
			x = Math.floorMod(x, m);
			y = Math.floorMod(y, n);
			plansza.set_pole(x, y, pola.dziecko);
			droga.poll();
			ruchy++;
			if(droga.isEmpty())trzymac_sie_drogi = false;
			
			}else if(paczka_zobaczona != 0) {
				System.out.println("ide po paczke");
				x = Math.floorMod(x, m);
				y = Math.floorMod(y, n);
				plansza.set_pole(x, y, pola.puste);
				switch (paczka_zobaczona)
				{
				case 1:
					x -= 1;
					break;
				case 2:
					y -= 1;
					break;
				case 3:
					y += 1;
					break;
				case 4:
					x += 1;
					break;
				}
				x = Math.floorMod(x, m);
				y = Math.floorMod(y, n);
				plansza.set_pole(x, y, pola.dziecko_obdarowane, "dzieckoobdarzone");
				stan_d = pola.dziecko_obdarowane;
				droga.poll();
				ruchy++;
			}else {
				System.out.println("losowy ruch: x = " + x + ", y = " + y);
				int temp1 = -1 + (int)(Math.random() * 3);
				int temp2;
				
				if(temp1 == 0) {
					temp2 = (int)(Math.random() * 2);
					temp2 = (temp2 == 0) ? -1 : 1;
				}else {
					temp2 = 0;
				}
				System.out.println("losowy ruch: temp1 = " + temp1 + ", temp2 = " + temp2);
				if(plansza.get_pole(Math.floorMod(x + temp1,m), Math.floorMod(y + temp2,n)) != pola.snieg
						&& plansza.get_pole(Math.floorMod(x + temp1,m), Math.floorMod(y + temp2,n)) != pola.puste)
							return;
				
				x = Math.floorMod(x, m);
				y = Math.floorMod(y, n);
				plansza.set_pole(x, y, pola.puste);
				x += temp1;
				y += temp2;
				x = Math.floorMod(x, m);
				y = Math.floorMod(y, n);
				plansza.set_pole(x, y, pola.dziecko, "losowy ruch");
				ruchy++;
				System.out.println("wykonalem losowy ruch");
			}
			
	}
		
	

	public void ustaw_miejsce(int i, int j)
	{
		x = i;
		y = j;
	}
	
	public int get_x() {
		return x;
	}
	
	public int get_y() {
		return y;
	}
	
	public void stop() {
	        stopWork = true;
	}

	public boolean czy_ma_prezent() {
		return stan_d == pola.dziecko_obdarowane;
	}
}
