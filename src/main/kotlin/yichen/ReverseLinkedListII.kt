package yichen

class ListNode(var `val`: Int){
    var next: ListNode? = null
}

// 1->2->3->4->5
// left=2 right=4
// 1->4->3->2->5
fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
    if(head == null || left >= right) return head

    var edenNode = ListNode(-1)
    edenNode.next = head

    var brokenMainStart: ListNode? = null
    var brokenMainEnd: ListNode? = null
    var brokenStart: ListNode? = null
    var brokenEnd: ListNode? = null

    var currentNode: ListNode? = edenNode
    repeat(left - 1){
        currentNode = currentNode?.next
        if(currentNode == null){
            return head
        }
    }

    brokenMainStart = currentNode
    brokenStart = currentNode?.next
    if(brokenStart == null) {return head}
    currentNode = brokenStart

    repeat(right - left){
        currentNode = currentNode?.next
        if(currentNode == null){
            return head
        }
    }

    brokenEnd = currentNode
    brokenMainEnd = currentNode?.next

    var previousNode = brokenStart
    currentNode = previousNode.next

    while(true){
        if(currentNode == null){
            break
        }

        if(currentNode == brokenEnd){
            currentNode.next = previousNode
            break
        }

        var nextNode = currentNode.next
        currentNode.next = previousNode
        previousNode = currentNode
        currentNode = nextNode
    }

    brokenMainStart!!.next = brokenEnd
    brokenStart.next = brokenMainEnd

    return edenNode.next
}