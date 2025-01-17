class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        int result = 0;
        for (int i = 0; i < derived.length; i++) {
            result ^= derived[i];
        }
        return (result == 0);
    }
}