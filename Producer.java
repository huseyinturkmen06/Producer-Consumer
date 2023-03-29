class Producer extends Thread
{
	private Resource item;
	
	public Producer(Resource resource)
	{
		item = resource;
	}
	
	public void run()
	{
		int pause;
		int newLevel;
		do
		{
			try
			{
				item.addOne();
				pause = (int)(Math.random() * 1000);
				// waiting time in ResourceServer and Resource
				sleep(pause);
			}
			catch (InterruptedException interruptEx)
			{
				System.out.println(interruptEx);
			}
		}while (true);
	}
}