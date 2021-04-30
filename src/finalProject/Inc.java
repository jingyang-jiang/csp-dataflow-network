package finalProject;

public class Inc extends AbstractActor{


	public Inc() {
		super();
		initialize(1, 1);
	}
	
	@Override
	public void run() {
		assert isComplete();
		while (!Simulation.end) {
			try {
				if(aInputChannels[0].peek()==null) {
					if(!Simulation.end) {
						Simulation.pool.getQueue().add(this);
						break;
					}else {break;}
				}
				int x = aInputChannels[0].take();
				Fire(x);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		

		
	}

	@Override
	public void Fire(int... is) throws InterruptedException {
		for ( int i : is)aOutputChannels[0].put(i+1);
		
	}

}
