import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.*;

import obliczenia.B³edne_dane_uzytkownika;
import obliczenia.B³êdny_input;
import obliczenia.Wymierna;
import rozrywka.Gra;

public class main {
	
	 static {
	        try (FileInputStream fis = new FileInputStream("C:\\Users\\kaziu\\eclipse-workspace\\zad5gra\\logging.properties")) {
	            LogManager.getLogManager().readConfiguration(fis);
	        } catch (IOException ex) {
	            throw new ExceptionInInitializerError(ex);
	        }
	  }
	 
	static final Logger logger = Logger.getLogger(main.class.getName());
	
	public static void main (String[] args) throws SecurityException, IOException
    {
		

	
		Scanner we = new Scanner(System.in);
		Gra g = new Gra();
		int licznik = 0;
		int mianownik = 0;
		long startTime;
		long endTime;
		long totalTime;
		logger.entering(main.class.getName(), "main");
		System.out.println("Podaj swoje imie");
		String imie_gracza = we.next();
		logger.log(Level.INFO, "Imie gracza: " + imie_gracza);
		
		while(true)
		{
			startTime = System.nanoTime();
			g.start(4);
			logger.log(Level.INFO, "Nowa gra");
			System.out.println("Zaczynamy gre, zgadnij liczbe wymierna z zakresu 0, 1");
			System.out.println("Podaj mianownik i licznik, oba z zakresu 1 do 4");
			while(!g.czy_odgadl() && g.licznik_prob())
			{
				try {
				System.out.println("Podaj licznik");
				licznik = we.nextInt();
				System.out.println("Podaj mianownik");
				mianownik = we.nextInt();
				}catch (InputMismatchException e) {
					throw new B³êdny_input();
				}
				if((mianownik < 1 || mianownik > 4) || ( licznik < 0 && licznik > 4))
				throw new B³edne_dane_uzytkownika(licznik, mianownik);
				logger.log(Level.INFO, "Liczba podana przez gracza " + licznik + "/" + mianownik);
				g.zgaduje(new Wymierna(licznik,mianownik));
			}	
			if(!g.licznik_prob()) {
				System.out.println("Przegrales");
				logger.log(Level.INFO, "Przegra³ gracz");
			}else logger.log(Level.INFO, "Wygra³ gracz");
			
			endTime   = System.nanoTime();
			totalTime = endTime - startTime;
			logger.log(Level.INFO, "Runda Trwala: " + totalTime);
		}
    	
    }
}
