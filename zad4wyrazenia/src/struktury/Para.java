package struktury;

public class Para implements Cloneable, Comparable<Para>{
	public final String klucz;
	private int wartoœæ;
	
	private Para()
	{
		klucz = ".";
	}
	
	private boolean check_string(String s)
	{
		if(s == null) return false;
		
		String needed = "abcdefghijklmnopqrstuvwxyz";
		for(char a : s.toCharArray())
		{
			if(!needed.contains(a+""))return false;
		}
		return true;
	}
	
	public Para(String k, int w)
	{
		if(!check_string(k)) throw new IllegalArgumentException("Niepoprawny klucz");
		
		klucz = k;
		wartoœæ = w;
	}
	
	public String get_klucz()
	{
		return klucz;
	}
	
	public int get_wartoœæ()
	{
		return wartoœæ;
	}
	
	public void set_wartoœæ(int w)
	{
		wartoœæ = w;
	}
	
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Para other = (Para) obj;
    
        if(klucz.equals(other.get_klucz()))
        return true;
        
        return false;
    }
	public String toString()
	{
		return "|" + klucz + ", " + wartoœæ + "|";
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	
	public int compareTo(Para p)
	{
		return (wartoœæ > p.get_wartoœæ()) ? 1 : ((wartoœæ < p.get_wartoœæ()) ? -1 : 0); 
	}

}
