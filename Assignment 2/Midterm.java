import java.util.*;
import java.lang.*;
import java.io.*;

public class Midterm {
	private static int[][] dp_table;
	private static int[] penalization;
	
	private static int compare;
	

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int chairs;
		try {
			chairs = Integer.valueOf(reader.readLine());
			penalization = new int[chairs];
			for (int i = 0; i < chairs; i++) {
				penalization[i] = Integer.valueOf(reader.readLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int answer = lost_marks(penalization);
		System.out.println(answer);
		
	}

	public static int lost_marks(int[] penalization) {

		
		dp_table = new int[penalization.length][penalization.length];
       compare=Integer.MAX_VALUE;
		fillTable(1, 1, penalization,0);

		
	
         
		return compare; // placeholder
	}



private static void fillTable(int i, int j, int[] penalization,int lastmark) {
	

	if(dp_table[i][j]!=0 && dp_table[i][j]< lastmark+penalization[i]) {
		return;
	}
		dp_table[i][j]=lastmark+penalization[i];
		if(dp_table[i][j]>compare) return;
		if(i==penalization.length-1) compare=Math.min(compare, dp_table[i][j]);
		
	if(i+j+1<penalization.length) {
		fillTable(i+j+1,j+1,penalization,dp_table[i][j]);
	}	
	if(i-j>=0) {
		fillTable(i-j,j,penalization,dp_table[i][j]);
	}
		
		
}

}