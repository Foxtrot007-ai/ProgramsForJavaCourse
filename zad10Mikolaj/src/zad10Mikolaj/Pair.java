package zad10Mikolaj;

public class Pair<T1, T2> {
	private T1 L;
	private T2 R;
	public Pair(T1 x, T2 y)
	{
		L = x;
		R = y;
	}
	private Pair() {}
	public T1 get_L()
	{
		return L;
	}
	public T2 get_R()
	{
		return R;
	}
	
	public void set_L(T1 x)
	{
		L = x;
	}
	public void set_R(T2 y)
	{
		R = y;
	}
}

