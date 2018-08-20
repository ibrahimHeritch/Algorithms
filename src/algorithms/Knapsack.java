package algorithms;

import java.util.Scanner;
/*
 * standard dp solution to the knapsack problem
 */
public class Knapsack {

	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		int weight=s.nextInt();
		int n=s.nextInt();
		int[] values=new int[n];
		int[] weights=new int[n];
		for(int i=0;i<n;i++) {
			values[i]=s.nextInt();
			weights[i]=s.nextInt();
		}
		int[][] ar= new int[n+1][weight+1];
		for(int i=0;i<=n;i++) {
		  for(int j=0;j<=weight;j++) {
			  
			  if (i==0 || j==0)
                  ar[i][j] = 0;
             else if(j>=weights[i-1]) {
				  ar[i][j]=Math.max(ar[i-1][j],values[i-1]+ar[i-1][j-weights[i-1]]);
			  }else{
				  ar[i][j]=ar[i-1][j];
			  }
		  }
		  
		}
      System.out.println(ar[n][weight]);
      System.out.println(knapSack(weight,weights,values,n));
	}
	static int max(int a, int b) { return (a > b)? a : b; }
    
	   // Returns the maximum value that can be put in a knapsack of capacity W
	    static int knapSack(int W, int wt[], int val[], int n)
	    {
	         int i, w;
	     int K[][] = new int[n+1][W+1];
	      
	     // Build table K[][] in bottom up manner
	     for (i = 0; i <= n; i++)
	     {
	         for (w = 0; w <= W; w++)
	         {
	             if (i==0 || w==0)
	                  K[i][w] = 0;
	             else if (wt[i-1] <= w)
	                   K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w]);
	             else
	                   K[i][w] = K[i-1][w];
	         }
	      }
	      
	      return K[n][W];
	    }
}










