import java.util.*;
import java.lang.*;
import java.io.*;

public class Game {

	Board sudoku;

	public class Cell {
		private int row = 0;
		private int column = 0;

		public Cell(int row, int column) {
			this.row = row;
			this.column = column;
		}

		public int getRow() {
			return row;
		}

		public int getColumn() {
			return column;
		}
	}

	public class Region {
		private Cell[] matrix;
		private int num_cells;

		public Region(int num_cells) {
			this.matrix = new Cell[num_cells];
			this.num_cells = num_cells;
		}

		public Cell[] getCells() {
			return matrix;
		}

		public void setCell(int pos, Cell element) {
			matrix[pos] = element;
		}

	}

	public class Board {
		private int[][] board_values;
		private Region[] board_regions;
		private int num_rows;
		private int num_columns;
		private int num_regions;

		public Board(int num_rows, int num_columns, int num_regions) {
			this.board_values = new int[num_rows][num_columns];
			this.board_regions = new Region[num_regions];
			this.num_rows = num_rows;
			this.num_columns = num_columns;
			this.num_regions = num_regions;
		}

		public int[][] getValues() {
			return board_values;
		}

		public int getValue(int row, int column) {
			return board_values[row][column];
		}

		public Region getRegion(int index) {
			return board_regions[index];
		}

		public Region[] getRegions() {
			return board_regions;
		}

		public void setValue(int row, int column, int value) {
			board_values[row][column] = value;
		}

		public void setRegion(int index, Region initial_region) {
			board_regions[index] = initial_region;
		}

		public void setValues(int[][] values) {
			board_values = values;
		}

	}

	private boolean goodOnReGion(Region region, int input) {
		boolean result = true;
		for (int i = 0; i < region.num_cells; i++) {
			int co = region.getCells()[i].getColumn();
			int row = region.getCells()[i].getRow();
			int value = sudoku.getValue(row, co);
			if (value == input) {
				result = false;
				break;

			}
		}

		return result;
	}

	private boolean checkA(Cell cell, int input) {
		int r = cell.getRow();
		int c = cell.getColumn();
		int nor = sudoku.getValues().length - 1;
		int noc = sudoku.getValues()[0].length - 1;
		
		if(r+1<=nor) {
			if(sudoku.getValue(r + 1,c)==input) return false;
			else if(c+1<=noc && (sudoku.getValue(r + 1, c+1)==input)) return false;
			else if(c-1>=0 && sudoku.getValue(r + 1, c-1)==input) return false;
		}if(r-1>=0) {
			if(sudoku.getValue(r - 1,c)==input) return false;
			else if(c+1<=noc && (sudoku.getValue(r - 1, c+1)==input)) return false;
			else if(c-1>=0 && sudoku.getValue(r - 1, c-1)==input) return false;
		}
		if(c+1<=noc&&sudoku.getValue(r , c+1)==input )  return false;
		if(c-1>=0&&sudoku.getValue(r , c-1)==input )  return false;
		
		
		
		return true;
	}

	public int[][] solver() {
		helpsolver();
		return sudoku.getValues();
	}
	private boolean helpsolver() {
		
		
		for (int i = 0; i < sudoku.getRegions().length; i++) {
			for (int j = 0; j < sudoku.getRegion(i).getCells().length; j++) {
				int r = sudoku.getRegion(i).getCells()[j].getRow();
				int c = sudoku.getRegion(i).getCells()[j].getColumn();
				if (sudoku.getValue(r, c) == -1) {
                    for(int n=1;n<=sudoku.getRegion(i).getCells().length;n++) {
                    	if(checkA(sudoku.getRegion(i).getCells()[j],n)&&goodOnReGion(sudoku.getRegion(i),n)) {
                    		this.sudoku.setValue(r, c, n);
                    		if(helpsolver()==true) {
                    			return true;
                    		}
                    		else {
                    			this.sudoku.setValue(r, c, -1);
                    		}
                    	}
                    }return false;
				}
			}
		}
		
		
		
		
		
		
		
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int rows = sc.nextInt();
		int columns = sc.nextInt();
		int[][] board = new int[rows][columns];
		// Reading the board
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				String value = sc.next();
				if (value.equals("-")) {
					board[i][j] = -1;
				} else {
					try {
						board[i][j] = Integer.valueOf(value);
					} catch (Exception e) {
						System.out.println("Ups, something went wrong");
					}
				}
			}
		}
		int regions = sc.nextInt();
		Game game = new Game();
		game.sudoku = game.new Board(rows, columns, regions);
		game.sudoku.setValues(board);
		for (int i = 0; i < regions; i++) {
			int num_cells = sc.nextInt();
			Game.Region new_region = game.new Region(num_cells);
			for (int j = 0; j < num_cells; j++) {
				String cell = sc.next();
				String value1 = cell.substring(cell.indexOf("(") + 1, cell.indexOf(","));
				String value2 = cell.substring(cell.indexOf(",") + 1, cell.indexOf(")"));
				Game.Cell new_cell = game.new Cell(Integer.valueOf(value1) - 1, Integer.valueOf(value2) - 1);
				new_region.setCell(j, new_cell);
			}
			game.sudoku.setRegion(i, new_region);
		}
		int[][] answer = game.solver();
		for (int i = 0; i < answer.length; i++) {
			for (int j = 0; j < answer[0].length; j++) {
				System.out.print(answer[i][j]);
				if (j < answer[0].length - 1) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

}
