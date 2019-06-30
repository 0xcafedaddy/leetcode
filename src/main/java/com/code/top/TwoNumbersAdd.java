package com.code.top;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */




public class TwoNumbersAdd {



    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }




    public ListNode test0630(ListNode l1, ListNode l2){

        int carry = 0; // 进位

        ListNode head = new ListNode(-1);
        ListNode currentNode = head;  // 需要一个中间的游标节点来指向当前的最后一个链表节点的位置
        while (l1 != null || l2 != null){

            int sum = carry;

            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }

            if(l2 !=null){
                sum += l2.val;
                l2 = l2.next;
            }

            int current = sum % 10;
            carry = sum / 10 ;

            ListNode node = new ListNode(current);

            currentNode.next = node;
            currentNode = node;

        }

        if(carry != 0){
            currentNode.next = new ListNode(carry);
        }

        return head.next;
    }





    /**
     * 最简单的解法：
     *  遍历两个链表，得到数字
     *  然后相加后将数字再转成链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        StringBuffer num1Str = new StringBuffer();
        StringBuffer num2Str = new StringBuffer();


        ListNode l1Next = l1;
        while(l1Next != null){
            num1Str.append(l1Next.val);
            l1Next = l1Next.next;
        }


        ListNode l2Next = l2;

        while(l2Next != null){
            num2Str.append(l2Next.val);
            l2Next = l2Next.next;
        }

        Integer num1 = Integer.valueOf(num1Str.reverse().toString());
        Integer num2 = Integer.valueOf(num2Str.reverse().toString());

        Integer num3 = num1 + num2;

        String num3Str = num3.toString();

        System.out.println(num3Str);

        List<ListNode> array = new ArrayList<ListNode>();



        int i = num3Str.length() -1;

        while( i >= 0){
            int val = Integer.valueOf(String.valueOf(num3Str.charAt(i)));
            ListNode node  = new ListNode(val);
            if( i == 0){
                node.next = null;
            }else{
                val = Integer.valueOf(String.valueOf(num3Str.charAt(i-1)));
                node.next = new ListNode(val);
            }
            i -= 1;
            array.add(node);
        }

        return array.get(0);
    }


    /**
     * Java 8ms 解法 Scott
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode(-1);
        ListNode current = head;
        while (l1 != null || l2 != null) {
            int number = carry;
            if (l1 != null) {
                number += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                number += l2.val;
                l2 = l2.next;
            }

            carry = number / 10;
            int remainder = number % 10;
            ListNode node = new ListNode(remainder);
            current.next = node;
            current = current.next;
        }
        if (carry != 0) {
            current.next = new ListNode(carry);
        }
        return head.next;
    }


    /**
     * 看过答案后做的一个版本
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(-1);
        ListNode currentNode = head;

        ListNode p = l1;
        ListNode q = l2;

        // 节点进位值
        int carry = 0;

        while ( p != null || q != null ){       // 两个链表长度可能不一致

            int sum = carry;

            if(p != null){
                sum += p.val;
                p = p.next;
            }

            if(q != null){
                sum += q.val;
                q = q.next;
            }

            // 节点当前位置的值
            int current = sum % 10;
            carry = sum / 10;

            ListNode node = new ListNode(current);

            // 先将此结点指向当前的最后一个位置
            // 将这两个点同时指向新产生的结点
            // 将游标结点再指向新产生的结点
            currentNode.next = node;
//            currentNode = node;
            currentNode = currentNode.next;
        }

        // 最后的进位
        if(carry > 0){
            currentNode.next = new ListNode(carry);
        }

        return head.next;
    }




    /**
     * 官方解法：
     * 就像你在纸上计算两个数字的和那样，我们首先从最低有效位也就是列表 l1l1 和 l2l2 的表头开始相加。由于每位数字都应当处于 0 \ldots 90…9 的范围内，我们计算两个数字的和时可能会出现 “溢出”。例如，5 + 7 = 125+7=12。在这种情况下，我们会将当前位的数值设置为 22，并将进位 carry = 1carry=1 带入下一次迭代。进位 carrycarry 必定是 00 或 11，这是因为两个数字相加（考虑到进位）可能出现的最大和为 9 + 9 + 1 = 199+9+1=19。
     *
     * 伪代码如下：
     *
     * 将当前结点初始化为返回列表的哑结点。
     * 将进位 carrycarry 初始化为 00。
     * 将 pp 和 qq 分别初始化为列表 l1l1 和 l2l2 的头部。
     * 遍历列表 l1l1 和 l2l2 直至到达它们的尾端。
     * 将 xx 设为结点 pp 的值。如果 pp 已经到达 l1l1 的末尾，则将其值设置为 00。
     * 将 yy 设为结点 qq 的值。如果 qq 已经到达 l2l2 的末尾，则将其值设置为 00。
     * 设定 sum = x + y + carrysum=x+y+carry。
     * 更新进位的值，carry = sum / 10carry=sum/10。
     * 创建一个数值为 (sum \bmod 10)(summod10) 的新结点，并将其设置为当前结点的下一个结点，然后将当前结点前进到下一个结点。
     * 同时，将 pp 和 qq 前进到下一个结点。
     * 检查 carry = 1carry=1 是否成立，如果成立，则向返回列表追加一个含有数字 11 的新结点。
     * 返回哑结点的下一个结点。
     * 请注意，我们使用哑结点来简化代码。如果没有哑结点，则必须编写额外的条件语句来初始化表头的值。
     *
     * 请特别注意以下情况：
     *
     * 测试用例	说明
     * l1=[0,1]l1=[0,1]，l2=[0,1,2]l2=[0,1,2]	当一个列表比另一个列表长时
     * l1=[]l1=[]，l2=[0,1]l2=[0,1]	当一个列表为空时，即出现空列表
     * l1=[9,9]l1=[9,9]，l2=[1]l2=[1]	求和运算最后可能出现额外的进位，这一点很容易被遗忘
     *
     *
     * 复杂度分析
     *
     * 时间复杂度：O(\max(m, n))O(max(m,n))，假设 mm 和 nn 分别表示 l1l1 和 l2l2 的长度，上面的算法最多重复 \max(m, n)max(m,n) 次。
     *
     * 空间复杂度：O(\max(m, n))O(max(m,n))， 新列表的长度最多为 \max(m,n) + 1max(m,n)+1。
     *
     * 拓展
     *
     * 如果链表中的数字不是按逆序存储的呢？例如：
     *
     * (3 \to 4 \to 2) + (4 \to 6 \to 5) = 8 \to 0 \to 7
     * (3→4→2)+(4→6→5)=8→0→7
     *
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/two-sum/solution/liang-shu-xiang-jia-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }





    public static void main(String[] args) {
        TwoNumbersAdd add = new TwoNumbersAdd();
        ListNode l1 = new ListNode(1);
        ListNode l11 = new ListNode(2);
        l1.next = l11;

        ListNode l2 = new ListNode(1);
        ListNode l22 = new ListNode(1);
        l2.next = l22;

        ListNode listNode = add.addTwoNumbers3(l1, l2);

        while(listNode != null){
            System.out.printf(listNode.val + " --> ");
            listNode = listNode.next;
        }


        // 如果链表无限长，就会执行失败

//        Line 31: java.lang.NumberFormatException: For input string: "9999999991"
//        [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]
//        [5,6,4]
    }


}
