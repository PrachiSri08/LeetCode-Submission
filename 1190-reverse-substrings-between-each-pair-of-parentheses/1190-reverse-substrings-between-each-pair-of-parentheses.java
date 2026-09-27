class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> st = new Stack<>();

        for(char ch : s.toCharArray()) {
            if(ch == ')') {
                StringBuilder temp = new StringBuilder();

                while(st.peek() != '(') {
                    temp.append(st.pop());
                }

                st.pop(); // remove '('

                for(char c : temp.toString().toCharArray()) {
                    st.push(c);
                }
            } else {
                st.push(ch);
            }
        }

        StringBuilder ans = new StringBuilder();

        while(!st.isEmpty()) {
            ans.append(st.pop());
        }

        return ans.reverse().toString();
    }
}