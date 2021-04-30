package finalProject;

public class LessThan extends AbstractActor{

	public LessThan() {
		super();
		initialize(2, 1);
	}

	@Override
	public void run() {
		assert isComplete();
		while(!Simulation.end) {
			try {
				if(aInputChannels[0].peek()==null ||aInputChannels[1].peek()==null) {
					if(!Simulation.end) {
						Simulation.pool.getQueue().add(this);
						break;
					}else {break;}
				}
				int x = aInputChannels[0].take();
				int y = aInputChannels[1].take();
				Fire (x,y);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			
		}

		
	}


	@Override
	public void Fire(int... is) throws InterruptedException {
		//again,counter-intuitively, 0 for true and 1 for false
		if (is[0]<is[1]) {
			aOutputChannels[0].put(0);
		}else {
			aOutputChannels[0].put(1);
		}
		
	}

}
