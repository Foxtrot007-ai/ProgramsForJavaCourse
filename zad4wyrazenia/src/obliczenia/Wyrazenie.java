package obliczenia;

import struktury.Para;

public abstract class Wyrazenie implements Obliczalny{
	
	public static int sume (Wyrazenie... wyr)
	{
		int s = 0;
		for(Wyrazenie w : wyr)
		{
			if(!(w == null))
			s += w.oblicz();	
		}
		
		return s;
	}
	
	public static int iloczyn (Wyrazenie... wyr)
	{
		int s = 0;
		for(Wyrazenie w : wyr)
		{
			if(!(w == null))
			s *= w.oblicz();	
		}
		
		return s;
	}
	

}
