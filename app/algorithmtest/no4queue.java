import java.util.Stack;

class no4queue {
    public static void main(String[] args) {
        Queue q = new Queue();
        q.enQueue(1);
        q.enQueue(2);
        q.enQueue(3);

        System.out.println(q.deQueue());
        System.out.println(q.deQueue());
        System.out.println(q.deQueue());
    }

    static class Queue {
        Stack<Integer> stackOne = new Stack<>();
        Stack<Integer> stackTwo = new Stack<>();

        void enQueue(int x) {
            while (!stackOne.isEmpty()) {
                stackTwo.push(stackOne.pop());
            }

            stackOne.push(x);

            while (!stackTwo.isEmpty()) {
                stackOne.push(stackTwo.pop());
            }
        }

        int deQueue() {
            if (stackOne.isEmpty()) {
                return 0;
            }

            int x = stackOne.peek();
            stackOne.pop();
            return x;
        }
    }


}