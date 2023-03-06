import java.util.Stack;

public class LC150_EvalReversePolish {
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String str: tokens) {
            if ('0' <= str.charAt(0) && str.charAt(0) <= '9') {
                stack.push(str);
            }
            else if (str.charAt(0) == '-' && str.length() > 1 && '0' <= str.charAt(1) && str.charAt(1) <= '9'){
                stack.push(str);
            }
            else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                char operator = str.charAt(0);
                int res;
                switch (operator) {
                    case '+':
                        res = num1 + num2;
                        stack.push(String.valueOf(res));
                        break;
                    case '-':
                        res = num1 - num2;
                        stack.push(String.valueOf(res));
                        break;
                    case '*':
                        res = num1 * num2;
                        stack.push(String.valueOf(res));
                        break;
                    case '/':
                        res = num1 / num2;
                        stack.push(String.valueOf(res));
                        break;
                }
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) {
        String[] str = {"4", "3", "-"};
        LC150_EvalReversePolish lc = new LC150_EvalReversePolish();
        System.out.println(lc.evalRPN(str));
    }
}
