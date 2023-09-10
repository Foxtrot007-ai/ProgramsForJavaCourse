package struktury;

public class ZbiorListowy implements Zbior, Cloneable{

	private class Wezel{
		private Para para;
		private Wezel nast;
		
		public Wezel(Para p) {
			nast = null;
			para = p;
		}
		
		
		public Para get_para()
		{
			return para;
		}
		
		public Wezel get_nast()
		{
			return nast;
		}
		
		public void set_nast(Wezel w)
		{
			nast = w;
		}
	}
	
	private Wezel lista;
	
	public ZbiorListowy()
	{
		lista = null;
	}
	
	@Override
	public Para szukaj(String k) {
		Wezel aktu = lista;
		
		if(aktu == null)
		{
			return null;
		}else {
			while(aktu.get_nast() != null && !aktu.get_para().get_klucz().equals(k))
			{
				aktu = aktu.get_nast();
			}
		}
		
		if(!aktu.get_para().get_klucz().equals(k)) return null;
		else return aktu.get_para();
	}

	@Override
	public void wstaw(Para p) {
		Wezel aktu = lista;
		
		if(aktu == null)
		{
			this.lista = new Wezel(p);
		}else {
			while(aktu.get_nast() != null && !aktu.get_para().equals(p)) {
				aktu = aktu.get_nast();
			}
			if(aktu.get_para().equals(p)) aktu.get_para().set_wartoœæ(p.get_wartoœæ());
			else aktu.nast = new Wezel(p);
			
		}
	}

	@Override
	public void usuñ(String k) {
		Wezel aktu = lista;
		Wezel przed = null;
		if(aktu == null)
		{
			return;
		}else {
			while(aktu.get_nast() != null && !aktu.get_para().get_klucz().equals(k))
			{
				przed = aktu;
				aktu = aktu.get_nast();
			}
		}
		
		if(!aktu.get_para().get_klucz().equals(k)) return;
		
		if(przed == null)
		{
			lista = aktu.get_nast();
		}else if(aktu.get_nast() == null)
		{
			przed.set_nast(null);
		}else {
			przed.set_nast(aktu.get_nast());
		}
		
	}

	@Override
	public void czysc() {
		lista = null;
		
	}

	@Override
	public int ile() {
		Wezel aktu = lista;
		
		if(aktu == null)
		{
			return 0;
		}else {
			int size = 1;
			while(aktu.get_nast() != null) {
				aktu = aktu.get_nast();
				size++;
			}
			return size;
		}
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	
	public String toString()
	{
		Wezel aktu = lista;
		String temp = "|Lista|";
		while(aktu != null) {
			temp += aktu.get_para().toString();
			aktu = aktu.get_nast();
		}
		return temp;
	}
}
