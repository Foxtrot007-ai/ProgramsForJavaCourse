package obliczenia;

public class Odejmowanie extends Operator2Arg{

	public Odejmowanie(Wyrazenie a, Wyrazenie b) {
		super(a, b);
	}
	
	public int oblicz()
	{
		return w1.oblicz() - w2.oblicz();
	}
	
	public String toString()
	{
		return "(" + w1.toString() + " - " + w2.toString() + ")";
	}
	
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Odejmowanie other = (Odejmowanie) obj;
    
        if(this.oblicz() == other.oblicz())
        return true;
        
        return false;
    }
	
}
