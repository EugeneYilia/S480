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

        while (true){
            val first = head

            var last: ListNode? = first;

            repeat(k - 1){
                last = last?.next
                if(last == null){
                    return@repeat
                }
            }


        }
    }
}