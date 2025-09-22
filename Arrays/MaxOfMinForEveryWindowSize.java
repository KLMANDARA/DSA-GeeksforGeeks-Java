import java.util.*;

class Solution {
    public ArrayList<Integer> maxOfMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];   // Previous Smaller Element
        int[] right = new int[n];  // Next Smaller Element

        Arrays.fill(left, -1);
        Arrays.fill(right, n);

        // Step 1: Find Previous Smaller Element (PSE)
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            if (!st.isEmpty()) left[i] = st.peek();
            st.push(i);
        }

        // Step 2: Find Next Smaller Element (NSE)
        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            if (!st.isEmpty()) right[i] = st.peek();
            st.push(i);
        }

        // Step 3: ans[len] stores max of minimums for window size len
        int[] ans = new int[n + 1];  // 1-based indexing
        for (int i = 0; i < n; i++) {
            int len = right[i] - left[i] - 1;
            ans[len] = Math.max(ans[len], arr[i]);
        }

        // Step 4: Fill blanks by propagating from right to left
        for (int i = n - 1; i >= 1; i--) {
            ans[i] = Math.max(ans[i], ans[i + 1]);
        }

        // Step 5: Convert to ArrayList
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            res.add(ans[i]);
        }
        return res;
    }
}
