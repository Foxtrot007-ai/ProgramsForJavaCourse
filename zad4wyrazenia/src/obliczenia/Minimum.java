package obliczenia;

public class Minimum extends Operator2Arg{

	public Minimum(Wyrazenie a, Wyrazenie b) {
		super(a, b);
	}
	
	public int oblicz()
	{
		int a = w1.oblicz();
		int b = w2.oblicz();
		return a > b ? b : a;
	}
	
	public String toString()
	{
		return "Min(" + w1.toString() + ", " + w2.toString() + ")";
	}
	
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Minimum other = (Minimum) obj;
    
        if(this.oblicz() == other.oblicz())
        return true;
        
        return false;
    }
	
}
