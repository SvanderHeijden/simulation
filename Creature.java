import java.util.ArrayList;
import java.util.List;

/**
 * This cell has the purpose of maintaining a creature. A creature has a certain energy
 * and sight. A creature can be moved to any cell in the world. Furthermore, a creature 
 * can be called to move and act which is decided in the extended classes. The sight, cell
 * and energy can be retured with methods. A creature can be called to die. When it does,
 * the creature is removed from the cell and its cell is set to null. Furthermore, a creature 
 * can be called whether it is alive. Also, the energy of a creature can be changed. Lastly, a method 
 * returns all the visible cells that are within sight of the creauture. 
 * 
 * @author 532033sh Sjoerd van der Heijden
 *
 */
public abstract class Creature {
	
	protected final int s;
	
	private int energy = 20;
	
	private Cell c;
	
	/**
	 * When this constructor is called it initializes a Creature
	 * 
	 * @param s is the sight of the creaure. 
	 */
	
	public Creature(int s) {
		
		this.s = s;
		
	}
	
	/**
	 * When this method is called it moves the creature to a certain cell in the world. 
	 * 
	 * @param newCell is the cell where the creature is supposed to move to. 
	 */
	
	public final void moveTo(Cell newCell) {
		
		this.die();
		
		this.c = newCell;
		
		c.addCreature(this);
		
		
		
	}
	
	/**
	 * When this method is called it return the cell that contains the creature. 
	 * 
	 * @return c is the current cell that contains the creature.
	 */
	
	public final Cell getCurrentCell() {
		
		return c;
		
	}
	
	/**
	 * When this abstact method is called the creature will be moved. 
	 * 
	 * For the herbivore extension:
	 * When this method is called it will make the Herbivore it will eat the plants 
	 * in the cell given by the formula Heat = min(plants, |(2*size^2)/(1+size)|. The 
	 * creature can never eat more plants than the total number of plants in the cell.
	 * 
	 * After eating, the energy of the herbivore will be increased with the number of 
	 * plants it has eaten and decreased by its size due to metabolism. 
	 * 
	 * For the carnivore extension
	 * The ordering of the cell is such that a cell with more herbivores is preferable 
	 * over one with less herbivores. If the cells contain an equal number of herbivores,
	 * then a cell with the biggest herbivore is preferable over one with a smaller herbivore. 
	 * 
	 * This method uses the moveTo method to move a Carnivore to the best cell.
	 */
	
	public abstract void move();
	
	/**
	 * When this abstact method is called the creature will act.
	 * 
	 * For the herbivore extension:
	 * When this method is called it will make the Herbivore it will eat the plants 
	 * in the cell given by the formula Heat = min(plants, |(2*size^2)/(1+size)|. The 
	 * creature can never eat more plants than the total number of plants in the cell.
	 * 
	 * After eating, the energy of the herbivore will be increased with the number of 
	 * plants it has eaten and decreased by its size due to metabolism. 
	 * 
	 * For the carnivore extension:
	 * When this method is called it will make the Carnivore eat the biggest herbivore 
	 * in the cell.
	 * 
	 * After eating, the energy of the carnivore will be increased with the size of the herbivore 
	 * and its energy is decreased by 6 (even if there are no herbivores in the cell).

	 * 
	 */
	
	public abstract void act();
	
	/**
	 * When this method is called it returns the sight of the creature.
	 * 
	 * @return s is the sight if the creature.
	 */
	
	public final int getSight() {
		
		return s;
		
	}
	
	/**
	 * When this method is called the creature and the creature
	 * is alive the creature is removed from the cell and the cell
	 * is set to null. 
	 * 
	 */
	
	public final void die() {
		
		if(this.isAlive()) {
			
			c.removeCreature(this);
			
			c = null;
			
		}
	}
	
	/**
	 * 
	 * When this method is called it checks whether the cell is null. A 
	 * boolean is set to false is the cell is indeed null.
	 * 
	 * @return myBoolean is true when the animal is alive, false when it isnt.
	 */
	
	public final boolean isAlive() {
		
		boolean myBoolean = true;
		
		if(c == null) {
			
			myBoolean = false;
			
		}
		
		return myBoolean;
		
	}
	
	/**
	 * When this method is called it return the energy of the creature.
	 * 
	 * @return energy is the energy of the creature. 
	 */
	
	public final int getEnergy() {
		
		return energy;
		
	}
	
	/**
	 * When this method is called it changes the energy of a creature with a certain amount.
	 * 
	 * @param amount is an int that is used to change the energy of a creature
	 */
	public final void changeEnergy(int amount) {
	
		energy += amount;
			
		if(energy < 1){
				
			this.die();
				
		}
	}
	
	/**
	 * When this method is called it returns all visible cells of the creature.
	 * The visible cells are given by Dist(c1,c2) = max(|xc1-xc2|,|yc1-yc2|).
	 * 
	 * @return vc is a List that contains all cells that are visible for
	 * the creature. 
	 */
	
	public final List<Cell> getVisibleCells(){
		
		List<Cell> vc = new ArrayList<>();
		
		int xMin = 0;
		
		int xMax = 0;
		
		int yMin = 0;
		
		int yMax = 0;
		
		xMin = c.getX() - s;
		
		if(xMin < 0) {
			
			xMin = 0;
			
		}

		xMax = c.getX() + s;
		
		if(xMax >= c.getWorld().getWidth()) {
			
			xMax = c.getWorld().getWidth() - 1;
			
		}
		
		yMin = c.getY() - s;
		
		if(yMin < 0) {
			
			yMin = 0;
			
		}
		
		yMax = c.getY() + s;
		
		if(yMax >= c.getWorld().getHeight()) {
			
			yMax = c.getWorld().getHeight() - 1;
			
		}
		
		for(int i = xMin; i<=xMax; i++) {
			
			for(int j = yMin; j<=yMax; j++) {
				
				vc.add(c.getWorld().getCell(i, j));
				
			}
		}
		
		return vc;
	}
}
