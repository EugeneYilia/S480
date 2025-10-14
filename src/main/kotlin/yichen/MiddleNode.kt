package yichen

fun middleNode(head: ListNode?): ListNode? {
    head ?: return head

    var doubleNode = head
    var singleNode = head

    while(doubleNode!=null && doubleNode.next!=null){
        doubleNode = doubleNode.next!!.next
        singleNode = singleNode!!.next
    }

    return singleNode
}