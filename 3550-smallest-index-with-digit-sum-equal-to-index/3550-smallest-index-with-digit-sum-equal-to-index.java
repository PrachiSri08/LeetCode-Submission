class Solution {
    public int smallestIndex(int[] nums) {
        int n = nums.length;
        for(int i=0; i<n; i++){
            int sum =0;
            while(nums[i]>0){
                int a = nums[i]%10;
                sum +=a;
                nums[i] = nums[i]/10;
            }
            if(sum == i){
                return i;
            }
        }
        return -1;
    }
}