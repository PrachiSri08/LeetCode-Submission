class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        int maxdiff = 0;
        long total =0;
        long k = k1+k2;
        for(int i=0; i<n; i++){
            diff[i] = Math.abs(nums1[i]-nums2[i]);
            maxdiff = Math.max(maxdiff, diff[i]);
            total += diff[i];
        }
        if(k >= total){
            return 0;
        }
        int low =0;
        int high =maxdiff;
        
        while(low < high){
            int mid = low + (high - low)/2;
            long oprn=0;
            for(int i=0; i<n; i++){
               if(diff[i] > mid){
                oprn += diff[i]-mid;
               }
            }
            if(oprn > k){
                low = mid+1;
            }
            else{
                high = mid;
            }
        }
        int limit = low;
        long oprn = 0;
        long ans=0;
        for(int i=0; i<n; i++){
            if(diff[i]>limit){
                oprn += diff[i]-limit;
            }
            long val = Math.min(diff[i], limit);
            ans +=val*val;
        }
        long rem = k - oprn;
        ans -= rem *(2L * limit -1);
        return ans;
    }
}