package obliczenia;

public class Dzielenie extends Operator2Arg{

	public Dzielenie(Wyrazenie a, Wyrazenie b) {
		super(a, b);
	}
	
	public int oblicz()
	{
		if(w2.oblicz() == 0) throw new IllegalArgumentException("Dzielenie przez 0");
		return w1.oblicz() / w2.oblicz();
	}
	
	public String toString()
	{
		return "(" + w1.toString() + " / " + w2.toString() + ")";
	}
	
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dzielenie other = (Dzielenie) obj;
    
        if(this.oblicz() == other.oblicz())
        return true;
        
        return false;
    }
	
}