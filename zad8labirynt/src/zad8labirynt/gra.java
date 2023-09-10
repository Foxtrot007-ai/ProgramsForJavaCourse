package zad8labirynt;

public class gra {
	private Labyrinth l;
	private int gracz_x;
	private int gracz_y;
	private int n;
	private int m;
	

	public void zmien_wielkosc(int size_n, int size_m)
	{
		n = size_n;
		m = size_m;
		l = new Labyrinth(size_n,size_m);
	}
	
	public boolean czy_zwyciestwo()
	{
		return (gracz_x == n+1 && gracz_y == m+1);
	}
	
	public void restart() 
	{
		gracz_x = 2;
		gracz_y = 2;
		l.generate_new();
		l.show_maze();
	}
	
	public void ruch_gracza(side s)
	{
		droga[][] temp = l.get_maze();
		temp[gracz_x][gracz_y] = droga.road;
		switch (s){
		case up:
			if(temp[gracz_x	   ][gracz_y - 1] == droga.road)
				gracz_y--;
		break;
		case down:
			if(temp[gracz_x	   ][gracz_y + 1] == droga.road)
				gracz_y++;
			break;
		case left:
			if(temp[gracz_x - 1][gracz_y 	] == droga.road)
				gracz_x--;
			break;
		case right:
			if(temp[gracz_x + 1][gracz_y    ] == droga.road)
				gracz_x++;
			break;
		default: 
			break;
		}
		System.out.println(gracz_x + " "+gracz_y);
		temp[gracz_x][gracz_y] = droga.gracz;
		
	}
	
	public droga[][] plansza()
	{
		droga[][] temp = l.get_maze();
		
		temp[n][m] = droga.road;
		temp[gracz_x][gracz_y] = droga.gracz;
		
		return temp;
	}
	
}
