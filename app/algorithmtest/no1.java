class no1 {
    public static void main(String[ ] args) {
        System.out.println("Hello Java");

        int a[] = { 5, 4, 1, 2, 6,7 };

        int miss = getMissing(a, a.length);

        System.out.println(miss);
    }

    static int getMissing(int a[],int n){

        int i, total;

        total = (n + 1) * (n + 2) / 2;

        for (i = 0; i < n; i++)

            total -= a[i];

        return total;
    }
}