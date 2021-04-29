package finalProject;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation {

	static ExecutorService pool;
	public static int CHANNELCAPACITY = 100 ; 
	
	public static void main(String[] args) {
		int t = Integer.parseInt(args[0]);
		pool= Executors.newFixedThreadPool(t);
		
	}
	
	void start() {
		
	}
}
