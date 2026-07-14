class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] arr = new int[2 * n];
        int left = 0; // Pointer for x1, x2, ..., xn
        
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                arr[i] = nums[left]; 
                left++; // Move to the next x element
            } else {
                // The corresponding y element is always exactly 'n' positions ahead of its x partner
                arr[i] = nums[left - 1 + n]; 
            }
        }
        return arr;  
    }
}