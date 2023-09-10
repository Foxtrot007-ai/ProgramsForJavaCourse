package zad12lambdy;

public class Trojkat implements Comparable<Trojkat> {
	double A;
	double B;
	double C;
	public Trojkat(double a, double b, double c)
	{
		if(!(a < b + c
		&& b < c + a
		&& c < a + b))
		throw new IllegalArgumentException("To nie jest trójk¹t" + a +"/"+ b + "/" + c+ "\n");	
		
		A = a;
		B = b;
		C = c;
	}
	public double obwod()
	{
		return A+B+C;
	}
	
	public boolean czy_rownoboczny()
	{
		return (A == B) && (B == C);
	}
	
	public boolean czy_prostokatny()
	{
		double a = A*A;
		double b = B*B;
		double c = C*C;
		return (a == b + c) ? true : (b == a + c) ? true : (c == b + a) ? true : false;  
	}
	public int compareTo(Trojkat t)
	{
		
		 return (this.obwod() < t.obwod()) ? -1 : (this.obwod() == t.obwod()) ? 0 : 1;
	}
	public String toString()
	{
		return "[" + A + ", " + B + ", " + C + "]";
	}
}
