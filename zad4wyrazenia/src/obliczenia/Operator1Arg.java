package obliczenia;

abstract class Operator1Arg extends Wyrazenie{
	protected Wyrazenie w1;
	
	private Operator1Arg(){}
	public Operator1Arg(Wyrazenie w)
	{
		w1 = w;
	}

}
