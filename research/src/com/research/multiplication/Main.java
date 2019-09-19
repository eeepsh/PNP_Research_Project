package com.research.multiplication;

import java.util.Scanner;

public class Main {
	// test client
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter the number of rows and column of matrix : ");
		int r = sc.nextInt();
		int c = sc.nextInt();

		System.out.println("Row of matrix m= " + r);
		System.out.println("Column of matrix n= " + c);
		Matrix m = new Matrix(r, c);
		Matrix A1 = m.random(r, c);
		System.out.println("Matrix A : ");
		m.printMatrix(A1);
		System.out.println();

		Matrix negativeIdentity = m.identity(c);
		System.out.println("Negative Identity Matrix is");
		m.printMatrix(negativeIdentity);

		Matrix negA = m.negativeMatrixA(A1);
		System.out.println("Negative of matrix A is ");
		m.printMatrix(negA);

		int total = c + r + r;
		System.out.println("n+2*m = " + total);
		Matrix M = m.matrixM(A1, negativeIdentity, negA, total);
		m.printMatrix(M);

		Matrix St = m.generateS(r, c);
		System.out.println("S Matrix is St: ");
		m.printMatrix(St);

		Matrix StM = m.StransposeM(St, M);
		System.out.println("S  * M  is St*M: : ");
		m.printMatrix(StM);

		Matrix Rt = m.generateR(r, c);
		System.out.println("R Matrix is  Rt: ");
		m.printMatrix(Rt);

		Matrix q = m.generateQ(r, c);
		System.out.println("Q is ");
		m.printMatrix(q);
		Matrix sumRtStm = m.RtSumStM(Rt, StM);
		System.out.println("Rt + St*M  : Coeffiecients Of Z ");
		m.printMatrix(sumRtStm);

		double[] obj = m.convertMatrixTo1D(sumRtStm); // converting coefficients of Z in one d array
		// Cplex cplex= new Cplex();
		// Cplex.solveModel(total, m, c, A, b);

//		Matrix z = m.subjectedConstraints(M);
//		System.out.println("Z is ");
//		m.printMatrix(z);
		
		//got the rhs of equation which is neg of q  from M*Z+Q>0 ~ M*Z > -Q
		Matrix negQ= m.generateNegQ(q);
		System.out.println("neg of q is :");
		m.printMatrix(negQ);
		double[] negQarr = m.convertMatrixTo1D(negQ);
		System.out.println("negQ after conversion of it to 1d array : ");
		m.printArray(negQarr);// converting 2d to 1d
		
		
		//converting from matrix to 2d format
		double[][] arraymatrixM= m.Matrixto2dArray(M);
		System.out.println("Matrix m after conversition to 2d array is ");
		m.print2DArray(arraymatrixM);
		
		
		// Define the coefficient matrix for the constraint
		
		linearProgramming.solveSimpleLinear(total, total, obj, arraymatrixM, negQarr);
	}
}
