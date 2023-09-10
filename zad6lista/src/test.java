
import java.util.Date;

import structures.*;
public class test {
	 public static void main (String[] args)
	 {
	   
	    
	    /////////////////////////////////////Integer
	    OrderedList<Integer> int_lst = new OrderedList<Integer>();
	    int_lst.insert(1);
	    int_lst.insert(5);
	    int_lst.insert(4);
	    int_lst.insert(6);
	    int_lst.insert(2);
	    
	    System.out.println(int_lst.toString());
	    
	    
	    int_lst.remove(6);
	    
	    System.out.println(int_lst.toString());
	    
	    int_lst.insert(8);
	    int_lst.remove(3);
	    
	    System.out.println(int_lst.toString());
	    
	    System.out.println("max: " + int_lst.max());
	    System.out.println("min: " + int_lst.min());
	    
	    System.out.println("Element at 2: " + int_lst.at(2));
	    
	    System.out.println("5's index is: " + int_lst.index(5));
	    
	    System.out.println("Does 6 exist? " + int_lst.search(6));
	    System.out.println("Does 2 exist? " + int_lst.search(2));
	    
	    System.out.println("size: " + int_lst.size());
	    
	    
	    for(Integer i : int_lst)
	    {
	    	System.out.println(i);
	    }
	    /////////////////////////////String
	    OrderedList<String> string_lst = new OrderedList<String>();
	    string_lst.insert("Java");
	    string_lst.insert("Cplusplus");
	    string_lst.insert("Python");
	    string_lst.insert("Rust");
	    string_lst.insert("C#");
	    
	   System.out.println(string_lst.toString());
	    
	    
	    string_lst.remove("Rust");
	    
	    System.out.println(string_lst.toString());
	    
	    string_lst.insert("Swift");
	    string_lst.remove("Rust");
	    
	    System.out.println(string_lst.toString());
	    
	    System.out.println("max: " + string_lst.max());
	    System.out.println("min: " + string_lst.min());
	    
	    System.out.println("Element at 2: " + string_lst.at(2));
	    
	    System.out.println("Java's index is: " + string_lst.index("Java"));
	    
	    System.out.println("Does C# exist? " + string_lst.search("C#"));
	    System.out.println("Does Rust exist? " + string_lst.search("Rust"));
	    
	    System.out.println("size: " + string_lst.size());
	    
	    
	    for(String i : string_lst)
	    {
	    	System.out.println(i);
	    }
	    ///////////////////////////////////////Date
	    OrderedList<Date> date_lst = new OrderedList<Date>();
	    date_lst.insert(new Date(2001,7,14));
	    date_lst.insert(new Date(2002,4,2));
	    date_lst.insert(new Date(2003,2,25));
	    date_lst.insert(new Date(2004,1,4));
	    date_lst.insert(new Date(2005,1,3));
	    
	    System.out.println(date_lst.toString());
	    
	    
	    date_lst.remove(new Date(2004,1,4));
	    
	    System.out.println(date_lst.toString());
	    
	    date_lst.insert(new Date(2006,1,3));
	    date_lst.remove(new Date(2004,1,4));
	    
	    System.out.println(date_lst.toString());
	    
	    System.out.println("max: " + date_lst.max());
	    System.out.println("min: " + date_lst.min());
	    
	    System.out.println("Element at 2: " + date_lst.at(2));
	    
	    System.out.println("5's index is: " + date_lst.index(new Date(2001,7,14)));
	    
	    System.out.println("Does 2002,4,2 exist? " + date_lst.search(new Date(2002,4,2)));
	    System.out.println("Does 2004,1,4 exist? " + date_lst.search(new Date(2004,1,4)));
	    
	    System.out.println("size: " + date_lst.size());
	    
	    for(Date i : date_lst)
	    {
	    	System.out.println(i);
	    }
	 }
}
