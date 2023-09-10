package struktury;

public class Para implements Cloneable, Comparable<Para>{
	public final String klucz;
	private int warto��;
	
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
		warto�� = w;
	}
	
	public String get_klucz()
	{
		return klucz;
	}
	
	public int get_warto��()
	{
		return warto��;
	}
	
	public void set_warto��(int w)
	{
		warto�� = w;
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
		return "|" + klucz + ", " + warto�� + "|";
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	
	public int compareTo(Para p)
	{
		return (warto�� > p.get_warto��()) ? 1 : ((warto�� < p.get_warto��()) ? -1 : 0); 
	}

}
