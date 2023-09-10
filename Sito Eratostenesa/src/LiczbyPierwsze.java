
import java.util.ArrayList;


public final class LiczbyPierwsze {
	
 private final static int POTEGA2 = 21;
 private final static int[] SITO = new int[1 << POTEGA2];
 private final static int LIMIT = 1 << 21;
 // potrzebny jest statyczny blok inicjalizacyjny dla sita
 // [0, 1, 2, 3, 2, 5, 2, 7, 2, 3, 2, 11, … ]
 
 private LiczbyPierwsze()
 {
	 
 }
 
 static
 {
	 SITO[1] = 1;
     for (int i = 2; i < LIMIT; i++)
         for (int j = i; j < LIMIT; j += i)
             if (SITO[j] == 0)
                 SITO[j] = i;
 }
 public static boolean czyPierwsza (long x) {
	 if (x < 2) return false;
     if (x < LIMIT) return (SITO[(int) x] == x);
     else {
    	 if(x % 2 == 0) return false; 
    	 for (long i = 3; i <= (long) Math.sqrt(x); i += 2) {
             if(x % i == 0) return false;
         }
     }
     return true;
	 
 }
 
 private static long[] get_long_tab(ArrayList<Long> tab)
 {
	 long[] temp = new long[tab.size()];
	 for(int i = 0 ; i < tab.size(); i++)
	 {
		 temp[i] = tab.get(i);
	 }
	 return temp;
 }
 public static long[] naCzynnikiPierwsze (long x) {
	 ArrayList<Long> czynniki = new ArrayList<>();
	 
	 
	 if(x == -1 || x == 0 || x == 1)
	 {
		 czynniki.add(x);
		 return get_long_tab(czynniki);
	 }
	
	 if(x < 0)
	 {
		 czynniki.add(-1L);
		 x *= -1;
	 }
	 
	 if(czyPierwsza(x))
	 {
		 czynniki.add(x);
		 return get_long_tab(czynniki);
	 }
	 
	 
	 if (x < LIMIT) {
         while (x > 1) {
        	 czynniki.add(new Long(SITO[(int) x]));      
             x /= SITO[(int) x];
         }
     } else {
         for (long i = 3; x > 1 && i <= (long) Math.sqrt(x) + 1; i += 2) {
             while ((i < LIMIT && SITO[(int) i] == i && x % i == 0) 
            		 || (i > LIMIT && x % i == 0)){
            	 czynniki.add(i);
                 x /= i;
             }
         }
     }
	 
     if (x > 1)
     {
    	 czynniki.add(x);
     }
         
     return get_long_tab(czynniki);
 }
	 
	 
 }
 

