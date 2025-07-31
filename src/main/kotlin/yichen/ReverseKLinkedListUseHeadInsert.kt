package yichen

class ReverseKLinkedListUseHeadInsert {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if (head == null) {
            return head
        }

        if (k <= 1) {
            return head
        }

        var first = ListNode(0).apply { next = head }
        var batchFirst = first

        while (true) {
            var currentLast : ListNode? = batchFirst

            repeat(k) {
                currentLast = currentLast?.next
                if (currentLast == null) {
                    return first.next
                }
            }

            var curr = batchFirst.next
            var next = curr!!.next

            repeat(k-1){
                curr.next = next!!.next
                next.next = batchFirst.next
                batchFirst.next = next
                next = curr.next
            }

            batchFirst = curr
        }
    }
}