package yichen

class Request{
    fun run(){}
}

// 限流器，每秒处理十个请求，使用基于请求的滑动窗口而不是自然时间的，比如请求开始处理的时间开始滑动
class RateLimiter(var requestBuffer: ArrayList<Request>) {

    var requestTime: ArrayList<Long> = ArrayList()

    fun receiveRequest(request: Request){
        this.requestBuffer.add(request)
    }

    fun processRequest(){
        while (true) {
            val currentTimeMills = System.currentTimeMillis()

            val waitTime = 1000 - currentTimeMills + requestTime.first()
            if (requestTime.size == 10 && waitTime > 0) {
                Thread.sleep(waitTime)
                continue
            }

            requestBuffer.removeFirst().run()

            requestTime.add(currentTimeMills)
            while (requestTime.size > 10) {
                requestTime.removeFirst()
            }
        }
    }
}