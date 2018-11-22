import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Offer {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> arrayList = new ArrayList<>(1000000);
        while (listNode != null) {
            arrayList.add(listNode.val);
            listNode = listNode.next;
        }
        int[] arrayLists = new int[arrayList.size()];
        int size = arrayList.size();
        for (int i = 0; i < arrayList.size() / 2; i++) {
            int t = arrayList.get(i);
            arrayList.set(i, arrayList.get(size - i - 1));
            arrayList.set(size - i - 1, t);
        }
        return arrayList;
    }

    @Test
    public void test() {
        ListNode prelistNode = new ListNode();
        ListNode header = prelistNode;
        prelistNode.val = 1;
        for (int i = 0; i < 100000; i++) {
            ListNode listNode = new ListNode();
            listNode.val = i;
            prelistNode.next = listNode;
            listNode.next = null;
            prelistNode = listNode;
        }
        printListFromTailToHead(header);
    }


}
