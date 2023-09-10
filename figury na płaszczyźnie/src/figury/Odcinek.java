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
	
	public void przesu�(Wektor v)
	{
		A.przesu�(v);
		B.przesu�(v);
	}
	
	public void obr��(Punkt p, double k�t)
	{
		A.obr��(p, k�t);
		B.obr��(p, k�t);
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
