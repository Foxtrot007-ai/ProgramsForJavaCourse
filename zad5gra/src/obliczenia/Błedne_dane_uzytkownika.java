package obliczenia;



public class B³edne_dane_uzytkownika extends IllegalArgumentException {
	public B³edne_dane_uzytkownika(int licznik, int mianownik)
	{
		super("Bledne dane: licznik: " + licznik + " mianownik: "+ mianownik);
	}

}
