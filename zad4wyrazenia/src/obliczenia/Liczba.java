package obliczenia;

import struktury.Para;

public class Liczba extends Wyrazenie{
	
	private int wartoœæ;
	public Liczba(int w)
	{
		wartoœæ = w;
	}
	
	public int oblicz()
	{
		return wartoœæ;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Liczba other = (Liczba) obj;
    
        if(this.oblicz() == other.oblicz())
        return true;
        
        return false;
    }
	
	public String toString()
	{
		return Integer.toString(wartoœæ);
	}
}
