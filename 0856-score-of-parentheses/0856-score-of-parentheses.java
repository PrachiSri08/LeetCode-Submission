class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                st.push(0);
            }
            else if(s.charAt(i) == ')'){
                int x = st.pop();
                if(x == 0){
                    x =1;
                }
                else{
                    x = 2*x;
                }
                st.push(st.pop()+x);
            }
        }
        return st.pop();
    }
}