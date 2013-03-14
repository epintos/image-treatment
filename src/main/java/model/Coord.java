package model;



import java.util.ArrayList;
import java.util.List;

public class Coord extends java.awt.Point {
	private static final long serialVersionUID = 1L;
	
	public Coord(int x, int y) {
		super(x, y);
	}

	
	public List<Coord> N4() {
		List<Coord> resp = new ArrayList<Coord>();
			resp.add(new Coord(this.x, this.y-1));
			resp.add(new Coord(this.x-1, this.y));
			resp.add(new Coord(this.x+1,this.y));
			resp.add(new Coord(this.x, this.y+1));
		return resp;
	}

}
