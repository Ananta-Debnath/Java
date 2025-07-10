//
// This class provides an implementation of the HeavyCompute interface
// Students have no action item in this class.
//

import java.util.Random;

public class MDSRHeaveyCompute implements HeavyCompute {
    private int[] count = new int[501];
    int certSeed = 1;
    Random rng = new Random(0);

    public String getCertificate() {
        String cert = Integer.toString(certSeed);
        certSeed++;
        try {
            Thread.sleep(rng.nextInt(50)+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cert;
    }
    public boolean compute(String cert) {
        try {
            Thread.sleep(rng.nextInt(200)+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        count[Integer.parseInt(cert)]++;
        System.out.println(Thread.currentThread().getName() + " completed computation with certificate: " + cert);
        return true;
    }

    public void reportSanity() {
        System.out.println("Sanity checking started ...");
        for (int i = 1; i < count.length; i++) {
            if (count[i] != 1) {
                System.out.println("ERROR!!! Certificate " + i + " was used for " + count[i] + " computes.");
            }
        }
        System.out.println("Sanity checking Completed");
    }
}
