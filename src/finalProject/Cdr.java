package finalProject;

public class Cdr extends AbstractActor{

	private boolean CdrDone = false; 
	public Cdr() {
		super();
		initialize(1, 1);
	}

	@Override
	public void run() {
		assert isComplete();
		
		while(!Simulation.end) {
			try {
				if(aInputChannels[0].peek()==null) {
					if(!Simulation.end) {
						Simulation.pool.getQueue().add(this);
						break;
					}else {break;}
				}
				
				if(!CdrDone) {
					aInputChannels[0].take();
					CdrDone = true;
					continue;
				}
				
				int result = aInputChannels[0].take();
				Fire(result);
				
			}catch (InterruptedException e ) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void Fire(int... is) throws InterruptedException {
		for (int i : is)aOutputChannels[0].put(i);
		
	}



}
