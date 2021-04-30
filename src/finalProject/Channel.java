package finalProject;

public interface Channel{

	public static Channel NullChannel = new Channel () {
		@Override
		public void set(int i) {return;}
		@Override
		public int take() throws InterruptedException {return 0;}
		@Override
		public void put(int i) throws InterruptedException {return;}
		@Override
		public boolean isNull() {return true;}
	};
	
	void set(int i);
	int take() throws InterruptedException;
	void put(int i) throws InterruptedException;
	default boolean isNull() {return false ;}
}
