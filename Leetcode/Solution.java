
import java.util.Arrays;

class Solution {
    public long countGoodIntegers(int n, int k) {
        int freq[] = new int[10];
        long count = 0;
        int now, tmp;
        
        int start = (10*n) / k;
        if (k * 10 < n) start++;

        for (int i = start; k*i < 100*n; i++)
        {
            Arrays.fill(freq, 0);
            tmp = 0;
            now = k*i;

            do
            {
                freq[now % 10]++;
            }
            while (now/10 != 0);

            for (int num: freq) tmp += num % 2;
            if (tmp < 2) count++;
        }

        return count;
    }
}