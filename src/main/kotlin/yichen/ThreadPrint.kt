package yichen

import java.util.concurrent.atomic.AtomicInteger

fun main() {
    Thread(Data(2, "C")).start()
    Thread(Data(1, "B")).start()
    Thread(Data(0, "A")).start()
}

class Data : Runnable {
    var priority: Int = 0
    var data: String = ""

    constructor(
        priority: Int,
        data: String
    ) {
        this.data = data
        this.priority = priority
    }

    companion object StaticData {
        var count = AtomicInteger(0)
    }

    override fun run() {
        while (true) {
            if (priority == count.get()) {
                print(data)
                count.incrementAndGet()
                return
            }
        }
    }
}
