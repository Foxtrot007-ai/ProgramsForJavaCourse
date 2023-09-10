package zad10Mikolaj;

public class Plansza {
	private pola[][] plansza;
	private int wysokosc;
	private int szerokosc;
	public Plansza(int w, int s)
	{
		wysokosc = w;
		szerokosc = s;
		plansza = new pola[wysokosc][szerokosc];
		reset();
	}
	public void reset()
	{
		for(int i = 0; i < wysokosc; i++)
			for(int j = 0; j < szerokosc; j++) 
				plansza[i][j] = pola.snieg;
		
	}
	
	public pola get_pole(int i, int j)
	{
		return plansza[i][j];
	}
	
	public void set_pole(int i, int j, pola p)
	{
		plansza[i][j] = p;
	}
	
	public void set_pole(int i, int j, pola p, String kto)
	{
		plansza[i][j] = p;
		System.out.println("zmieniono plansze: " + kto);
	}
}
