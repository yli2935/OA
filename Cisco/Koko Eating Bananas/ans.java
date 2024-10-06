class Solution {
    private boolean isPossible(int[] piles, int possK, int h) {
        int totTime = 0;
        for (int i = 0; i < piles.length; i++)
            totTime += Math.ceil(piles[i] * 1d / possK); // Calculate time for each pile
        return totTime <= h; // Check if total time is within allowed hours
    }
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = Integer.MIN_VALUE;
        for (int pile : piles)
            high = Math.max(high, pile);
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(piles, mid, h)) {
                result = mid;
                 high = mid - 1;
            }else {
                low = mid + 1; // Mid speed is not enough, search for higher speeds
            }
        }
        return result;
    }
}