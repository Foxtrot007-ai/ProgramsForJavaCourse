package obliczenia;

public class Stala extends Wyrazenie{
	
	private int warto��;
	public Stala(int w)
	{
		warto�� = (w == 0 || w == 1 || w == -1) ? w : 0;
	}
	
	public int oblicz()
	{
		return warto��;
	}

	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Stala other = (Stala) obj;
    
        if(this.oblicz() == other.oblicz())
        return true;
        
        return false;
    }
	public String toString()
	{
		return Integer.toString(warto��);
	}
}
