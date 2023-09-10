package obliczenia;

public class ZeroException extends IllegalArgumentException {
	public ZeroException()
	{
		super("Divide by 0");
	}

}
