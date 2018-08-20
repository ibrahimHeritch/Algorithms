package algorithms;
/**
 * divide and conquer algo
 *  compute the number of inversions in the file given,
 *   where the ith row of the file indicates the ith entry of an array.
 *   
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class inversions {
	public static void main(String[] args) throws NumberFormatException, Exception {
		StringBuilder sb= new StringBuilder();
		STDIN scanner= new STDIN();
		int n=100000;
		
			int[] ar=new int[n];
			for(int i=0;i<n;i++) {
				ar[i]=scanner.nextInt();
			}
			sb.append(count(ar,0,ar.length)+"\n");
		
		System.out.println(sb);
	}

	private static long count(int[] ar, int i, int l) {
		if(l-i<2) {
			return 0;
		}else {
			long right=count(ar,i+(l-i)/2,l);
			long left=count(ar,i,i+(l-i)/2);
			return right+left+splitCount(ar,i,l);
		}
		
	}

	private static long splitCount(int[] ar, int s, int l) {
		int a=s,b=s+(l-s)/2;
		long sum=0;
		int[] temp=new int[l-s];
		for(int i=0;i<l-s;i++) {
			if(b>=l||(a<s+(l-s)/2 && ar[a]<ar[b])) {
				temp[i]=ar[a];
				a++;
			}else {
				temp[i]=ar[b];
				if(a<s+(l-s)/2) {
					sum+=s+(l-s)/2-a;
				}
				b++;
				
				
			}
		}
		for(int i=s;i<l;i++) {
			ar[i]=temp[i-s];
		}
		return sum;
	}
}
class STDIN {
	BufferedReader br;
	StringTokenizer st;

	public STDIN() {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = null;
	}

	boolean hasNext() throws Exception {
		if (!st.hasMoreTokens())
			st = new StringTokenizer(br.readLine());
		return st.hasMoreTokens();
	}

	int nextInt() throws NumberFormatException, Exception  {
		return Integer.parseInt(next());
	}

	long nextLong() throws Exception {
		return Long.parseLong(next());
	}

	double nextDouble() throws Exception {
		return Double.parseDouble(next());
	}

	String next() throws Exception {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine());
		return st.nextToken();
	}

	String nextLine() throws Exception {
		return br.readLine();
	}

}