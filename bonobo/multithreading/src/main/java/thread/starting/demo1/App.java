package thread.starting.demo1;

/**
 * This class creates threads by extending Thread class
 */
class Runner extends Thread {

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
        Runner t1 = new Runner();
        t1.start();

        Runner t2 = new Runner();
        t2.start();
    }
}
