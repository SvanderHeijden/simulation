import java.util.ArrayList;

import java.util.Collections;

import java.util.List;

/**
 * This cell has the porpose of extending the creature class. The extended creature (herbivore)
 * has a sight that is equal to 1, and energy equal to 20. The class can return the size of the 
 * herbivore. Furthermore, the move method extends the move method in creature. The class uses
 * the natural ordering of the cells to decide where the herbivore must move. Also, a herbivore
 * can be called to act to eat some or all of the plants in a cell. 
 * 
 * @author 532033sh Sjoerd van der Heijden
 */

public class Herbivore extends Creature {
	
	private static int s = 1;
	
	private int size;
	
	/**
	 * When this constructor is called it initializes a Herbivore (extended from creature) 
	 * with a certain size. An Illegal Argument Exception is thrown when the size is smaller
	 * or equal to zero.
	 * 
	 * @param size is the size of the Herbivore.
	 */
	
	public Herbivore(int size) {
		
		super(s);
		
		this.size = size;
		
		if(size <= 0) {
			
	         throw new IllegalArgumentException();
	         
	     }
	}
	
	
	/**
	 * When this method is called it returns the size of a Herbivore. 
	 * 
	 * @return size is the size of the herbivore.
	 */
	
	public int getSize() {
		
		return size;
		
	}
	
	@Override
	public void move() {
		
		List<Cell> vc = new ArrayList<>();
		
		vc = this.getVisibleCells();
		
		Collections.sort(vc);
		
		int x = vc.get(0).getX();

		int y = vc.get(0).getY();

		this.moveTo(this.getCurrentCell().getWorld().getCell(x,y));
		
	}
	
	@Override
	public void act() {
		
		int hEat = 0;
		
		if(this.getCurrentCell().getPlants() >= (2*size*size)/(1+size)) {
			
			hEat = (2*size*size)/(1+size);
			
		}
		
		else {
			
			hEat = this.getCurrentCell().getPlants();

		}

		this.getCurrentCell().changePlants(-hEat);
		
		this.changeEnergy(-size+hEat);

	}

}
