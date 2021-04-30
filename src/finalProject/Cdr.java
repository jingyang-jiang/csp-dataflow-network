package finalProject;

public class Cdr extends AbstractActor{

	public Cdr() {
		super();
		initialize(1, 1);
	}

	@Override
	public void run() {
		assert isComplete();
		try {
			aInputChannels[0].take();
			while (!Simulation.end) {
				int result = aInputChannels[0].take();
				Fire(result);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Fire(int... is) throws InterruptedException {
		for (int i : is)aOutputChannels[0].put(i);
		
	}



}
