import java.util.ArrayList;
import java.util.List;

/**
 * This class has the porpose of creating a world with cells. Furthermore, the width
 * and height of the world can be called. Also, each cell in the world can be returned.
 * A list of creatures can be called for besides a full list of the cells.
 * 
 * @author 532033sh Sjoerd van der Heijden
 */

public class World {
	
	private int w;
	
	private int h;
	
	private List<Cell> c = new ArrayList<>();
	
	private List<Creature> cr = new ArrayList<>();
	
	/**
	 * When this constructor is called it initializes a world with a certain
	 * width and height. It also initializes all the Cell that make up the world.
	 * 
	 * @param w is an int that contains the width of the world
	 * @param h is an int that contains the height of the world.
	 */
	
	public World(int w, int h) {
		
		this.w = w;
		
		this.h = h;
		
		for(int i = 0; i<w; i++) {
			
			for(int j=0; j<h ; j++) {
				
				c.add(new Cell(this, i, j));

			}
		}
	}
	
	/**
	 * When this method is called it returns the width.
	 * 
	 * @return w is the width of the world
	 */
	
	public int getWidth() {
		
		return w;
		
	}
	
	/**
	 * When this method is called it returns the height of the world.
	 * 
	 * @return h is the width of the height
	 */
	
	public int getHeight() {
		
		return h;
	}
	
	/**
	 * When this method is called it returns the x and y coordinate
	 * of a cell.
	 * 
	 * @param x is the x coordinate of the cell
	 * @param y is the y coordinate of the cell
	 * @return cell is a Cell with coordinates x and y
	 */
	
	public Cell getCell(int x, int y) {
		
		for(Cell cell : c) {
			
			if(cell.getX() == x && cell.getY() == y) {
				
				return cell;
				
			}
			
		}

		throw new IllegalArgumentException();
		
	}

	/**
	 * When this mehtod is called it returns a List with all the creatures in the world.
	 * 
	 * @return cr is the List that contain all the Creatures in the world. 
	 */
	
	public List<Creature> getCreatures(){
		
		for(Cell cell : c) {
			
			cr.addAll(cell.getCreatures());
			
		}
		
		return cr;
		
	}
	
	
	/**
	 * When this method is called it returns a List will all the cells in the world.
	 * 
	 * @return c is the List containing all the Cells in the World.
	 */
	
	public List<Cell> getCellList(){
	
		List<Cell> getCellList = new ArrayList<>();
		 
		for(Cell cell : c) {
			
			getCellList.add(cell);
			
		}
		
		return getCellList;
		
	}
}
