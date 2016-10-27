package thread.starting.demo2;

/**
 * This class creates Thread by implementing Runnable interface
 */
class Runner implements Runnable {

    @Override
    public void run() {
        for (int i=0; i<10; i++){
            System.out.println("Hello " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

public class App {

    public static void main(String a[]) {
        Thread t1 = new Thread(new Runner());
        t1.start();

        Thread t2 = new Thread(new Runner());
        t2.start();

        Thread anonymous = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10; i++){
                    System.out.println("anonymous " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        anonymous.start();
    }
}
