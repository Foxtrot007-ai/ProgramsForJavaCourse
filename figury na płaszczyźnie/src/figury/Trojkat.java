package figury;

public class Trojkat {
	private Punkt A;
	private Punkt B;
	private Punkt C;
	
	private Trojkat()
	{
		
	}
	
	public Trojkat(Punkt A, Punkt B, Punkt C)
	{
		if(A.equals(B)
		|| B.equals(C)
		|| C.equals(A))
		throw new IllegalArgumentException("Punkty maj¹ byc ró¿ne");
		
		double a = Punkt.odleglosc(A, B);
		double b = Punkt.odleglosc(B, C);
		double c = Punkt.odleglosc(A, C);
		
		if(a < b + c
		&& b < c + a
		&& c < a + b)
		throw new IllegalArgumentException("To nie jest trójk¹t");	
		
		this.A = A;
		this.B = B;
		this.C = C;
	}
	
	public void przesuñ(Wektor v)
	{
		A.przesuñ(v);
		B.przesuñ(v);
		C.przesuñ(v);
	}
	
	public void obróæ(Punkt p, double k¹t)
	{
		A.obróæ(p, k¹t);
		B.obróæ(p, k¹t);
		C.obróæ(p, k¹t);
	}
	
	public void odbij(Prosta p)
	{
		A.odbij(p);
		B.odbij(p);
		C.odbij(p);
	}
	
	public String toString () {
        return "|A"+ A.toString() + ", B" + B.toString() + ", C" + C.toString()+"|";
    }
}
