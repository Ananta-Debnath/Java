public class MyThread extends Thread {
    //
	// TODO: Write necessary member variables
	//
	MyMTAObject mta;

    MyThread(MyMTAObject mta, String threadName) {
		//
        // TODO: Implement this constructor.
		// Do not start the thread here.
		super(threadName);
		this.mta = mta;
		
    }
	
	//
	// TODO: Write necessary code so that after the thread is started
	// it performs the heavy computation by calling doCompute() on the provided
	// MyMTAObject instance. Then it is done.
	//

	@Override
	public void run()
	{
		mta.doCompute();
	}

	
}
