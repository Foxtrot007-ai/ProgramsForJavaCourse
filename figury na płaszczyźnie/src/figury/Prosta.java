package figury;

public class Prosta {
	
	public final double a;
	public final double b;
	public final double c;
	
	private Prosta()
	{
		a = 1;
		b = 1;
		c = 1;
	}
	
	Prosta(double A, double B, double C)
	{
		a = A;
		b = B;
		c = C;
	}
	
	public static Prosta przesuñ(Prosta p, Wektor w)
	{
		return new Prosta(p.a, p.b, p.c + w.dy - p.a * w.dx);		
	}
	
	public static boolean czy_równoleg³e(Prosta p1, Prosta p2)
	{
		return (p1.a / -p1.b == p2.a / -p2.b)
				&& (p1.c / -p1.b != p2.c / -p2.b);
	}
	
	public static boolean czy_prostopad³e(Prosta p1, Prosta p2)
	{
		return (p1.a / -p1.b) * (p2.a / -p2.b) == -1;
	}
	
	public static Punkt punkt_przeciêcia(Prosta p1, Prosta p2)
	{
		if(czy_równoleg³e(p1,p2))
		throw new IllegalArgumentException("Proste równoleg³e");
		
		double w = p1.a * p2.b - p2.a * p1.b;
		double wx = (-p1.c) * p2.b - (-p2.c) * p1.b;
		double wy = p1.a * (-p2.c) - p2.a * (-p1.c);

		return new Punkt(wx/w,wy/w);
		
	}
	
	public String toString () {
        return "|"+ a + "x + " + b + "y + "+ c +" = 0|";
    }
}
