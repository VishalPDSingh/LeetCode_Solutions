class Solution {
    public List<Boolean> kidsWithCandies(int[] arr, int extraCandies) {
         List<Boolean> li = new ArrayList<>();

        int maxCandies = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxCandies) {
                maxCandies = arr[i];
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] + extraCandies >= maxCandies) {
                li.add(true);
            } else {
                li.add(false);
            }
        }
        return li;
    }
}