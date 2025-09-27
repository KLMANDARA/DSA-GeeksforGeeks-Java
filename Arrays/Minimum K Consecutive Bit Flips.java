class Solution {
    public int kBitFlips(int[] arr, int k) {
        int n = arr.length;
        int flip = 0; // tracks current flip parity (0 = no flip, 1 = flipped)
        int count = 0; // total flips performed

        int[] isFlipped = new int[n]; // array to track where flips start

        for (int i = 0; i < n; i++) {
            // Remove the effect of flip that ends at i - k
            if (i >= k) {
                flip ^= isFlipped[i - k];
            }

            // If current bit (after considering ongoing flips) is 0
            if ((arr[i] ^ flip) == 0) {
                if (i + k > n) return -1; // Not enough room to flip
                isFlipped[i] = 1; // Start a new flip here
                flip ^= 1; // Update flip parity
                count++;
            }
        }

        return count;
    }
}
