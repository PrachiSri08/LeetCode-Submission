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
    public ListNode reverseList(ListNode newhead){
        ListNode curr = newhead;
        ListNode prev = null;
        ListNode front = newhead;
        while(curr != null){
            front = curr.next;
            curr.next = prev;
            prev = curr;
            curr = front;
        }
        return prev;
    }
    public boolean isPalindrome(ListNode head) {
        if(head.next == null){
            return true;
        }
        ListNode newHead = new ListNode(head.val);
        ListNode temp = head.next;
        ListNode node = newHead;
        while(temp != null){
            ListNode newNode = new ListNode(temp.val);
            node.next = newNode;
            node = node.next;
            temp = temp.next;
        }
        newHead = reverseList(newHead);
        temp = head;
        node = newHead;
        while(temp != null){
            if(temp.val != node.val){
                return false;
            }
            temp = temp.next;
            node = node.next;
        }
        return true;
    }
}