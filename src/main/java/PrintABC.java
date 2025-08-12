import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABC {
    static ReentrantLock lock = new ReentrantLock();
    static Condition aCondition = lock.newCondition();
    static Condition bCondition = lock.newCondition();
    static Condition cCondition = lock.newCondition();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                lock.lock();
                while (true) {
                    cCondition.await();
                    System.out.print("C");
                    aCondition.signal();
                }
            } catch (InterruptedException _) {
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                while (true) {
                    bCondition.await();
                    System.out.print("B");
                    cCondition.signal();
                }
            } catch (InterruptedException _) {
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                while (true) {
                    System.out.print("A");
                    bCondition.signal();
                    aCondition.await();
                }
            } catch (InterruptedException _) {
            }
        }).start();
    }
}
