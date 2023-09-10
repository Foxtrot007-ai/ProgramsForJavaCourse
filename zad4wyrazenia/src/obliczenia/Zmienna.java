package obliczenia;
import struktury.*;
public class Zmienna extends Wyrazenie{
	private static final ZbiorListowy zlist = new ZbiorListowy();
	private final String id;
	
	private Zmienna()
	{
		id = null;
	}
	
	public Zmienna(String id)
	{
		Para temp = new Para(id,0);
		this.id = id;
	}
	
	public int oblicz()
	{
		Para temp =	zlist.szukaj(id);
		if(temp == null)
			throw new IllegalArgumentException("Nie ma takiej zmiennej w liscie");
		return temp.get_wartoœæ();
	}
	
	public static void set(String k, int w)
	{
		zlist.wstaw(new Para(k,w));
	}
	
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Zmienna other = (Zmienna) obj;
    
        if(this.oblicz() == other.oblicz())
        return true;
        
        return false;
    }
	
	public String toString()
	{
		return id;
	}
	
}
