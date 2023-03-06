import java.util.Stack;

public class LC232_QueueUsingStack {
    Stack<Integer> mainStack;
    Stack<Integer> extraStack;
    int top;
    public LC232_QueueUsingStack() {
        mainStack = new Stack<>();
        extraStack = new Stack<>();
    }

    public void push(int x) {
        if (mainStack.empty())
            top = x;
        mainStack.push(x);
    }

    public int pop() {
        int oldTop = 0; // assume the call is valid
        int newTop = 0;
        int ct = mainStack.size();
        while (!mainStack.empty()) {
            oldTop = mainStack.pop();
            if (ct == 2) newTop = oldTop;
            if (ct == 1) break;
            ct--;
            extraStack.push(oldTop);
        }
        top = newTop;

        while (!extraStack.empty())
            mainStack.push(extraStack.pop());

        return oldTop;
    }

    public int peek() {
        return top;
    }

    public boolean empty() {
        return mainStack.empty();
    }

    public static void main(String[] args) {
        LC232_QueueUsingStack queueUsingStack = new LC232_QueueUsingStack();
        queueUsingStack.push(1);
        queueUsingStack.push(2);
        System.out.println(queueUsingStack.peek());
        System.out.println(queueUsingStack.pop());
        System.out.println(queueUsingStack.empty());
    }
}
