
import java.util.Scanner;



public class main {
	
	
		
	    public static void main (String[] args)
	    {
	    	if (args.length == 0) {
	            System.err.println("Nie Podano argumentów");
	        }else
	        {
	        	 for (String arg : args) {
	        		 long x = Long.valueOf(arg);
	        		 long[] czynniki = LiczbyPierwsze.naCzynnikiPierwsze(x);
	        		 
	        		 System.out.print(x + " = ");
	        		 for(int i = 0; i < czynniki.length; i++) {
	        			 System.out.print(czynniki[i]);
	                 if (i + 1 < czynniki.length)
	                     System.out.print("*");
	        		 }
	        		 System.out.print("\n");
	        	 }
	        }
	       
	    	
	    }
	}


