package obliczenia;



public class Wymierna implements Comparable<Wymierna> {
	private int licznik, mianownik = 1;
	public Wymierna()
	{
		licznik = 0;
	}
	
	private static int euklides(int a, int b)
    {
        if (a == 0)
            return b;
          
        return euklides(b%a, a);
    }
	
	public Wymierna(int k, int m) throws ZeroException
	{
		licznik = k;
		mianownik = m;
		
		if(mianownik == 0) throw new ZeroException();
		
		if(mianownik < 0) {
			licznik = -licznik;
			mianownik = -mianownik;
		}
		
		int nwd = euklides(licznik, mianownik);
		
		licznik /= nwd;
		mianownik /= nwd;
		
	}
	//konstruktor delegatowy
	public Wymierna(int n) 
	{
		this(n,1);
	}
	
	//gettery
	public int get_licznik()
	{
		return licznik;
	}
	
	public int get_mianownik()
	{
		return mianownik;
	}
	
	@Override
	public String toString()
	{
		return licznik + "/" + mianownik;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Wymierna other = (Wymierna) obj;
    
        if(this.licznik == other.get_licznik()
        		&& this.mianownik == other.get_mianownik())
        return true;
        
        return false;
    }
	public double wartosc()
	{
		return (double)licznik / (double) mianownik;
	}
	
	@Override
	public int compareTo(Wymierna p)
	{
		double w1 = this.licznik / this.mianownik;
		double w2 = p.get_licznik() / p.get_mianownik();
		return (w1 > w2) ? 1 : ((w1 < w2) ? -1 : 0); 
	}
	
	public static Wymierna dodaj(Wymierna a, Wymierna b)
	{
		int l = a.get_licznik()*b.get_mianownik() + b.get_licznik()*a.get_mianownik();
		int m = a.get_mianownik() * b.get_mianownik();
		return new Wymierna(l, m);
	}
	
	public static Wymierna odejmij(Wymierna a, Wymierna b)
	{
		int l = a.get_licznik()*b.get_mianownik() - b.get_licznik()*a.get_mianownik();
		int m = a.get_mianownik() * b.get_mianownik();
		return new Wymierna(l, m);
	}
	
	public static Wymierna pomnoz(Wymierna a, Wymierna b)
	{
		int l = a.get_licznik() * b.get_licznik();
		int m = a.get_mianownik() * b.get_mianownik();
		return new Wymierna(l, m);
	}
	
	public static Wymierna dziel(Wymierna a, Wymierna b) throws ArithmeticException
	{
		if(b.compareTo(new Wymierna()) == 0)throw new ArithmeticException();
		int l = a.get_licznik() * b.get_mianownik();
		int m = a.get_mianownik() * b.get_licznik();
		return new Wymierna(l, m);
	}
	
}
