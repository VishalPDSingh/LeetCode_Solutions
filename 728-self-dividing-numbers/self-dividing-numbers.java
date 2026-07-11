class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {

        List<Integer> ans = new ArrayList<>();

        for (int i = left; i <= right; i++) {

            int num = i;
            boolean selfDividing = true;

            while (num > 0) {
                int digit = num % 10;

                if (digit == 0 || i % digit != 0) {
                    selfDividing = false;
                    break;
                }

                num /= 10;
            }

            if (selfDividing) {
                ans.add(i);
            }
        }

        return ans;
    }
}