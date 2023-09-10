package figury;

public class Punkt {
	private double x;
	private double y;
	
	private Punkt()
	{
		
	}
	
	public Punkt(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	
	public double get_x()
	{
		return x;
	}
	
	public double get_y()
	{
		return y;
	}
	
	public void set_x(double x)
	{
		this.x = x;
	}
	
	public void set_y(double y)
	{
		this.y = y;
	}
	
	public void przesuñ(Wektor v)
	{
		x += v.dx;
		y += v.dy;
	}
	
	public void obróæ(Punkt p, double k¹t)
	{
		double radians = Math.toRadians(k¹t);
		x = p.x * Math.cos(radians) - p.y * Math.sin(radians);
		y = p.x * Math.sin(radians) + p.y * Math.cos(radians);
	}
	
	public void odbij(Prosta p)
	{
		double a = -p.a / p.b;
		double b = -p.c / p.b;
		
		double a2 = Math.pow(a, 2);
		double w = ((1 - a2) * x + 2 * a * y - 2 * a * b)/ (1 + a2);
		double u = ((a2 - 1) * y + 2 * a * x + 2 * a * b)/ (1 + a2);
		
		x = w;
		y = u;
	}
	
	public static double odleglosc(Punkt A, Punkt B)
	{
		return Math.sqrt(
				Math.pow(A.get_x() - B.get_x(), 2) 
			  + Math.pow(A.get_y() - B.get_y(), 2));
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Punkt other = (Punkt) obj;
    
        if(this.x == other.get_x() 
        	&& this.y == other.get_y())
        return true;
        
        return false;
    }
	public String toString () {
        return "("+ x + ", " + y+")";
    }
}
