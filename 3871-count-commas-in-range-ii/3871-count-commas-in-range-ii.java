class Solution {
    public long countCommas(long n) {
        long ans = 0;
        long base = 1000;
        long c = 1;
        while(base <= n)
            {
                long next = base*1000-1;
                if(next > n)
                    next=n;
                ans += (next-base+1)*c;
                base = base*1000;
                c++;
            }
        return ans;
    }
}