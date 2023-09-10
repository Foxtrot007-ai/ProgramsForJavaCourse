package obliczenia;

public class LogDyskretny extends Operator2Arg{

	public LogDyskretny(Wyrazenie a, Wyrazenie b) {
		super(a, b);
	}
	
	public int oblicz()
	{
		int a = w1.oblicz();
		int b = w2.oblicz();
		return (int) (Math.log(b) / Math.log(a));
	}
	
	public String toString()
	{
		return "Log(" + w1.toString() + ", " + w2.toString() + ")";
	}
	
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LogDyskretny other = (LogDyskretny) obj;
    
        if(this.oblicz() == other.oblicz())
        return true;
        
        return false;
    }
	
}
