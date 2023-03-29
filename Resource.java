
import java.util.*;



 class Resource
{
	private int numResources;
	private final int MAX = 5;

	//constructor
	//we are initializing start level with "0"
	public Resource(int startLevel)
	{
		numResources = startLevel;
	}
	
	public int getLevel()
	{
		return numResources;
	}


	//add one and takeOne() methods must be working synchronized
	//to avoid from interrupt each other
	//because they are using the same variable "numResources"


	public Stack<Integer> stack = new Stack<>();
	public  int max=100;
	public  int min=1;
	public int popedItem,pushedItem;


	public synchronized void addOne()
	{
		try
		{
			while (numResources >= MAX)	wait();
			//makes waiting the producer
			//else:

			numResources++;
			//0->> 1,2,3,4,5

			pushedItem = stack.push((int)(Math.random() *(max - min + 1) + min  ));

			System.out.println("PUSHED ITEM = " + pushedItem);
			if(stack.size()==5){
				System.out.println("STACK IS FULL");
			}
			
			//'Wake up' any waiting consumer...
//			notifyAll();
			//used notifyAll() instead of notify() because we have more than one customer
		}
		catch (InterruptedException interruptEx)
		{
			System.out.println(interruptEx);
		}
	}
	
	public synchronized int takeOne()
	{
		
		try
		{			
			while (numResources == 0) wait();
			popedItem = stack.pop();
			numResources--;
			System.out.println("POPED ITEM = " + popedItem);
			if(stack.isEmpty()){
				System.out.println("STACK IS EMPTY");
			}

			//'Wake up' waiting producer...
			//if numResources doesn't equal to 0 and takeOne() method called by customer
			//than inform producer ( Resource.class above) to procure new resources
			notify(); //oppsite of wait

		}
		catch (InterruptedException interruptEx)
		{
			System.out.println(interruptEx);
		}
		return popedItem;
	}
}