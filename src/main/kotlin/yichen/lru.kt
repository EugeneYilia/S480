package yichen

fun main() {
    val cache = LRUCache(2)
    cache.put(1, 1) // cache is {1=1}
    cache.put(2, 2) // cache is {1=1, 2=2}
    println(cache.get(1))    // return 1
    cache.put(3, 3) // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    println(cache.get(2))    // returns -1 (not found)
    cache.put(4, 4) // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    println(cache.get(1))    // return -1 (not found)
    println(cache.get(3))    // return 3
    println(cache.get(4))    // return 4
}

class CacheNode() {
    var key: Int = 0
    var value: Int = 0
    var previous: CacheNode? = null
    var next: CacheNode? = null
}

class LRUCache(capacity: Int) {
    var cacheSize: Int = 0
    var cache: HashMap<Int, CacheNode> = HashMap()
    var first: CacheNode = CacheNode()
    var last: CacheNode = CacheNode()

    init {
        this.cacheSize = capacity
        first.next = last
        last.previous = first
    }

    fun put(key: Int, value: Int) {
        val node = cache.get(key)
        if (node != null) {
            node.value = value

            node.previous!!.next = node.next
            node.next!!.previous = node.previous

            last.previous!!.next = node
            node.previous = last.previous
            node.next = last
            last.previous = node
        } else {
            val newCacheNode = CacheNode()
            newCacheNode.key = key
            newCacheNode.value = value
            cache.put(key, newCacheNode)

            last.previous!!.next = newCacheNode
            newCacheNode.previous = last.previous
            newCacheNode.next = last
            last.previous = newCacheNode

            if (cache.size > cacheSize) {
                val removeCache = first.next
                cache.remove(removeCache!!.key)

                first.next = removeCache.next
                removeCache.next!!.previous = first
            }
        }
    }

    fun get(key: Int): Int {
        val node = cache.get(key)
        if (node == null) {
            return -1
        } else {
            node.previous!!.next = node.next
            node.next!!.previous = node.previous

            last.previous!!.next = node
            node.previous = last.previous
            node.next = last
            last.previous = node
            return node.value
        }
    }
}