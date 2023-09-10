package struktury;

public interface Zbior {
	Para szukaj (String k);
	// metoda ma wyszukaæ parê z zadanym kluczem;
	//metoda zwraca null, gdy nie znajdzie pary o podanym kluczu;
	void wstaw (Para p);
	//metoda ma wstawiæ do zbioru now¹ parê; gdy para o
	//podanym kluczu ju¿ jest w zbiorze, metoda dokonuje aktualizacji wartoœci w
	//znalezionej parze;
	void usuñ(String k);
	// metoda ma usun¹æ ze zbioru parê o zadanym kluczu; gdy
	//pary o podanym kluczu nie ma w zbiorze metoda nic nie robi;
	void czysc();
	//metoda ma usun¹æ wszystkie pary ze zbioru; po tej operacji zbiór
	//staje siê pusty;
	int ile();
	//metoda ma podaæ ile jest wszystkich par w zbiorze.
}
