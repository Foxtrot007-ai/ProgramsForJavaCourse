package obliczenia;

abstract class Operator2Arg extends Operator1Arg{
	protected Wyrazenie w2;
	public Operator2Arg(Wyrazenie a, Wyrazenie b) {
		super(a);
		w2 = b;
	}
}
