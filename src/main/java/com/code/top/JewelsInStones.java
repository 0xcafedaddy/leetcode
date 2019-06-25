package com.code.top;

/**
 * Created by kunYang on 2019/06/25.
 *
 *  给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 *
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jewels-and-stones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class JewelsInStones {


    public static void main(String[] args) {
        JewelsInStones jewelsInStones = new JewelsInStones();
        int count = jewelsInStones.numJewelsInStones2("aA","aAAbbbb");
        System.out.println(count);
    }


    public int numJewelsInStones(String J, String S) {

        char[] sArry = S.toCharArray();
        char[] jArry = J.toCharArray();
        int count = 0;

         for (int i = 0; i< sArry.length; i++){
             for ( int j =0; j < jArry.length; j++){
                 if (sArry[i] == jArry[j]){
                     count++;
                 }
             }
         }

        return count;
    }




    public int numJewelsInStones2(String J, String S) {
        int count = 0;
        for (int i =0; i< S.length(); i++){
            if(J.indexOf(S.charAt(i)) != -1){
                count++;
            }
        }
        return count;
    }


}
