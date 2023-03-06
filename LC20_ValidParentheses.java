import java.util.Stack;

public class LC20_ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == '(' || cur == '[' || cur == '{')
                stack.push(cur);
            else {
                if (stack.empty()) return false;
                char last = stack.pop();
                switch (last) {
                    case '(':
                        if (cur != ')') return false;
                        break;
                    case '[':
                        if (cur != ']') return false;
                        break;
                    case '{':
                        if (cur != '}') return false;
                        break;
                }
            }
        }

        return stack.empty();
    }
}
