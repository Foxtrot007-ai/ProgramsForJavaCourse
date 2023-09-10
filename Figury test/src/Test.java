import figury.*;

public class Test
{
    public static void main (String[] args)
    {
        Punkt A = new Punkt(0,3);
        Punkt B = new Punkt(0,4);
        Punkt C = new Punkt(0,5);
        double a = Punkt.odleglosc(A, B);
		double b = Punkt.odleglosc(B, C);
		double c = Punkt.odleglosc(A, C);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
        //Trojkat T = new Trojkat(A,B,C);
		
		
		A = new Punkt(0,0);
	    B = new Punkt(0,4);
	    C = new Punkt(2,2);
		Wektor v = new Wektor(1,2);
		
		Trojkat T = new Trojkat(A,B,C);
		
		T.przesuñ(v);
		
		System.out.println(T.toString());
		
        //y=3x-17
		//y=-2x+33
		//y=13
		//x=10
		Prosta p1 = new Prosta(3,-1,-17);
		Prosta p2 = new Prosta(-2,-1, 33);
		Punkt x = Prosta.punkt_przeciêcia(p1,p2);
		System.out.println(x.toString());
    }
}
