package struktury;

public interface Zbior {
	Para szukaj (String k);
	// metoda ma wyszuka� par� z zadanym kluczem;
	//metoda zwraca null, gdy nie znajdzie pary o podanym kluczu;
	void wstaw (Para p);
	//metoda ma wstawi� do zbioru now� par�; gdy para o
	//podanym kluczu ju� jest w zbiorze, metoda dokonuje aktualizacji warto�ci w
	//znalezionej parze;
	void usu�(String k);
	// metoda ma usun�� ze zbioru par� o zadanym kluczu; gdy
	//pary o podanym kluczu nie ma w zbiorze metoda nic nie robi;
	void czysc();
	//metoda ma usun�� wszystkie pary ze zbioru; po tej operacji zbi�r
	//staje si� pusty;
	int ile();
	//metoda ma poda� ile jest wszystkich par w zbiorze.
}
