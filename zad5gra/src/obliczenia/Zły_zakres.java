package obliczenia;

public class Z�y_zakres extends IllegalArgumentException {
	public Z�y_zakres(int z)
	{
		super("zakres powinien byc < 4, twoj zakres: " + z);
	}

}