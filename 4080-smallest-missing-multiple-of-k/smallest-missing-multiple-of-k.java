class Solution {
    public int missingMultiple(int[] arr, int k) {

        int multiple = k;

        while (true) {

            boolean found = false;

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == multiple) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return multiple;
            }

            multiple += k;
        }
    }
}