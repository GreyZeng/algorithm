package code; 
// TODO
//1. 在一个有序数组中，找某个数是否存在
//2. 在一个有序数组中，找>=某个数的最左位置
//3. 在一个有序数组中，找<=某个数的最右位置
//4. 局部最小值问题
public class Code_0019_BinarySearch {
    
    // 二分查找某个元素是否存在  
    public static boolean exist(int[] sortedArr, int num){
        if(null == sortedArr || sortedArr.length == 0) {
            return false;
        }
        int N = sortedArr.length;
        int L = 0;
        int R = N - 1;
        int M = 0;
        while(L < R) {
            M = L + ((R - L) >> 1);
            if (sortedArr[M] == num) {
                return true;
            } else if (sortedArr[M] > num) {
                R = M - 1;
            } else {
                L = M + 1;
            }
        }
        return sortedArr[L] == num;
    }
    

    
}
