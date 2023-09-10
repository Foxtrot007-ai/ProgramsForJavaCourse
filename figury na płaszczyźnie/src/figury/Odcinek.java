package figury;

public class Odcinek {
	private Punkt A;
	private Punkt B;
	
	private Odcinek()
	{
		
	}
	
	public Odcinek(Punkt A, Punkt B)
	{
		if(A.equals(B))
		throw new IllegalArgumentException("Dwa te same punkty");
		
		this.A = A;
		this.B = B;
	}
	
	public void przesuñ(Wektor v)
	{
		A.przesuñ(v);
		B.przesuñ(v);
	}
	
	public void obróæ(Punkt p, double k¹t)
	{
		A.obróæ(p, k¹t);
		B.obróæ(p, k¹t);
	}
	
	public void odbij(Prosta p)
	{
		A.odbij(p);
		B.odbij(p);
	}
	
	public String toString () {
        return "|A"+ A.toString() + ", B" + B.toString() + "|";
    }
}
