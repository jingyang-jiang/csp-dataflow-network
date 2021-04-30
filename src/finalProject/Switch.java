package finalProject;

public class Switch extends AbstractActor{

	public Switch() {
		super();
		initialize(2, 2);
	}
	


	@Override
	public void run() {
		assert isComplete();
		while(!Simulation.end) {
			try {

				int bool = aInputChannels[0].take();
				// this bool has to be either 0 or 1 
				assert bool == 0 || bool == 1 ;

				int data = aInputChannels[1].take();
				System.out.println(data);
				Fire(bool,data);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

		
	}
	
	@Override
	public void Fire(int... is) throws InterruptedException {
		int bool = is[0];
		int data = is[1];
		aOutputChannels[bool].put(data);
		
	}

}
