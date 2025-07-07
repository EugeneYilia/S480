package yichen

class ReverseKLinkedList {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (head == null){
            return head
        }

        if (k <= 1){
            return head
        }

        var first = head
        var batchFirst = head
        var isFirst = true
        var last: ListNode? = null

        while (true){
            var currentLast: ListNode? = batchFirst

            repeat(k - 1){
                currentLast = currentLast?.next
                if(currentLast == null){
                    return first
                }
            }

            if(isFirst){
                isFirst = false
                first = currentLast
            }

            if(last != null){
                last.next = currentLast
            }

            var current = batchFirst
            var next = current!!.next
            // k=2  repeat 1
            repeat(k - 1){
                val oldNode = current
                current = next
                next = current?.next
                current!!.next = oldNode
            }

            batchFirst!!.next = next
            last = batchFirst
            batchFirst = next
        }
    }
}