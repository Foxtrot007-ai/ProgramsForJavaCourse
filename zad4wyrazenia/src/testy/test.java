package testy;
import struktury.*;
import obliczenia.*;
public class test {
	public static void main (String[] args)
    {
		//para i kolejka
		try {
			Para p1 = new Para("okno",12);
			Para p2 = (Para)p1.clone();
			p2.set_wartoœæ(13);
			System.out.println(p1.toString());
			System.out.println(p2.toString());
		}catch(CloneNotSupportedException c) {}
		
		ZbiorListowy lista = new ZbiorListowy();
		lista.wstaw(new Para("okno",1));
		lista.wstaw(new Para("okno",3));
		lista.wstaw(new Para("klucz",1));
		lista.wstaw(new Para("kluczyk",5));
		lista.usuñ("klucz");
		System.out.println(lista.toString());
		System.out.println(lista.szukaj("okno"));
		
		//wyrazenia test
		
		Zmienna.set("x", 2);
		Zmienna.set("y", 7);
		
		Wyrazenie w[] = {
		//3 + 5
		new Dodawanie(
					new Liczba(3),
					new Liczba(5)),
		//-(2 - x) * y
		new Mnozenie(
					new ZmianaZnaku(
							new Odejmowanie(
									new Liczba(2),
									new Zmienna("x"))),
					new Zmienna("y")),
		//min((x + 13) * x, (1 - x) mod 2)
		new Minimum(
					new Mnozenie(
								new Dodawanie(
											 new Zmienna("x"), 
											 new Liczba(13)), 
								new Zmienna("x")),
					new Modulo(
							new Odejmowanie(
									new Liczba(1),
									new Zmienna("x")),
							new Liczba(2))),
		//(3 * 11 - 1) / (y + 5)
		new Dzielenie(
				new Odejmowanie(
						new Mnozenie(
								new Liczba(3), 
								new Liczba(11)),
						new Liczba(1)),
				new Dodawanie(
						new Zmienna("y"),
						new Liczba(5))),
		//2^5 + x * log(2,y) < 20
		new Mniejsze(
				new Dodawanie(
						new Potegowanie(
								new Liczba(2), 
								new Liczba(5)),
						new Mnozenie(
								new Zmienna("x"), 
								new LogDyskretny(
										new Liczba(2), 
										new Zmienna("y")))),
				new Liczba(20))
		};
		
		for(int i = 0; i < 5; i++)
		{
			System.out.println(w[i].toString() + " = " + w[i].oblicz());
		}
	}
}
