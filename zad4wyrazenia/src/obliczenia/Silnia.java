package obliczenia;

public class Silnia extends Operator1Arg{

	
	
	
	public Silnia(Wyrazenie w)
	{
		super(w);
	}
	
	@Override
	public int oblicz() {
		
		int n = w1.oblicz();
		int wynik = 1;
		for(int i = n; i > 1; i--)
			wynik *= i;
		
		return wynik;
	}
	
	
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Silnia other = (Silnia) obj;
    
        if(this.oblicz() == other.oblicz())
        return true;
        
        return false;
    }
	
	
	public String toString()
	{
		return "!" + w1.toString();
	}
	

}

