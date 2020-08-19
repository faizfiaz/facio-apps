import java.util.HashSet;

class no2 {
    public static void main(String[] args) {
        int[] a = {2, 45, 7, 3, 5, 1, 8, 9};
        printSumPairs(a, 15);
    }

    public static void printSumPairs(int[] array, int sum) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int num : array) {
            if (set.contains(sum - num)) {
                String s = num + ", " + (sum - num);
                System.out.println(s);
            }
            set.add(num);
        }
    }
}