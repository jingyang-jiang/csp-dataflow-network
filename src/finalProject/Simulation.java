package finalProject;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulation {

    static ExecutorService pool;
    static final Factory aFactory = new Factory();
	static boolean end = false;
	public static void main(String[] args)  {
		
		int t = Integer.parseInt(args[0]);
		assert t>=1;
		pool= Executors.newFixedThreadPool(t);
		long start= System.currentTimeMillis();
		start();
		System.out.println(end);
		while(System.currentTimeMillis()-start<200);
		end = true;
		System.out.println(end);
		
	}
	
	static void start() {
		ArrayList<Actor> sim= exampleFor();
		sim.forEach(a -> pool.execute(a));
	}
	
	/**this example is taken from the same for loop in lecture notes 04-07, which iterates for 10 times and output a 10
	 * @return 
	 */
	static ArrayList<Actor> exampleFor(){
		ArrayList<Actor> result = new ArrayList<Actor>();
		//merge actor construction
		Actor merge = aFactory.createActor("merge");
		Channel merge_falseChannel = aFactory.createChannel();
			//this initialize it with a 0
		merge_falseChannel.set(0);
			// the bool Channel is missing because it is the output of some other actor.
		Channel merge_trueChannel = aFactory.createChannel();
		Channel merge_outChannel = aFactory.createChannel();
		merge.ConnectIn(merge_trueChannel, 1);
		merge.ConnectIn(merge_falseChannel, 2);
		merge.ConnectOut(merge_outChannel, 0);
		//fork1 actor construction 
		Actor fork1 = aFactory.createActor("fork");
		Channel fork1_outChannel1 = aFactory.createChannel();
		Channel fork1_outChannel2 = aFactory.createChannel();
		fork1.ConnectIn(merge_outChannel, 0);
		fork1.ConnectOut(fork1_outChannel1, 0);
		fork1.ConnectOut(fork1_outChannel2, 1);
		//less than actor construction
		Actor lessthan = aFactory.createActor("lessthan");
		Channel constantTen = aFactory.createChannel();
		for(int i = 0;i<10;i++)
			try {
				constantTen.put(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // this is kind of abusing the implementation
		Channel lessthan_outChannel = aFactory.createChannel();
		lessthan.ConnectIn(fork1_outChannel2, 0);
		lessthan.ConnectIn(constantTen, 1);
		lessthan.ConnectOut(lessthan_outChannel, 0);
		//fork2 actor construction
		Actor fork2 = aFactory.createActor("fork");
		Channel fork2_outChannel1 = aFactory.createChannel();
		Channel fork2_outChannel2 = aFactory.createChannel();
		fork2_outChannel2.set(1);
		fork2.ConnectIn(lessthan_outChannel, 0);
		fork2.ConnectOut(fork2_outChannel1, 0);
		fork2.ConnectOut(fork2_outChannel2, 1);
		merge.ConnectIn(fork2_outChannel2, 0);
		//switch actor construction 
		Actor Switch = aFactory.createActor("switch");
		Channel Switch_outTrue = aFactory.createChannel();
		Channel Switch_outFalse = aFactory.createChannel();
		Switch.ConnectIn(fork2_outChannel1,0);
		Switch.ConnectIn(fork1_outChannel1, 1);
		Switch.ConnectOut(Switch_outTrue, 0);
		Switch.ConnectOut(Switch_outFalse, 1);
		//Inc actor construction
		Actor inc = aFactory.createActor("inc");
		inc.ConnectIn(Switch_outTrue, 0);  
		inc.ConnectOut(merge_trueChannel, 0);
		//output actor construction
		Actor output = aFactory.createActor("output");
		Channel sink = aFactory.createChannel();
		output.ConnectIn(Switch_outFalse, 0);
		output.ConnectOut(sink, 0);
		//add all actors 
		result.add(merge);result.add(fork1);result.add(lessthan);result.add(fork2);result.add(Switch);result.add(inc);result.add(output);
		return result;
	}
}
