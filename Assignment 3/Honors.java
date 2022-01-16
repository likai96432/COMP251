import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Honors {

	public static int min_moves(int[][] board) {
		int[][]visited=new int[board.length][board[0].length];
		for(int i=0;i<visited.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				visited[i][j]=-1;
			}
		}
		visited[0][0]=0;
		LinkedList<int[]> queue=new LinkedList<int[]>();
		
		int []start= {0,0};
		queue.add(start);
		while (queue.size() != 0) {
			int[] temp = queue.poll();
			int row = temp[0];
			int co = temp[1];
			int move = board[temp[0]][temp[1]];
			if (row - move >= 0) {
				int[] left = { row - move, co };
				if(visited[row-move][co]==-1) {
					visited[row-move][co]=visited[row][co]+1;
					queue.add(left);
				}
				else {
					if(visited[row-move][co]>visited[row][co]+1) {
						visited[row-move][co]=visited[row][co]+1;
						queue.add(left);
					}
				}
			}
			if (row + move <= board[0].length) {
				int[] right = { row + move, co };
				if(visited[row+move][co]==-1) {
					visited[row+move][co]=visited[row][co]+1;
					queue.add(right);
				}
				else {
					if(visited[row+move][co]>visited[row][co]+1) {
						visited[row+move][co]=visited[row][co]+1;
						queue.add(right);
					}
				}
				
				
			}
			if (co - move >= 0) {
				int[] bottom = { row, co - move };
				if(visited[row][co-move]==-1) {
					visited[row][co-move]=visited[row][co]+1;
					queue.add(bottom);
				}
				else {
					if(visited[row][co]>visited[row][co-move]+1) {
						visited[row][co]=visited[row][co-move]+1;
						queue.add(bottom);
					}
				}
			}
			if (co - move >= 0) {
				int[] up = { row, co + move };
				if(visited[row][co+move]==-1) {
					visited[row][co+move]=visited[row][co]+1;
					queue.add(up);
				}
				else {
					if(visited[row][co]>visited[row][co+move]+1) {
						visited[row][co]=visited[row][co+move]+1;
						queue.add(up);
					}
				}
			}

		}
		return board[board.length][board.length];
	}

	
	public void test(String filename) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(filename));
		int num_rows = sc.nextInt();
		int num_columns = sc.nextInt();
		int[][] board = new int[num_rows][num_columns];
		for (int i = 0; i < num_rows; i++) {
			char line[] = sc.next().toCharArray();
			for (int j = 0; j < num_columns; j++) {
				board[i][j] = (int) (line[j] - '0');
			}

		}
		sc.close();
		int answer = min_moves(board);
		System.out.println(answer);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Honors honors = new Honors();
		honors.test(args[0]); // run 'javac Honors.java' to compile, then run 'java Honors testfilename'

		// or you can test like this
		// int [][]board = {1,2,3}; // not actual example
		// int answer = min_moves(board);
		// System.out.println(answer);
	}

}