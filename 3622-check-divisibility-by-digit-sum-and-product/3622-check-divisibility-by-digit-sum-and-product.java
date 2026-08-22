class Solution {
    public boolean checkDivisibility(int n) {
        int sum =0;
        int prod =1;
        int number = n;
        while(n>0){
            int num = n%10;
            sum+=num;
            prod = prod*num;
            n = n/10;
        }
        int totalsum= sum + prod;
        if(totalsum == 0){
            return false;
        }
        return number%totalsum == 0;
    }
}