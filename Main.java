class Main {
    static int x = 5, y;
    int z;
    {
        x = 5;
        z = 20;
    }
    static {
        y = x * 4;
        // z = y;
    }
    int s1() {
        return x * y;
    }
    static void s2() {
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        // System.out.println("z = " + z);
    }
    public static void main(String[] args) {
        // s1();
        System.out.println("x = " +x);
        // System.out.println("z = " + z);
        s2();
    }
}