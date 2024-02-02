package dp;

public class ClimbingStairs {
    public static void main(String[] args) {
        int n = 100;
        System.out.println("Number of ways to climb 4 steps = " + calculateStepsWithDP(n));
    }

    private static long calculateStepsWithDP(int n) {
        //base case
        if (n == 1)
            return 1;
        
        long dp[] = new long[n+1];
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i -2];
        }
        return dp[n];
    }

    
}
