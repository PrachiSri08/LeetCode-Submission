class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int left = 0;
        int right = 0;
        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                left++;
            }
            else if(ch == ')') {
                if(left > 0)
                    left--;
                else
                    right++;
            }
        }
        Set<String> ans = new HashSet<>();
        solve(s, 0, left, right, 0, new StringBuilder(), ans);
        return new ArrayList<>(ans);
    }
    private void solve(String s, int index, int left, int right,
                       int balance, StringBuilder curr,
                       Set<String> ans) {
        if(index == s.length()) {
            if(left == 0 && right == 0 && balance == 0) {
                ans.add(curr.toString());
            }
            return;
        }
        char ch = s.charAt(index);
        if(ch == '(' && left > 0) {
            solve(s, index + 1, left - 1, right,
                  balance, curr, ans);
        }
        if(ch == ')' && right > 0) {
            solve(s, index + 1, left, right - 1,
                  balance, curr, ans);
        }
        curr.append(ch);
        if(ch == '(') {
            solve(s, index + 1, left, right,
                  balance + 1, curr, ans);
        }
        else if(ch == ')') {
            if(balance > 0) {
                solve(s, index + 1, left, right,
                      balance - 1, curr, ans);
            }
        }
        else {
            solve(s, index + 1, left, right,
                  balance, curr, ans);
        }
        curr.deleteCharAt(curr.length() - 1);
    }
}