package obliczenia;

public class Z³y_zakres extends IllegalArgumentException {
	public Z³y_zakres(int z)
	{
		super("zakres powinien byc < 4, twoj zakres: " + z);
	}

}