package finalProject;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Simulation {

    public static ThreadPoolExecutor pool;
    static final Factory aFactory = new Factory();
	volatile static boolean end = false;
	volatile static long startTime;
	volatile static long endTime;
	public static void main(String[] args) throws InterruptedException  {
		
		int t = Integer.parseInt(args[0]);
		assert t>=1;
		pool= (ThreadPoolExecutor) Executors.newFixedThreadPool(t);
		startTime= System.currentTimeMillis();
		start();
	
		while(!end)TimeUnit.MILLISECONDS.sleep(100);
		pool.shutdown();
		System.out.print("time taken for "+t+" threads: "+(endTime-startTime)+"ms.");
	}
	
	
	
	static void start() {
		ArrayList<Actor> sim= exampleFor();
		sim.forEach(a -> pool.execute(a));
	}
	
	/**this example is taken from the same for loop in lecture notes 04-07, which iterates for 10 times and output a 10
	 * usually a channel is anonymous except when initialization is required.
	 * @return 
	 */
	static ArrayList<Actor> exampleFor(){
		ArrayList<Actor> result = new ArrayList<Actor>();
		//merge actor construction
		Actor merge = aFactory.createActor("merge");
		Actor fork1 = aFactory.createActor("fork");
		Actor fork2 = aFactory.createActor("fork");
		Actor constantFork =aFactory.createActor("fork");
		Actor constantTen = new Constant(10);
		Actor lessthan = aFactory.createActor("lessthan");
		Actor Switch = aFactory.createActor("switch");
		Actor inc = aFactory.createActor("inc");
		Actor output = aFactory.createActor("output");
		
		//this channel acts as input;
		Channel merge_falseChannel = aFactory.createChannel();
		for(int i=0;i<10000;i++)merge_falseChannel.set(0);
		merge.ConnectIn(merge_falseChannel, 2);
	
		Factory.connectActors(aFactory.createChannel(), merge, fork1, 0, 0);
		Factory.connectActors(aFactory.createChannel(), fork1, constantFork, 1, 0);
		Factory.connectActors(aFactory.createChannel(), fork1, Switch, 0, 1);
		Factory.connectActors(aFactory.createChannel(), Switch, inc, 0, 0);
		Factory.connectActors(aFactory.createChannel(), Switch, output, 1, 0);
		Factory.connectActors(aFactory.createChannel(), inc, merge, 0, 1);
		Factory.connectActors(aFactory.createChannel(), constantFork, lessthan, 0, 0);
		Factory.connectActors(aFactory.createChannel(), constantFork, constantTen, 1, 0);
		Factory.connectActors(aFactory.createChannel(), constantTen, lessthan, 0, 1);
		Factory.connectActors(aFactory.createChannel(), lessthan, fork2, 0, 0);
		Factory.connectActors(aFactory.createChannel(), fork2, Switch, 0, 0);
		//this channel is initialized to false for merge
		Channel merge_boolChanel = aFactory.createChannel();
		merge_boolChanel.set(1);
		fork2.ConnectOut(merge_boolChanel, 1);
		merge.ConnectIn(merge_boolChanel, 0);
		
		//connect output to a channel 
		Channel outputSink = aFactory.createChannel();
		output.ConnectOut(outputSink, 0);

		result.add(merge);result.add(fork1);result.add(lessthan);result.add(fork2);
		result.add(Switch);result.add(inc);result.add(output);result.add(constantTen);result.add(constantFork);
		return result;
	}
}
