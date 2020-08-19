import java.util.Stack;

class no3Stack {
    public static void main(String[] args) {
        StackMaxInteger s = new StackMaxInteger();
        s.push(3);
        s.push(1);
        s.push(5);
        s.pop();
        s.push(790);
        s.push(5);
        s.pop();

        System.out.println(s.get());
    }

    static class StackMaxInteger {
        Stack<Integer> stack = new Stack<>();
        int max = 0;

        int get() {
            if (stack.empty()) {
                return -1;
            }
            return max;

        }

        int peek() {
            if (stack.empty()) {
                return -1;
            }

            int p = stack.peek();
            if (p > max) {
                return max;
            }
            return p;
        }

        void pop() {
            if (stack.empty()) {
                return;
            }
            System.out.println(stack);
            int p = stack.peek();
            stack.pop();

            if (p > max) {
                max = max * 2 - p;
            }
        }

        void push(int v) {
            if (stack.empty()) {
                max = v;
                stack.push(v);
                return;
            }
            if (v > max) {
                stack.push(v * 2 - max);
                max = v;
            } else {
                stack.push(v);
            }
        }
    }
}