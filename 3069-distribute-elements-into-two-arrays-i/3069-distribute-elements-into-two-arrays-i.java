class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] arr1 = new int[n];
        int j=0;
        arr1[j] = nums[0];
        int[] arr2 = new int[n];
        int k=0;
        arr2[k] = nums[1];
        for(int i=2; i<n; i++){
            if(arr1[j] > arr2[k]){
                arr1[++j]=nums[i];
            }
            else{
                arr2[++k] = nums[i];
            }
        }
        int[] res = new int[n];
        int idx =0;
        for(int i=0; i<=j; i++){
            res[idx++] = arr1[i];
        }
        for(int i=0; i<=k; i++){
            res[idx++] = arr2[i];
        }
        return res;
    }
}