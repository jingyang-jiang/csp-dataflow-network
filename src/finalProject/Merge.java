package finalProject;

public class Merge extends AbstractActor {

	public Merge() {
		super();
		initialize(3, 1);
	}
	


	@Override
	public void run() {
		assert isComplete();
		while (true) {
			try {
				int bool = aInputChannels[0].take();
				//this bool has to be either 0 or 1 
				assert bool == 0 || bool == 1; 
				int trueBranch = aInputChannels[1].take();
				int falseBranch = aInputChannels[2].take();
				Fire(bool,trueBranch,falseBranch);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
		
	}
	
	@Override
	public void Fire(int... is) throws InterruptedException {

		int bool = is[0];
		int trueBranch = is[1];
		int falseBranch = is[2];
		if (bool == 0) {
			aOutputChannels[0].put(trueBranch);
		}else {
			aOutputChannels[0].put(falseBranch);
		}
		
	}
	
}
