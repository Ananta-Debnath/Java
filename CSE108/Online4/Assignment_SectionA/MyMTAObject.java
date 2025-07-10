public class MyMTAObject {
	//
	// TODO: Write appropriate member variable and constructor
	// to store a HeavyCompute implementation
	//
	MDSRHeaveyCompute heavy;

    public MyMTAObject(MDSRHeaveyCompute heavy) {
        this.heavy = heavy;
    }

	//
	// TODO: Write appropriate implementation of doCompute()
	// NOTE: This method will be called from multiple threads.
	//
	// You have to first collect a certificate for computation.
	// Ensure that no two threads get the same certificate.
	//
	// Then call compute() with the certificate.
	// Write your code in a way to maximize parallelization.
	//

	void doCompute()
	{
		String cert;
		synchronized (this) {cert = heavy.getCertificate();}
		// cert = heavy.getCertificate();
		heavy.compute(cert);
	}
}
