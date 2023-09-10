package zad12lambdy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Zad2{

	private String regex1 = "\\s*(([1-9][0-9]+)|[0-9])(.[0-9]+)?(\\s+(([1-9][0-9]+)|[0-9])(.[0-9]+)?){2}\\s*(//(.*))?";
	private String regex2 =  "\\s*(//(.*))?";
	private LinkedList<Trojkat> array = new LinkedList<Trojkat>();
	
	private Trojkat get_trojkat(String ln) {
		int number = 0;
		boolean number_found = false;
		String[] temp = new String[3];
		
		temp[0] = "";
		temp[1] = "";
		temp[2] = "";
		for(int i = 0; i < ln.length(); i++)
		{
			char ch = ln.charAt(i);
			if(ch <= '9' && ch >= '0')
				number_found = true;
			
			if(number_found && (ch > '9' || ch < '0') && ch != '.')
			{
				number++;
				number_found = false;
			}
			
			if(number == 3) break;
			
			if(number_found) temp[number] += String.valueOf(ch);
		}
		
		return new Trojkat(Double.parseDouble(temp[0]),Double.parseDouble(temp[1]),Double.parseDouble(temp[2]));
	}
	
	public Zad2()
	{
		//wczytywanie
		Pattern pattern1 = Pattern.compile(regex1);
		Pattern pattern_pusta_linia = Pattern.compile(regex2);
		try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\kaziu\\eclipse-workspace\\zad12lambdy\\src\\zad12lambdy\\dane2.txt"))){
			for(String ln = br.readLine(); ln != null; ln = br.readLine())
			{
				if(!pattern1.matcher(ln).matches() && !pattern_pusta_linia.matcher(ln).matches())
					throw new Exception("Z³y format linii");
				else {
					if(!pattern_pusta_linia.matcher(ln).matches()) {
						array.add(get_trojkat(ln));
					}
				}
			}
		}
		catch(Exception ex) {System.out.print(ex.getMessage());};
		
		//operacje
		
		//sortowanie 
		array.stream().sorted((a,b)-> a.compareTo(b)).forEach(System.out::println);
		
		//prostokatne
		array.stream().filter(n -> n.czy_prostokatny()).forEach(System.out::println);
		
		//ile rownobocznych
		long ilosc = array.stream().filter(n -> n.czy_rownoboczny()).count();
		System.out.println("ilosc rownobocznych: " + ilosc);
		
		//max i min
		Trojkat T_max = array.stream().max((a,b)-> a.compareTo(b)).orElse(null);
		Trojkat T_min = array.stream().min((a,b)-> a.compareTo(b)).orElse(null);
		System.out.println("min: " + T_min);
		System.out.println("max: " + T_max);
	}

	
}
