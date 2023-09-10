package zad12lambdy;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Zad3 {
	public static UnaryOperator<Integer> collatza;
	static {
		collatza = n -> (n == 1) 
			? 1 
			: (n % 2 > 0) 
				? 1 + collatza.apply(n * 3 + 1)
				: 1 + collatza.apply(n / 2);
	}
	public BinaryOperator<Integer> euklides;
	{
		euklides = (a,b) -> (a != b)
				? euklides.apply((a > b)? a - b : a,(b > a) ? b - a : b)
				: a;
	}
}