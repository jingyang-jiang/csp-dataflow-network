package finalProject;

public class Merge extends AbstractActor {

	public Merge() {
		super();
		initialize(3, 1);
	}
	


	@Override
	public void run() {
		assert isComplete();
		while (!Simulation.end) {
			try {
				
				int bool = aInputChannels[0].take();
				//this bool has to be either 0 or 1 
				assert bool == 0 || bool == 1; 
				if(bool == 0 ) {
					
					int trueBranch = aInputChannels[1].take();
					Fire(trueBranch);
				} else {
					
					int falseBranch = aInputChannels[2].take();
					Fire(falseBranch);
				}
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}

		
	}
	
	@Override
	public void Fire(int... is) throws InterruptedException {
			aOutputChannels[0].put(is[0]);
	}
	
}
