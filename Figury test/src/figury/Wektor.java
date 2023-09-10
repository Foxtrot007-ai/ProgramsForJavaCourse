
package figury;

public class Wektor {
	public final double dx;
	public final double dy;
	
	private Wektor() 
	{
		this.dx = 0;
		this.dy = 0;
	}
	
	public Wektor(double dx, double dy)
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	public static Wektor Z�o�enie_wektor�w(Wektor a, Wektor b)
	{
		return new Wektor(a.dx + b.dx, a.dy + b.dy);
	}
	
	
	public String toString () {
        return "|"+ dx + ", " + dy+"|";
    }
	
}
