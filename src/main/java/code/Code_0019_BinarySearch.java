package code; 

public class Code_0019_BinarySearch {
    
    // 二分查找某个元素是否存在 
    // TODO 补对数器
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
