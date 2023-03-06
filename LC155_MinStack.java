import java.util.*;

public class LC155_MinStack {
    Stack<MinStackNode> stack;
    int min;
    public LC155_MinStack() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int val) {
        min = Math.min(min, val);
        stack.push(new MinStackNode(val, min));
    }

    public void pop() {
        stack.pop();
        if (stack.isEmpty()) min = Integer.MAX_VALUE;
        else min = stack.peek().getMin();
    }

    public int top() {
        return stack.peek().getVal();
    }

    public int getMin() {
        return stack.peek().getMin();
    }

    protected static class MinStackNode {
        private final int val;
        // min until this node
        private final int min;

        public MinStackNode(int val, int min) {
            this.val = val;
            this.min = min;
        }

        public int getMin() {
            return min;
        }

        public int getVal() {
            return val;
        }
    }
}
