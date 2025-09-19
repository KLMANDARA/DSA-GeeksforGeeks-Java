class Solution {
    public int minParentheses(String s) {
        int open = 0;        // Unmatched '(' count
        int insertions = 0;  // Total insertions needed

        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++; // We need a ')' to match this
            } else if (c == ')') {
                if (open > 0) {
                    open--; // Match with an existing '('
                } else {
                    insertions++; // Need to insert a '('
                }
            }
        }

        // Any unmatched '(' still need a ')'
        return insertions + open;
    }
}
