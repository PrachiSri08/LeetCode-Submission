class Solution {
    public int minMoves(int[] nums) {
        int count =0;
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int sum=0;
        for(int i=0; i<n; i++){
            sum +=nums[i];
            if(nums[i]<min)
            min = nums[i];
        }
        return (int) (sum - (long) n*min);
    }
}