public interface HeavyCompute {
    //
    // Returns a certificate to do computation.
	// When calls are made from single thread, it is guaranteed to return
	// unique certificate. But the implementation may not be threadsafe.
	// Therefore, the caller must ensure that no two threads are calling it
	// simultaneously.
    //
    public String getCertificate();
    
    //
    // Perform computation. The passed in certificate (cert) must be a certificate
    // that was obtained using a call to getCertificate().
    // One certificate can be used only for single computation.
    //
    public boolean compute(String cert);
}
