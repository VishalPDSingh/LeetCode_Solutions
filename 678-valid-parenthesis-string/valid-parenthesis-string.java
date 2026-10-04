class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else if (c == '*') {
                minOpen--; // treat '*' as ')'
                maxOpen++; // treat '*' as '('
            }

            // Too many ')' - cannot be matched by any preceding '(' or '*'
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot be negative since we can't have negative unmatched '('
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        // If minOpen is 0, we can match all open parentheses
        return minOpen == 0;
    }
}