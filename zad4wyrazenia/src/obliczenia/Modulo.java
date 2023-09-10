
package obliczenia;

public class Modulo extends Operator2Arg{

	public Modulo(Wyrazenie a, Wyrazenie b) {
		super(a, b);
	}
	
	public int oblicz()
	{
		return w1.oblicz() % w2.oblicz();
	}
	
	public String toString()
	{
		return "(" + w1.toString() + " % " + w2.toString() + ")";
	}
	
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Modulo other = (Modulo) obj;
    
        if(this.oblicz() == other.oblicz())
        return true;
        
        return false;
    }
	
}
