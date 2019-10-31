import java.util.ArrayList;
import java.util.List;

/**
 * This class has the purpose of maintaining a cell. A cell is an element
 * in the two dimensional world. Cell contain plants (with a max of 100, min of 0).
 * Furthermore, the cell keeps a natural order. If two cells contain an equal number of plants, 
 * then a cell with less creatures is preferable over one with more creatures. 
 * More preferred cells should come earlier in the order than less preferred cells. 
 * 
 * The class can return the x and y coordinates of the cell in the world. Furthermore,
 * the Class can also return the world that the cell is in and the plants that are in the cell.
 * The ammount of plants can be changed with a method and creatures can be added and removed.
 * Lastly, a list of all creatures in the cell can be returned.
 *
 * @author 532033sh Sjoerd van der Heijden
 */

public class Cell implements Comparable<Cell> {
	
	public static final int MAX_PLANTS = 100;
	
	private int x;
	
	private int y;
	
	private World w;
	
	private int plants = 0;
	
	private List<Creature> creatureCell = new ArrayList<>();
	
	/**
	 * When this constructor is called it initializes the cell.
	 * 
	 * @param w is the world
	 * @param x is the x coordinate
	 * @param y is the y coordinate
	 */
	
	public Cell(World w, int x, int y) {
		
		this.x = x;
		
		this.y = y;
		
		this.w = w;
		
	}

	/**
	 * When this method is called it returns the x coordinate.
	 * 
	 * @return x coordinate
	 */
	
	public int getX() {
		
		return x;
	}
	
	/**
	 * When this method is called it returns the y coordinate.
	 * 
	 * @return y coordinate
	 */
	
	public int getY() {
		
		return y;
	}
	
	/**
	 * When this method is called it returns the world
	 * 
	 * @return w is the world
	 */
	
	
	public World getWorld() {
		
		return w;
		
	}
	
	/**
	 * When this method is called it returns the plant count.
	 * 
	 * @return plants is the total ammount of plants in a cell
	 */
	
	public int getPlants() {

		return plants;
		
	}
	
	/**
	 * When this method is called with an integer it changes the ammount of plants 
	 * in a cell. 
	 * 
	 * @param amount integer that changes the ammount of plants in a cell
	 * 
	 */
	
	public void changePlants(int amount) {
		
		if(plants + amount < 0) {
			
			throw new IllegalArgumentException();
			
		}
		
		plants += amount;
		
		if(plants > MAX_PLANTS) {
			
			plants = MAX_PLANTS;
			
		}	
	}
	
	/**
	 * When this method is called is adds a Creature to a cell.
	 * 
	 * @param c is the creature that is to be added.
	 */
	
	public void addCreature(Creature c) {
		
		creatureCell.add(c);
		
	}
	
	/**
	 * When this method is called it removes a creature from a cell.
	 * 
	 * @param c is the Creature that is to be removed. 
	 */
	public void removeCreature(Creature c) {
		
		creatureCell.remove(c);
		
	}
	
	/**
	 * When this method is called it returns a List containing all the creatures 
	 * in the cell.
	 * 
	 * @return creatureCell is a List that contains all the creatures in the cell.
	 */
	
	public List<Creature> getCreatures() {
		
		List<Creature> getCreaturesList = new ArrayList<>();
		 
		for(Creature creature : creatureCell) {
			
			getCreaturesList.add(creature);
			
		}
		
		return getCreaturesList;
		
	}
	
	/**
	 * This method defines the natural ordering of the Cell.
	 * The ordering of the cell is such that a cell with
	 * more plants is preferable over one with less plants.
	 * If thow cells contain an equal number of plants,
	 * then a cell with less creatures is preferable over 
	 * one with more creatures. 
	 */
	
	@Override
	public int compareTo(Cell other) {
		
		if(plants != other.plants) {
			
			return other.plants - plants;
			
		}
		
		else {
			
			return creatureCell.size() - other.creatureCell.size();
			
		}
	}
}
