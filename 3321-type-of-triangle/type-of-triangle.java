class Solution {
    public String triangleType(int[] nums) {
          int a = nums[0];
        int b = nums[1];
        int c = nums[2];

        // 1. Triangle Inequality Theorem: Sum of any two sides MUST be greater than the third side
        if (a + b <= c || a + c <= b || b + c <= a) {
            return "none";
        }

        // 2. All 3 sides are equal
        if (a == b && b == c) {
            return "equilateral";
        }

        // 3. At least 2 sides are equal
        if (a == b || b == c || a == c) {
            return "isosceles";
        }

        // 4. All 3 sides are different
        return "scalene";
    }
}