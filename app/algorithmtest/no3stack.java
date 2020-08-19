import java.util.Stack;

class no3Stack {
    public static void main(String[] args) {
        StackMaxInteger s = new StackMaxInteger();
        s.push(1);
        s.push(10);
        s.push(1200);
        s.push(131);
        s.push(12);
        s.push(12001);
        System.out.println(s.get());
    }

    static class StackMaxInteger {
        Stack<Integer> stack = new Stack<>();
        int max;

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
            int p = stack.peek();
            stack.pop();

            if (p > max) {
                max = p;
            }
        }

        void push(int v) {
            if (stack.empty()) {
                max = v;
                stack.push(v);
                return;
            }
            if(v > max) {
                stack.push(v);
                max = v;
            } else {
                stack.push(v);
            }
        }
    }
}