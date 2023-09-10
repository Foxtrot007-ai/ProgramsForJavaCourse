package zad10Mikolaj;

public class Stan_gry {
	private stany_gry stan;
	public Stan_gry()
	{
		stan = stany_gry.startowy;
	}
	public void ustaw_stan(stany_gry s)
	{
		stan = s;
	}
	
	public stany_gry get_stan()
	{
		return stan;
	}
}
