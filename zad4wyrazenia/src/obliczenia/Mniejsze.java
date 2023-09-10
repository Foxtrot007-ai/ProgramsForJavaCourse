package obliczenia;

public class Mniejsze extends Operator2Arg{

	public Mniejsze(Wyrazenie a, Wyrazenie b) {
		super(a, b);
	}
	
	public int oblicz()
	{
		int a = w1.oblicz();
		int b = w2.oblicz();
		return a < b ? 1 : 0;
	}
	
	public String toString()
	{
		return "(" + w1.toString() + " < " + w2.toString() + ")";
	}
	
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mniejsze other = (Mniejsze) obj;
    
        if(this.oblicz() == other.oblicz())
        return true;
        
        return false;
    }
	
}

