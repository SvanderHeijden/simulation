import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This cell has the porpose of extending the creature class. The extended creature (carnivore)
 * has a sight that is equal to 2, and energy equal to 30. The move method extends the move method in creature. 
 * The class uses a special ordering to devide where the creature must move (see method for more explanation).
 * Also, a carnivore can be called to act that will make it eat the biggest herbivore in the cell. 
 * 
 * @author 532033sh Sjoerd van der Heijden
 */

public class Carnivore extends Creature {
	
	private static int s;
	
	
	/**
	 * When this constructor is called it initiates a new creature that is a Carnivore.
	 * The energy is set to 30 and the sight is set to 2. 
	 */
	
	public Carnivore() {
		
		super(s);
		
		this.changeEnergy(10);
				
		s = 2;
	}
	
	@Override
	public void move() {
		
		List<Cell> vc = new ArrayList<>();
		
		List<Integer> myCount = new ArrayList<>();
		
		List<Integer> mySize = new ArrayList<>();
		
		vc = this.getVisibleCells();
	
		for(Cell visible : vc) {
			
			List<Creature> cr = visible.getCreatures();
			
			int count = 0;
			
			int maxSize = 0;
			
			for(Creature creatures : cr) {
				
				if(creatures instanceof Herbivore){
					
					count++;
					
					if(maxSize < ((Herbivore) creatures).getSize()) {
						
						maxSize = ((Herbivore) creatures).getSize();

					}
					
				}

			}
			
			mySize.add(maxSize);
			
			myCount.add(count);
		}
		
		Integer max = Collections.max(myCount);
		
		
		List<Integer> index = new ArrayList<>();
		
		List<Integer> indexSize = new ArrayList<>();
		
		int newCount = 0;
		
		for(Integer item : myCount){
			
			if(item == max) {
				
				index.add(newCount);
				
			}
			
			newCount++;
		}
		
		for(Integer item : index) {
			
			indexSize.add(mySize.get(item));
				
		}
		
		int finalMax = Collections.max(indexSize);
		
		int finalIndex = 0;
		
		for(Integer size : mySize) {
			
			for(Integer item : index) {
				
				if(size == finalMax) {
					
					if(finalIndex < item) {
						
						finalIndex = item;
						
					}
				}
			}
		}
		
		
		if(Collections.max(myCount) != 0) {
			
			this.moveTo(this.getCurrentCell().getWorld().getCell(vc.get(finalIndex).getX(),vc.get(finalIndex).getY()));
			
		}
	}

	@Override
	public void act() {
		
		List<Creature> cr = this.getCurrentCell().getCreatures();
		
		List<Creature> herb = new ArrayList<>();
		
		for(Creature creature : cr) {
			
			if(creature instanceof Herbivore) {
				
				herb.add(creature);
				
			}
		}
		
		int maxSize = 0;
		
		for(Creature h : herb) {
			
			if(maxSize < ((Herbivore) h).getSize()){
				
				maxSize = ((Herbivore) h).getSize();
				
			}
			
		}
		
		for(Creature h : herb) {
			
			if(maxSize == ((Herbivore) h).getSize()) {
				
				this.changeEnergy(h.getEnergy());
				
				h.die();
				
				
				
			}
		}
		
		this.changeEnergy(-6);
	}
}
