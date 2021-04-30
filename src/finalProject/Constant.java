package finalProject;

public class Constant extends AbstractActor{

	private int data; 
	
	public Constant(int i) {
		super();
		initialize(1, 1);
		data = i ;
	}
	
	@Override
	public void run() {
		assert isComplete();
		while(true) {
			
			try {
				aInputChannels[0].take();
				Fire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			
			
		}
		
	}
	
	@Override
	public void Fire(int... is) throws InterruptedException {
		aOutputChannels[0].put(data);
		
	}



}
