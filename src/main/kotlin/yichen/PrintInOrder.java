package yichen;

import java.util.concurrent.atomic.AtomicInteger;

class Foo {

    AtomicInteger count = new AtomicInteger(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.

        while (true) {
            if (0 == count.get()) {
                printFirst.run();
                count.incrementAndGet();
                return;
            }
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.


        while (true) {
            if (1 == count.get()) {
                printSecond.run();
                count.incrementAndGet();
                return;
            }
        }
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.

        while (true) {
            if (2 == count.get()) {
                printThird.run();
                count.incrementAndGet();
                return;
            }
        }
    }
}
