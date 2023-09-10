package zad10Mikolaj;


public class mikolaj implements Runnable{
	private boolean stopWork;
	private Plansza plansza;
	private Stan_gry stan;
	private char ruch = ' ';
	private int x;
	private int y;
	private int n;
	private int m;
	private boolean czy_zyje;
	
	public mikolaj(Stan_gry s, Plansza p, int szerokosc, int wysokosc) {
		stan = s;
		n = szerokosc;
		m = wysokosc;
		plansza = p;

	}
	
	public void zmien_ruch(char k)
	{
		ruch = k;
	}
	
	public void rusz_sie(char k)
	{
		x = Math.floorMod(x, m);
		y = Math.floorMod(y, n);
		plansza.set_pole(x, y, pola.puste);
		
		switch(k)
		{
		case 'W':
			if(sprawdz_czy_puste(x - 1,y    ))
				if(plansza.get_pole(Math.floorMod(x-2, m), Math.floorMod(y, n)) == pola.dziecko_sen)
					plansza.set_pole(Math.floorMod(x-1, m), Math.floorMod(y, n) ,pola.prezent);
				else x--;
			break;
		case 'A':
			if(sprawdz_czy_puste(x    ,y - 1))
				if(plansza.get_pole(Math.floorMod(x, m),Math.floorMod(y-2, n)) == pola.dziecko_sen)
					plansza.set_pole(Math.floorMod(x, m), Math.floorMod(y-1, n) ,pola.prezent);
				else y--;
			break;
		case 'S':
			if(sprawdz_czy_puste(x + 1,y    )) 
				if(plansza.get_pole(Math.floorMod(x+2, m), Math.floorMod(y, n)) == pola.dziecko_sen)
					plansza.set_pole(Math.floorMod(x+1, m), Math.floorMod(y, n) ,pola.prezent);
				else x++;
			break;
		case 'D':
			if(sprawdz_czy_puste(x    ,y + 1)) 
				if(plansza.get_pole(Math.floorMod(x, m), Math.floorMod(y+2, n)) == pola.dziecko_sen)
					plansza.set_pole(Math.floorMod(x, m), Math.floorMod(y+1, n),pola.prezent);
				else y++;
			break;
		}
		x = Math.floorMod(x, m);
		y = Math.floorMod(y, n);
		plansza.set_pole(x, y, pola.mikolaj);	
		ruch = ' ';
		
	}
	
	public boolean sprawdz_czy_puste(int i, int j)
	{
		
		return plansza.get_pole(Math.floorMod(i,m),Math.floorMod(j,n)) == pola.puste 
			|| plansza.get_pole(Math.floorMod(i,m),Math.floorMod(j,n)) == pola.snieg;
		
	}
	
	public boolean sprawdz_czy_dziecko_obok()
	{
		
		return plansza.get_pole(Math.floorMod(x-1,m),Math.floorMod(y,n)) == pola.dziecko
			|| plansza.get_pole(Math.floorMod(x+1,m),Math.floorMod(y,n)) == pola.dziecko
			|| plansza.get_pole(Math.floorMod(x,m),Math.floorMod(y-1,n)) == pola.dziecko
			|| plansza.get_pole(Math.floorMod(x,m),Math.floorMod(y+1,n)) == pola.dziecko;
		
	}
	
	@Override
	public void run() {
		stopWork = false;
		czy_zyje = true;
		ruch = ' ';
	
		System.out.println(stan.get_stan());
		while(!stopWork && czy_zyje && stan.get_stan() == stany_gry.w_trakcie_gry)
		{
			if(ruch != ' ') {
			synchronized(plansza){
			rusz_sie(ruch);
			}
			}
			synchronized(plansza){
			if(sprawdz_czy_dziecko_obok())czy_zyje = false;
			}
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
	
	public boolean czy_zyje_gracz()
	{
		return czy_zyje;
	}
	
	public void stop() {
	        stopWork = true;
	}
}


