package com.code.top;


import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 *
 * 此题目中有个局限，不能出现重复的元素 比如 3 + 3 = 6;
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class TowNumbersSum {


    public static void main(String[] args) {

    }


    /**
     * 解法1:
     *  最简单直接的解法。时间复杂度最高
     *  第一次循环一次数组，获取当前的数组下标的的值。然后再依次获取到此数组下标之后的所有的所有元素，和此元素相加，判断是否等于目标值，相等即退出。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {

        int[] res = new int[2];

        for (int i =0; i< nums.length; i++){
            int first_item = nums[i];

            for (int j = i+1; j< nums.length; j++){
                int next_item = nums[j];

                if( first_item + next_item == target){
                  res[0] = i;
                  res[1] = j;
                  return res;
                }
            }
        }

        return null;
    }


    /**
     * 解法2：
     *  使用两次循环，第一次按照 [元素-下标] 的形式存入map。
     *  第二次循环一次数组，用target - 循环元素 = 差值。
     *  再通过 map.getkey 的方式去查找 是否存在这个差值，如果存在 即退出循环。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {

        int[] res = new int[2];

        Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        for (int i =0; i < nums.length; i++){
            hashMap.put(nums[i], i);
        }


        for (int i =0; i< nums.length; i++){
            int sub = target - nums[i];
            // [3,2,4]
            if (hashMap.containsKey(sub)  && hashMap.get(sub) != i){
                res[0] = i;
                res[1] = hashMap.get(sub);

                return res;
            }
        }

        return null;
    }


    /***
     * 解法3：
     *  使用一次循环，在循环数组的同时将元素添加进map集合。
     *  在每次添加元素之前，先进行 差值 判断，是否存在在map中
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target) {

        int[] res = new int[2];
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        for (int i =0; i< nums.length; i++){
            int sub = target - nums[i];

            if (hashMap.containsKey(sub) && hashMap.get(sub) != i ){
                res[0] = i;
                res[1] = hashMap.get(sub);
                return res;
            }

            hashMap.put(nums[i], i);
        }

        return null;
    }


    /***
     * 这种解法最快
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum4(int[] nums, int target) {
        int indexArrayMax = 2047;
        int[] indexArrays = new int[indexArrayMax + 1];
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            int index = diff & indexArrayMax;
            if (indexArrays[index] != 0) {
                return new int[] { indexArrays[index] - 1, i };
            }
            indexArrays[nums[i] & indexArrayMax] = i + 1;
        }
        throw new IllegalArgumentException("No two sum value");
    }

}
