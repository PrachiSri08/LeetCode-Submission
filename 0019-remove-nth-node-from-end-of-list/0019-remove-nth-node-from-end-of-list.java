/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null){
            return head;
        }
        int count = 0;
        ListNode temp = head;
        while(temp != null){
            count++;
            temp = temp.next;     
        }
        if(count == n){
            return head.next;
        }
        int idx = count - n;
        int cnt = 1;
        ListNode temp1 = head;
        ListNode temp2 = temp1.next;
        while(cnt < idx ){
            temp1 = temp1.next;
            cnt++;
            temp2 = temp1.next;
        }
        temp1.next = temp2.next;
        return head;
    }
}