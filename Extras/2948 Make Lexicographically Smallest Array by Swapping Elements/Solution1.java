class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] res = new int[n];
        ArrayList<int[]> valIndex = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            valIndex.add(new int[] { nums[i], i });
        }

        Collections.sort(valIndex, (a, b) -> a[0] - b[0]);

        int l = 0;
        int index = 0;
        while (l < n - 1) {
            // for (int l = 0; l<n-1; l++){
            // int [][] arr = new int[n][2];
            ArrayList<int[]> arr = new ArrayList<>();
            // int i = 1;
            // arr[0][0] = valIndex.get(l)[0];
            // arr[0][1] = valIndex.get(l)[1];
            arr.add(valIndex.get(l));

            boolean found = false;

            for (int r = l; r < n - 1; r++) {
                if (Math.abs(valIndex.get(r)[0] - valIndex.get(r + 1)[0]) <= limit) {
                    // arr[i][0] = valIndex.get(r+1)[0];
                    // arr[i][1] = valIndex.get(r+1)[1];
                    // i++;
                    arr.add(valIndex.get(r + 1));
                }
                // else {
                // l = r+1;
                // break;
                // }

                else {
                    l = r + 1;
                    found = true;
                    break;
                }

            }

            if (!found) {
                l++;
            }

            // int val = 0;
            // while (arr.size() > val){
            // int value = arr.get(val)[0];
            // int index = arr.get(val)[1];
            // res[val] = value;
            // // System.out.println("res" + Arrays.toString(res));
            // val++;
            // }

            for (int[] item : arr) {
                res[index++] = item[0];
            }
            // int test = 0;
            // while(arr.size() > test){
            // System.out.println("arr " + Arrays.toString(arr.get(test)));
            // test++;
            // }
        }

        return res;
    }

}