package finalProject;

public class Output extends AbstractActor{

	public Output() {
		super(); 
		initialize(1, 1);
	}

	@Override
	public void run() {
		assert isComplete();
		while(!Simulation.end) {
			try {

				int x = aInputChannels[0].take();
				Fire(x);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			
		}

		
	}

	@Override
	public void Fire(int... is) throws InterruptedException {
		for(int i : is ) System.out.println(i);
		
	}


}
