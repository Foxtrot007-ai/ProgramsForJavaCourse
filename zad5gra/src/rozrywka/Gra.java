package rozrywka;

import obliczenia.Wymierna;
import obliczenia.Z³y_zakres;

public class Gra {
	private int zakres;
	private Wymierna liczba;
	private int maksIloœæPrób;
	private int licznikPrób;
	private boolean odgadniêto;
	
	private boolean czy_wystartowano;
	
	
	
	public Gra()
	{
		czy_wystartowano = false;
	}
	public boolean czy_wystartowano()
	{
		return czy_wystartowano;
	}
	
	public boolean licznik_prob()
	{
		return licznikPrób <= maksIloœæPrób;
	}
	public boolean czy_odgadl()
	{
		return odgadniêto;
	}
	public void start(int z) {
		 if (z < 4) throw new Z³y_zakres(z);
		 zakres = z;
		 int licz = (int) (Math.random() * zakres) + 1; // (zakres - 1)
		 int mian = (int) (Math.random() * zakres) + 1; // (Math.random() * (zakres - licznik)) + 1 + licznik
		 liczba = new Wymierna(licz, mian);
		 // inicjalizacja: maksIloœæPrób, licznikPrób, odgadniêto
		 maksIloœæPrób = (int) Math.ceil(3 * Math.log(z));
		 licznikPrób = 1;
		 odgadniêto = false;
		 System.out.println(licz);
		 System.out.println(mian);
		 System.out.println(liczba.wartosc());
		 assert liczba.wartosc() > 0 && liczba.wartosc() < 1;
		 czy_wystartowano = true;
		 }
	
	public void zgaduje(Wymierna l)
	{
		assert czy_wystartowano;
		switch (l.compareTo(liczba))
		{
			case -1:
				System.out.println("Podana liczba jest mniejsza od zgadywanej");
			break;
			case 0:
				System.out.println("Zgad³eœ gratuluje!");
				odgadniêto = true;
				czy_wystartowano = false;
			break;
			case 1:
				System.out.println("Podana liczba jest wiêksza od zgadywanej");
			break;
			default:
			break;
			
		}
		licznikPrób++;
		if(!licznik_prob())czy_wystartowano = false;
	}
}
