/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1=1;
        int len2 =1;
        ListNode temp1 = headA;
        ListNode temp2 = headB;
        while(temp1.next != null){
            temp1 = temp1.next;
            len1++;
        }
        while(temp2.next != null){
            temp2 = temp2.next;
            len2++;
        }
        temp1 = headA;
        temp2 = headB;
        if(len1 >= len2){
            while(len1 != len2){
                len1--;
                temp1 = temp1.next;
            }
            while(temp1 != temp2){
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            return temp1;
        }
        else{
            while(len1 != len2){
                len2--;
                temp2 = temp2.next;
            }
            while(temp1 != temp2){
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
            return temp1;
        }
        //return temp1;
    }
}