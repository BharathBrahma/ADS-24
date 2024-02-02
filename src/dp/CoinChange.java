package dp;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        int[] coins = { 1, 3, 5, 6} ;
        int amount = 9;

        tenderMinCoins(coins, amount);
    }

    private static void tenderMinCoins(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for(int i=0 ; i< dp.length; i++){
            dp[i] = 10;
        }
        dp[0] = 0;

        for(int coin : coins) {
            System.out.println("");
            System.out.println("coin : " +  coin);
            for( int i=1; i < amount + 1; i++)
            {
                System.out.println("i : " + i );
                if(coin <= i && dp[i-coin] != 10){
                    System.out.println("Math.min ( dp[" + i + "] , 1 +  dp[ " + i + " - " + coin + "] )");
                    System.out.println("Math.min (" + dp[i] + " , 1 + " + dp[i - coin] + ")");
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
                
                System.out.println(Arrays.toString(dp));
            }
        }
        System.out.println("ANSWER : " + dp[amount]);
    }
}
