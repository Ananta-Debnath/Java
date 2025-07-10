//
// Job Executor 
//
// Do not remove any line of code
// Follow the TODO remark and write your code
//

import java.util.Queue;

public class Executor extends Thread {
    // Queue of work items.
    private Queue<WorkItem> queue = null;

    // Whether termination of the Executor has been signalled.
    private boolean fQuit = false;
    
    public Executor(Queue<WorkItem> queue, String name) {
        super(name);
        this.queue = queue;
    }

    public void signalShutDown() {
        //
        // TODO: Record that shutdown has been signalled.
        //
        fQuit = true;
    }

    public void run() {
        //
        // Executor thread begins
        //
        // TODO: Run an infinite loop. If there is work to do, we perform it
        // Otherwise, we go to sleep for a second and try again. However, 
        // if shutdown has been signalled, then we break the infinite loop
		//
		// To extract a workitem from queue, use queue.poll(). If there is
		// nothing in the queue, it will return null. Else, it will return the
		// reference to a work item to be executed.
		//
		// The underlying queue is not threadsafe. So we need to
        // take appropriate measure(s).
        //
        WorkItem workItem;
        while (true) {
			//
			// If the work was successfully performed, we write a log statement.
			//
            synchronized (Executor.class)
            {
                workItem = queue.poll();
            }
            if (workItem != null)
            {
                workItem.work();
                System.out.println("" + Thread.currentThread().getName() + ">> Finished work item: " + workItem.getId());
            }
            else
            {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            if (fQuit) break;
        }
        
    }
}
