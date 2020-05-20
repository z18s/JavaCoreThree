package lesson_4;

public class HomeworkFour {

    private static final Object obj = new Object();

    private static final char[] sequence = "ABC".toCharArray();
    private static volatile char sym = sequence[0];

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                print('A');
            }
        }, "Thread 1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                print('B');
            }
        }, "Thread 2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                print('C');
            }
        }, "Thread 3").start();
    }

    public static void print(char c) {
        char nextC;

        int index = -1;
        for (int i = 0; i < sequence.length; i++) {
            if (c == sequence[i]) {
                index = i;
                break;
            }
        }
        nextC = sequence[((index == sequence.length - 1) ? 0 : index + 1)];

        for (int i = 0; i < 5; i++) {
            synchronized (obj) {
                try {
                    while (sym != c) {
                        obj.wait();
                    }
                    System.out.print(c);
                    sym = nextC;
                    obj.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}