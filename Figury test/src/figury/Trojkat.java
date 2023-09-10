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
		throw new IllegalArgumentException("Punkty maj� byc r�ne");
		
		double a = Punkt.odleglosc(A, B);
		double b = Punkt.odleglosc(B, C);
		double c = Punkt.odleglosc(A, C);
		
		if(!(a < b + c
		&& b < c + a
		&& c < a + b))
		throw new IllegalArgumentException("To nie jest tr�jk�t");	
		
		this.A = A;
		this.B = B;
		this.C = C;
	}
	
	public void przesu�(Wektor v)
	{
		A.przesu�(v);
		B.przesu�(v);
		C.przesu�(v);
	}
	
	public void obr��(Punkt p, double k�t)
	{
		A.obr��(p, k�t);
		B.obr��(p, k�t);
		C.obr��(p, k�t);
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
