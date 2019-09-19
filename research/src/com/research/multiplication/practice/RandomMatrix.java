package com.research.multiplication.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Generates random matrix containing 0's and 1's in the ratio 3:7
 *
 */
public class RandomMatrix {
	/**
	 * Random object to select a random row.
	 */
	private Random rand1;
	/**
	 * Random object to select a random column.
	 */
	private Random rand2;

	/**
	 * This method checks if there are atleast two 1s present in the given row and
	 * column.
	 * 
	 * @param arr
	 * @param x
	 * @param y
	 * @return true if atleast two 1s present else returns false.
	 */
	public boolean check(int[][] arr, int x, int y) {
		int sum = 0;
		for (int j = 0; j < arr[0].length; j++)
			sum += arr[x][j];
		if (sum < 2)
			return false;
		sum = 0;
		for (int i = 0; i < arr.length; i++)
			sum += arr[i][y];
		if (sum < 2)
			return false;
		return true;
	}

	/**
	 * This method randomly fills the remaining zeroes in the matrix.
	 * 
	 * @param arr
	 * @param m
	 * @param n
	 * @param zeroes
	 */
	public void fillZeroes(int[][] arr, int m, int n, double zeroes) {
		while (zeroes > 0) {
			int rand_row = rand1.nextInt(m);
			int rand_col = rand2.nextInt(n);
			if (arr[rand_row][rand_col] != 0 && check(arr, rand_row, rand_col) == true) {
				arr[rand_row][rand_col] = 0;
				zeroes--;
			}
		}
	}

	/**
	 * This method fills 1's and 0s in the matrix randomly.
	 * 
	 * @param arr
	 * @param m
	 * @param n
	 */
	public void fillElements(int[][] arr, int m, int n) {
		rand1 = new Random(10);
		rand2 = new Random(20);
		double zeroes = 0.3 * m * n; // total zeroes that should be present.
		ArrayList<Integer> arr_row = new ArrayList<>(); // Row numbers that do not contain atleast one zero.
		ArrayList<Integer> arr_col = new ArrayList<>(); // Column numbers that do not contain atleast one zero.
		int r = m; // number of rows remaining in which zeroes are to be filled.
		int c = n; // number of columns remaining in which zeroes are to be filled.
		for (int[] row : arr)
			Arrays.fill(row, 1);
		for (int i = 0; i < m; i++)
			arr_row.add(i);
		for (int i = 0; i < n; i++)
			arr_col.add(i);

		// filling zeroes in random rows and columns.
		while (r > 0 && c > 0) {
			int rand_row = rand1.nextInt(r) + 1;
			int rand_col = rand2.nextInt(c) + 1;
			if (arr[arr_row.get(rand_row - 1)][arr_col.get(rand_col - 1)] == 1) {
				arr[arr_row.get(rand_row - 1)][arr_col.get(rand_col - 1)] = 0;
				zeroes--;
				arr_row.remove(rand_row - 1);
				arr_col.remove(rand_col - 1);
				r--;
				c--;
			}
		}

		// filling the remaining zeroes after all the rows and columns have atleast one
		// zero.
		if (r == 0 && c == 0) {

			fillZeroes(arr, m, n, zeroes);

		} else if (r == 0) { // filling zeroes in the left over columns.
			while (c > 0 && zeroes > 0) {
				int rand_row = rand1.nextInt(m);
				int rand_col = 0;
				int i = 0;
				int flag = -1;
				for (i = 0; i < arr_col.size(); i++) {
					rand_col = arr_col.get(i);
					if (arr[rand_row][rand_col] != 0 && check(arr, rand_row, rand_col) == true) {
						arr[rand_row][rand_col] = 0;
						zeroes--;
						c--;
						flag = 1;
						break;
					}
				}
				if (flag == 1)
					arr_col.remove(i);
			}

		} else { // filling zeroes in the left over rows.

			while (r > 0 && zeroes > 0) {
				int rand_col = rand2.nextInt(n);
				int rand_row = 0;
				int flag = -1;
				int i = 0;
				for (i = 0; i < arr_row.size(); i++) {
					rand_row = arr_row.get(i);
					if (arr[rand_row][rand_col] != 0 && check(arr, rand_row, rand_col) == true) {
						arr[rand_row][rand_col] = 0;
						zeroes--;
						r--;
						flag = 1;
						break;
					}
				}
				if (flag == 1)
					arr_row.remove(i);
			}
		}
		fillZeroes(arr, m, n, zeroes); // placing the remaining zeroes randomly in the matrix.
	}

	public static void main(String[] args) {
		int m, n;
		Scanner in = new Scanner(System.in);
		
		do {
			System.out.println("Enter the number of rows (>1)");
			m = in.nextInt();
			System.out.println("Enter the number of columns (>1)");
			n = in.nextInt();
			if ((0.3 * m * n) < Math.max(m, n) || (m * n) % 10 != 0)
				System.out.println("Wrong input!! Enter again ");
		} while ((0.3 * m * n) < Math.max(m, n) || (m * n) % 10 != 0);

		int arr[][] = new int[m][n];
		RandomMatrix r1 = new RandomMatrix();
		r1.fillElements(arr, m, n);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

	}
}
