package obliczenia;

public class ZmianaZnaku extends Operator1Arg{

	
	
	public ZmianaZnaku(Wyrazenie w)
	{
		super(w);
	}
	
	@Override
	public int oblicz() {
		
		return - w1.oblicz();
	}
	
	
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ZmianaZnaku other = (ZmianaZnaku) obj;
    
        if(this.oblicz() == other.oblicz())
        return true;
        
        return false;
    }
	
	
	public String toString()
	{
		return "-" + w1.toString();
	}
	

}
