package finalProject;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation {

    static ExecutorService pool;
    static final Factory aFactory = new Factory();
	
	public static void main(String[] args) {
		int t = Integer.parseInt(args[0]);
		assert t>=1;
		pool= Executors.newFixedThreadPool(t);
		
	}
	
	void start() {
		
	}
	
	ArrayList<Actor> exampleFor(){
		ArrayList<Actor> result = new ArrayList<Actor>();
		
		return null;
	}
}
