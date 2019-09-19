package com.research.multiplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import ilog.concert.*;
import ilog.cplex.*;

import ilog.concert.IloNumVar;

public class Matrix {
	private int row;
	private int column;
	private double[][] element;

	// create M-by-N matrix of 0's with rows and columns
	public Matrix(int r, int c) {
		this.row = r;
		this.column = c;
		this.element = new double[r][c];
	}

	// create matrix based on 2d array with given rows and column
	public Matrix(double[][] element) {
		row = element.length;
		column = element[0].length;
		this.element = new double[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++)

				this.element[i][j] = element[i][j];
		}
	}

	// create and return a random M-by-N matrix A with values between 0 and 1
	public static Matrix random(int M, int N) {
		Matrix A = new Matrix(M, N); // new matrix A with all element 0
		//Random random = new Random();// create object to generate random number
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				A.element[i][j]=Math.random();
//				if (random.nextDouble() < 0.3) { // create random number between 1 and 0
//					A.element[i][j] = 0;
//				} else {
//					A.element[i][j] = 1;
//				}
				
				
			}
			
			 
		}
		
//		for (int i = 0; i < M; i++) {
//			for (int j = 0; j < N; j++) {
//				if (i == 0 || j == 1) {
//					A.element[i][j] = 1;
//				}
//			}
//		}
		return A;
	}

	// generate negative matrix A
	public Matrix negativeMatrixA(Matrix matrixA) {
		int M = matrixA.row;
		int N = matrixA.column;
		Matrix negativeMatrixA = new Matrix(M, N);
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				negativeMatrixA.element[i][j] = -matrixA.element[i][j];
			}
		}
		return negativeMatrixA;
	}

	// create negative identity matrix of size N
	public static Matrix identity(int N) {
		Matrix I = new Matrix(N, N);
		for (int i = 0; i < N; i++)
			I.element[i][i] = -1;
		return I;
	}

	// for forming matrix M by using negative Identity Matrix, Matrix A and -A
	public Matrix matrixM(Matrix A, Matrix negI, Matrix negA, int total) {
		Matrix M = new Matrix(total, total);
		int fix = negI.column;
		int sum = fix + A.row;
		System.out.println("Matrix M ");
		for (int i = 0; i < total; i++) {
			for (int j = 0; j < total; j++) {
				if (j >= fix) {
					M.element[i][j] = 0;
				} else {
					if (i < fix && j < fix) {
						M.element[i][j] = negI.element[i][j];
					} else if (i >= fix && i < (A.row + negI.row)) {
						M.element[i][j] = A.element[i - fix][j];
					} else if (i < total && i >= (A.row + negI.row)) {
						M.element[i][j] = negA.element[i - fix - A.row][j];
					}
				}
			}
		}
		return M;
	}

	// to generate Q as per the given m and n
	public Matrix generateQ(int m, int n) {
		Matrix Qt = new Matrix(1, n + 2 * m);
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < n + 2 * m; j++) {
				if (j < n) {
					Qt.element[i][j] = 1;
				} else if (j >= n && j < m + n) {
					Qt.element[i][j] = -1;
				} else if (j >= m + n && j < n + 2 * m) {
					Qt.element[i][j] = 1;
				}
			}
		}
		return Qt;
	}

	// negative of q
	public Matrix generateNegQ(Matrix q) {
		Matrix negQ = new Matrix(q.row, q.column);
		for (int i = 0; i < q.row; i++) {
			for (int j = 0; j < q.column; j++) {
				negQ.element[i][j] = -q.element[i][j];
			}
		}
		return negQ;

	}

	// to generate S transpose as per the given m and n
	public Matrix generateS(int m, int n) {
		Matrix S = new Matrix(1, n + 2 * m);
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < n + 2 * m; j++) {
				if (j < n) {
					S.element[i][j] = 0;
				} else if (j >= n && j < m + n) {
					S.element[i][j] = 1;
				} else if (j >= m + n && j < n + 2 * m) {
					S.element[i][j] = 0;
				}
			}
		}
		return S;
	}

	// to generate matrix R transpose
	public Matrix generateR(int m, int n) {
		Matrix Rt = new Matrix(1, n + 2 * m);
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < n + 2 * m; j++) {
				if (j < n) {
					Rt.element[i][j] = 1;
				} else if (j >= n && j < m + n) {
					Rt.element[i][j] = 0;
				} else if (j >= m + n && j < n + 2 * m) {
					Rt.element[i][j] = 1;
				}
			}
		}
		return Rt;
	}

	// Multiply S transpose M (St*M)
	public Matrix StransposeM(Matrix St, Matrix M) {
		Matrix StM = new Matrix(St.row, M.column);
		for (int i = 0; i < St.row; i++) { // st row
			for (int j = 0; j < StM.column; j++) {// M column
				for (int k = 0; k < St.column; k++) {// st column
					StM.element[i][j] += (St.element[i][k] * M.element[k][j]);
				}
			}
		}
		return StM;
	}

	// sum of rT and StM
	public Matrix RtSumStM(Matrix Rt, Matrix StM) {
		Matrix SumRtStM = new Matrix(Rt.row, Rt.column);
		for (int i = 0; i < Rt.row; i++) {
			for (int j = 0; j < Rt.column; j++) {
				SumRtStM.element[i][j] = Rt.element[i][j] + StM.element[i][j];
			}
		}
		return SumRtStM;
	}

	// convert 2d matrix to 1 d matrix
	public double[] convertMatrixTo1D(Matrix any) {
		int m = any.row;
		int n = any.column;
		double[] oneD = new double[m * n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				oneD[j] = any.element[i][j];
			}
		}
		return oneD;
	}

	public double[][] Matrixto2dArray(Matrix any) {
		int m = any.row;
		int n = any.column;
		double[][] arrayMatrix = new double[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				arrayMatrix[i][j] = any.element[i][j];
			}
		}
		return arrayMatrix;

	}

	// private Matrix multiplyMZ(Matrix M, String[][] Z) {
	//
	// int c1 = M.column;
	// int r1 = M.row;
	// int c2 = 1;
	// int r2=r1;
	// Matrix c = new Matrix(r1,c2);
	// for (int i = 1; i <= r1; i++)
	// for (int j = 1; j <=c1; j++)
	// for (int k = 1; k <= r2; k++)
	// c[i][j] += M.element[i][k]* 1;
	// //return c;
	//
	// }

	// print matrix
	public void printMatrix(Matrix sample) {
		int x = sample.row;
		int y = sample.column;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				System.out.printf("%3.0f ", sample.element[i][j]);
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}

	// print 2D matrix
	public void print2DArray(double[][] sample) {
		int r = sample.length;
		int c = sample[0].length;
		System.out.println("row of 2d matrix is " + r);
		System.out.println("column of 2d matrix is " + c);
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.printf("%3.0f ", sample[i][j]);
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}

	// print 1d Array
	public void printArray(double[] sample) {
		int r = sample.length;
		System.out.println("row of 1d matrix is " + r);
		for (int i = 0; i < r; i++) {
			System.out.printf("%3.0f ", sample[i]);
		}
		System.out.println(" ");
	}

}
