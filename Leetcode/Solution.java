class Solution {
    public long maximumTripletValue(int[] nums) {
        long max = 0;
        long tmp;
        int l = nums.length;

        for (int i = 0; i < l; i++)
        {
            for (int j = i + 1; j < l; j++)
            {
                for (int k = j + 1; k < l; k++)
                {
                    tmp = (long) (nums[i] - nums[j]) * nums[k];
                    if (tmp > max)
                    {
                        max = tmp;
                    }
                }
            }
        }

        return max;
    }
}