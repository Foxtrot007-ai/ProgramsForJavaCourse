package zad8labirynt;

import java.util.Random;

public class Labyrinth {
	int n;
	int m;
	droga[][] maze;
	boolean[][] visited;
	Random generator;
	public Labyrinth(int size_n, int size_m)
	{
		n = size_n + 4;
		m = size_m + 4;
		maze = new droga[n][m];
		visited = new boolean[n][m];
		generator = new Random();
	}

	public void generate_new()
	{
		do
		{
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(i == 0 || i == 1 || i == n-2 || i == n-1
					|| j == 0 || j == 1 ||j == m-2 || j == m-1)
				{
					visited[i][j] = true;
				}
				else visited[i][j] = false;
				maze[i][j] = droga.wall;
			}	
		}
		
		
			make_maze(2,2);
		}while(maze[n-3][m-3] != droga.road);
		
	}
	
	private boolean check_wall(droga a)
	{
		return a == droga.wall;
	}
	
	private boolean can_make_way(int x, int y, side s)
	{
		if(visited[x][y]) return false;
		
		switch (s){
		case up:
			return check_wall(maze[x-1][y]) && check_wall(maze[x+1][y]) && check_wall(maze[x][y+1]);
		case down:
			return check_wall(maze[x-1][y]) && check_wall(maze[x+1][y]) && check_wall(maze[x][y-1]);
		case left:
			return check_wall(maze[x][y-1]) && check_wall(maze[x+1][y]) && check_wall(maze[x][y+1]);
		case right:
			return check_wall(maze[x][y-1]) && check_wall(maze[x-1][y]) && check_wall(maze[x][y+1]);
		default: return false;
		}
	}
	
	
	private boolean check_way(int r, int x ,int y)
	{
		switch (r){
		case 1:
			return can_make_way(x-1,y  ,side.right);
		case 2:
			return can_make_way(x+1,y  ,side.left);
		case 3:
			return can_make_way(x  ,y+1,side.up);
		case 4:
			return can_make_way(x  ,y-1,side.down);
		default: return false;
		}
	}
	
	private void make_maze(int x, int y)
	{
		visited[x][y] = true;
		maze[x][y] = droga.road;
			
		int pick;
	
		while(check_way(1,x,y)
			||check_way(2,x,y)
			||check_way(3,x,y)
			||check_way(4,x,y))
		{
			do {
				pick = generator.nextInt(4) + 1;
			}while(!check_way(pick,x,y));
			
			
			switch (pick) {
			case 1:
				make_maze(x-1,y);
				break;
			case 2:
				make_maze(x+1,y);
				break;
			case 3:
				make_maze(x,y+1);
				break;
			case 4: 
				make_maze(x,y-1);
				break;
			}
		}
	}
	
	public droga[][] get_maze()
	{
		return maze;
	}
	
	public void show_maze()
	{
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(maze[i][j] == droga.wall)
					System.out.print("X");
				else System.out.print(" ");
			}	
			System.out.print("\n");
		}
	}
}
