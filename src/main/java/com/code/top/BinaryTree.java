package com.code.top;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 *给定一个二叉树，返回它的中序 遍历。
 *
 * 在二叉树中，中序遍历首先遍历左子树，然后访问根结点，最后遍历右子树。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * Created by kunYang on 2019/06/30.
 */
public class BinaryTree {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }



    public List<Integer> inorderTraversal(TreeNode root) {

        // 1. 如果有左节点，就一直遍历下去，直到左节点为null
        // 2. 左节点为null, 就访问根节点
        // 3. 再访问右节点
        // 4. 以右节点为根，同上述操作

        List<Integer> res = new ArrayList<Integer>();
        helper(root, res);
        return res;
    }

    /**
     * 递归到最后，某个节点是一个 "没有左右节点的root节点"
     * @param root
     * @param res
     */
    public void helper(TreeNode root, List<Integer> res){

        if(root.left != null){
            helper(root.left , res);
        }

        res.add(root.val);

        if(root.right != null){
            helper(root.right , res);
        }

    }


    /**
     * 辅助，使用栈的解法
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(TreeNode root) {

        // 1. 如果有左节点，就一直遍历下去，直到左节点为null
        // 2. 左节点为null, 就访问根节点
        // 3. 再访问右节点
        // 4. 以右节点为根，同上述操作

        List<Integer> res = new ArrayList<Integer>();

        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root;


        // 如果不 判断栈 是否为空，那么在输出的时候就会少根节点的值，
        // 因为遍历到最后的一个左节点，下一个值是 左根节点的右节点，此时肯定为空。需要跳回到上一个根节点，再进行遍历
        while (curr != null  || !stack.isEmpty() ){

            // 一直添加左节点元素，并不是左节点的值
            while (curr != null){
                stack.push(curr);
                curr = curr.left;
            }

            // 左节点为空，获取当前节点(最左的节点。也是左右节点都为空的root节点)
            // 第一次：最左的节点，然后设置curr为right，也为空
            // 第二次：right也为空，那么上面的就执行不了，直接到这里弹出根节点
            // 第三次：如果根右点不为空，则遍历右边的。如果右边为空，则返回到上一个根节点
            curr = stack.pop();
            res.add(curr.val);

            curr = curr.right;

        }

        return res;

    }





}
