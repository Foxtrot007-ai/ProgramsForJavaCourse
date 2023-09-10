package zad12lambdy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;

public class Zad1 {

	private String regex2 = "(\\s*(([1-9][0-9]{1,9})|[0-9])\\s*(//(.*))?)|(\\s*(//(.*))?)";
	private ArrayList<Integer> array = new ArrayList<Integer>();
	
	private String get_string(String ln) {
		String temp = "";
		Boolean number_found = false;
		
		char[] chars = ln.toCharArray();
		
		for(char ch : chars)
		{
			if(ch <= '9' && ch >= '0')
				number_found = true;
			
			if(number_found && (ch > '9' || ch < '0'))
				break;
			
			if(number_found) temp += ch;
		}
		return temp;
	}
	
	private boolean isPrime(int n)
	{
		if(n<=2) return false;
		
		if(n == 2) return true;
		
		if(n % 2 == 0) return false;
		
		for(int i = 3; i*i <= n; i++)
			if(n % i == 0) return false;
		
		return true;
	}
	
	public Zad1()
	{
		//wczytywanie
		Pattern pattern = Pattern.compile(regex2);
		try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\kaziu\\eclipse-workspace\\zad12lambdy\\src\\zad12lambdy\\dane1.txt"))){
			for(String ln = br.readLine(); ln != null; ln = br.readLine())
			{
				if(!pattern.matcher(ln).matches()){
					
					throw new Exception("Z³y format linii");
				}else {
					String temp = get_string(ln);			
					if(temp.compareTo("") != 0)
					array.add(Integer.valueOf(temp));
				}
			}
		}
		catch(Exception ex) {System.out.print(ex.getMessage());};
		
		//operacje
		
		//sortowanie 
		array.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
		
		//pierwsze
		array.stream().filter(n -> isPrime(n)).forEach(System.out::println);
		
		//mniejsze od 1000
		long suma = array.stream().filter(n -> n < 1000).mapToInt(Integer::intValue).sum();
		System.out.println("Suma: " + suma);
		
		//podzielne przez 13
		long ilosc = array.stream().filter(n -> n % 7 == 0).count();
		System.out.println("ilosc podzielnych przez 7: " + ilosc);
	}

	
}
