class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int [] arr = new int[n];
        int val = 0;

        HashMap hm = new HashMap();

        for (int i = 0; i< n; i++){
            if (!hm.containsKey(A[i])){
                hm.put(A[i],0);
            } else {
                val++;
                arr[i] = val;
            }

            if (!hm.containsKey(B[i])){
                hm.put(B[i],1);
            } else {
                val++;
                arr[i] = val;
            }
            arr[i] = val;
        }
        return arr;
    }
}