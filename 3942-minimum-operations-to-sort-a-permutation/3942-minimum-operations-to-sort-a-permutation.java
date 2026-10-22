class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero =0;
        for(int i=0; i<n; i++){
            if(nums[i] == 0){
                zero=i;
                break;
            }
        }
        boolean left =true;
        boolean right =true;
        for(int i=1; i<n; i++){
            int prevR = (zero+i-1)%n;
            int currR = (zero+i)%n;
            if(nums[prevR] > nums[currR]){
                right = false;
            }
        }
        for(int i=1; i<n; i++){
            int prevL = (zero -i +1 +n)%n;
            int currL = (zero -i + n)%n;
            if(nums[prevL] > nums[currL]){
                left = false;
            }
        }
        int ans = Integer.MAX_VALUE;
        if(right){
            ans = Math.min(ans, zero);
            ans = Math.min(ans, n-zero + 2);
        }
        if(left){
            ans = Math.min(ans, zero+2);
            ans = Math.min(ans, n - zero);
        }
        if(ans != Integer.MAX_VALUE){
            return ans;
        }
        return -1;
    }
}