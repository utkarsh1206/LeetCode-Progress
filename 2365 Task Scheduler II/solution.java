class Solution {
    public long taskSchedulerII(int[] tasks, int space) {
        long n = tasks.length;
        HashMap<Integer, Long> hm = new HashMap<>();
        long count = 0;
        for (int i = 0; i < n; i++) {
            if (!hm.containsKey(tasks[i])) {
                count++;
                hm.put(tasks[i], count);
            } else {
                long last = hm.get(tasks[i]);
                long expected = last + space + 1;
                if (count < expected) {
                    count = expected;
                } else if (count >= expected) {
                    count++;
                }
                hm.put(tasks[i], count);
            }
        }
        return count;
    }
}