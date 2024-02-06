package dp;

import java.util.Arrays;

public class AllUniquePaths {
    static int countWaysUtil(int i, int j, int[][] dp) {
        //Base case
        if(i == 0 && j == 0) return 1;
        //out of bounds case
        if(i < 0 || j < 0 ) return 0;
        
        if(i >= 0 && j >= 0 && dp[i][j] == -1) return dp[i][j] = 0;

        //if already calculated return result
        if(dp[i][j] != -1 ) return dp[i][j]; 
        
        //what is the possible things to do at a position
        int up = countWaysUtil(i-1, j, dp);
        int left = countWaysUtil(i, j-1, dp);

        //sum and store in dp
        return dp[i][j] = up + left;
    }

    static int countWays(int m, int n) {
        int dp[][] = new int[m][n];
            
        // Initialize the DP array with -1 to indicate uncomputed values
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // Start the recursive calculation from the bottom-right cell (m-1, n-1)
        return countWaysUtil(m-1, n-1, dp);
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 3;

        // Call the countWays function and print the result
        System.out.println("No of paths : " +countWays(m, n));
    }
}
