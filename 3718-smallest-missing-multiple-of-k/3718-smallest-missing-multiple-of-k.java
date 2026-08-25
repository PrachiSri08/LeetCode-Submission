class Solution {
    public int missingMultiple(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++){
            map.put(i+1, nums[i]);
        }
        for(int i=1; i<=n+1; i++){
            if(!map.containsValue(k*i))
            return k*i;
        }
        return k;
    }
}