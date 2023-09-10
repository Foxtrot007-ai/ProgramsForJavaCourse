import java.util.Scanner;
import java.math.BigInteger;


public class Silnia {
	
	
		public static BigInteger policz_silnie(int n)
		{
			BigInteger wynik = new BigInteger("1");
			for(int i = n; i > 1; i--)
				wynik = wynik.multiply(BigInteger.valueOf(i));
			return wynik;
			
				
		}
	    public static void main (String[] args)
	    {
	    	System.out.println("Podaj liczbê z której policzymy silnie: \n");
	    	
	    	Scanner we = new Scanner(System.in);
	    	
	    	int x = we.nextInt();
	    	
	    	if(x < 0)System.err.println("Twoja liczba jest za ma³a!\n");
	    	else if(x > 100)System.err.println("Twoja liczba jest za du¿a!\n");
	    	else System.out.println(x + "! = " + policz_silnie(x));
	    		
	    }
	}

