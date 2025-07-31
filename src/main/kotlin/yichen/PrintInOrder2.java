package yichen;

class PrintInOrder2 {

    private volatile boolean isFirstJobDone = false;
    private volatile boolean isSecondJobDone = false;

    private final Object lock = new Object();

    public PrintInOrder2() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (this.lock) {
            printFirst.run();
            isFirstJobDone = true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (this.lock) {
            while (!isFirstJobDone) {
                lock.wait();
            }
            printSecond.run();
            isSecondJobDone = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (this.lock) {
            while (!isSecondJobDone) {
                lock.wait();
            }
            printThird.run();
        }
    }
}
