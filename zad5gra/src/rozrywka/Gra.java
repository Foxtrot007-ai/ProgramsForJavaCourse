package rozrywka;

import obliczenia.Wymierna;
import obliczenia.Z�y_zakres;

public class Gra {
	private int zakres;
	private Wymierna liczba;
	private int maksIlo��Pr�b;
	private int licznikPr�b;
	private boolean odgadni�to;
	
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
		return licznikPr�b <= maksIlo��Pr�b;
	}
	public boolean czy_odgadl()
	{
		return odgadni�to;
	}
	public void start(int z) {
		 if (z < 4) throw new Z�y_zakres(z);
		 zakres = z;
		 int licz = (int) (Math.random() * zakres) + 1; // (zakres - 1)
		 int mian = (int) (Math.random() * zakres) + 1; // (Math.random() * (zakres - licznik)) + 1 + licznik
		 liczba = new Wymierna(licz, mian);
		 // inicjalizacja: maksIlo��Pr�b, licznikPr�b, odgadni�to
		 maksIlo��Pr�b = (int) Math.ceil(3 * Math.log(z));
		 licznikPr�b = 1;
		 odgadni�to = false;
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
				System.out.println("Zgad�e� gratuluje!");
				odgadni�to = true;
				czy_wystartowano = false;
			break;
			case 1:
				System.out.println("Podana liczba jest wi�ksza od zgadywanej");
			break;
			default:
			break;
			
		}
		licznikPr�b++;
		if(!licznik_prob())czy_wystartowano = false;
	}
}
