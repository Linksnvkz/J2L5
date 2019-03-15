public class Main {
    static final int SIZE = 10000000;
    static final int H = SIZE / 2;
    static float[] arr = new float[SIZE];
    static float[] a1 = new float[H];
    static float[] a2 = new float[H];

    public static void main(String[] args) {
        meth1();
        meth2();
    }

    static void meth1() {

        for (float i:arr) {
            arr[(int) i] = 1;
        }

        long startTime = System.currentTimeMillis();

        for (float i:arr) {
            arr[(int) i] = (float)(arr[(int) i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long stopTime = System.currentTimeMillis();
        long a = stopTime - startTime;
        System.out.println(a);
    }

    static void meth2() {

        for (float i:arr) {
            arr[(int) i] = 1;
        }

        long startTime = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, H);
        System.arraycopy(arr, H, a2, 0, H);
        new Thread(new MyRunnableClass1()).start();
        new Thread(new MyRunnableClass2()).start();
        System.arraycopy(a1, 0, arr, 0, H);
        System.arraycopy(a2, 0, arr, H, H);
        long stopTime = System.currentTimeMillis();
        long a = stopTime - startTime;
        System.out.println(a);
    }

    static class MyRunnableClass1 implements Runnable {
        @Override
        public void run() {
            for (float i:a1) {
                a1[(int) i] = 1;
                a1[(int) i] = (float)(a1[(int) i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
    }

    static class MyRunnableClass2 implements Runnable {
        @Override
        public void run() {
            for (float i:a2) {
                a2[(int) i] = 1;
                a2[(int) i] = (float)(a2[(int) i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
    }
}
