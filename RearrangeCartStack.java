import java.util.Stack;

// Adapted from Problem 9.5.3 Rearranging Railroad cars

/**
 * Since push and pop in stack are reverse operation <br>
 * <b>Let f = [push, push, pop, push, pass, pop, pop] </b><br>
 * let A = 1,2,3,4; while B = 2,4,3,1; <br>
 * then f(A) = B <br>
 * Consider f' which is the <b>inverse</b> of f,
 * f' acts on the tail of B, fill A stating from tail of A <br>
 * <b>f' = [push, push, pass, pop, push, pop, pop]</b> <br>
 * f'(B) = A
 */
public class RearrangeCartStack {
    private static Stack<Integer>[] stackArr;
    private static int numOfStacks;
    private static int numOfCarts;

    public RearrangeCartStack(int cart, int stack) {
        numOfCarts = cart;
        numOfStacks = stack;
        stackArr = (Stack<Integer>[]) new Object[numOfStacks];
    }

    // from 54321 to 12345
    public static boolean rearrange(int[] input) {
        for (int i = 0; i < stackArr.length; i++) {
            stackArr[i] = new Stack<>();
        }

        int targetPtr = 0;

        for (int cartNo = 1; cartNo <= numOfCarts; cartNo++) {
            // no more input carts, all carts are in output track or stacks
            if (targetPtr >= numOfCarts) {
                if (!checkStack(cartNo)) return false;
                else continue;
            }

            int cur = input[targetPtr];
            // matched -> directly pass
            if (cartNo == cur) {
                continue;
            }

            // already in stack
            if (checkStack(cartNo)) continue;

            // need to put aside
            boolean inTrack = false;
            for (Stack<Integer> stack: stackArr) {
                if (cur < cartNo) {
                    stack.push(cur);
                    inTrack = true;
                    break;
                }
            }
            if (!inTrack) return false;

            targetPtr++;
        }
        return true;
    }

    /**
     * Check whether cartNo is in stackArr, if yes, pop cartNo from that stack
     * @param cartNo target number
     * @return true if cartNo is in stackArr, otherwise false
     */
    private static boolean checkStack(int cartNo) {
        boolean out = false;
        for (Stack<Integer> stack: stackArr) {
            if (!stack.isEmpty() && cartNo == stack.peek()) {
                stack.pop();
                out = true;
                break;
            }
        }
        return out;
    }

    public static void main(String[] args) {
        RearrangeCartStack re = new RearrangeCartStack(5, 1);
        if (rearrange(new int[]{5,4,3,2,1})) {
            System.out.println("success");
        }
    }
}
