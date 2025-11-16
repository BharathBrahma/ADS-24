class Solution {
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";

        int[] need = new int[128]; // freq map for t
        int required = 0;

        // fill need[] with chars from t
        for (char c : t.toCharArray()) {
            if (need[c] == 0) required++; // number of distinct chars needed
            need[c]++;
        }

        int[] window = new int[128];
        int formed = 0;
        int left = 0, right = 0;

        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            window[c]++;

            // when we fully satisfy this character's requirement
            if (window[c] == need[c]) {
                formed++;
            }

            // try to shrink the window from the left
            while (formed == required) {
                // update answer
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
                window[leftChar]--;

                // if removing this breaks the requirement
                if (window[leftChar] < need[leftChar]) {
                    formed--;
                }
                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}
