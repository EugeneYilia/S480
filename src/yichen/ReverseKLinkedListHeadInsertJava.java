package yichen;

public class ReverseKLinkedListHeadInsertJava {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return head;
        if (k <= 1) return head;

        ListNode first = new ListNode(0);
        first.next = head;
        ListNode batchStart = first;

        while(true){
            ListNode lastNode = batchStart;
            for(int i =0;i<k;i++){
                lastNode = lastNode.next;
                if(lastNode == null) return first.next;
            }

            ListNode current = batchStart.next;
            ListNode next = current.next;

            for(int i=0;i<k-1;i++){
                current.next = next.next;
                next.next=batchStart.next;
                batchStart.next = next;
                next = current.next;
            }
            batchStart = current;
        }
    }
}
