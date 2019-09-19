package com.research.staticInput;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;


public class Main {

	// test client
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter the number of rows and column of matrix : ");
                double startTime, endTime;
		int r = sc.nextInt();
		int c = sc.nextInt();ss
		double[][] a = new double[r][c];

		System.out.println("Row of matrix m= " + r);
		System.out.println("Column of matrix n= " + c);
		int sizeOfArray= r*c;
		int total = c + r + r;
		
	    
	    
		

//		System.out.println("Enter the elements of martix A row wise which is only 1 and 0 \n");
//		for (int i = 0; i < r; i++) {
//			for (int j = 0; j < c; j++) {
//				
//				a[i][j] = sc.nextInt();
//				if(a[i][j]!=0 && a[i][j]!=1) {
//					System.out.println("Wrong input ");
//				}
//			}
//		}
	
		//FileReader fReader= new FileReader();
		
		
		double[] data= readFiles("InputFile.text");
		System.out.println(Arrays.toString(data));
		
		//System.out.println("Data row" + data.length);
			
		
		 startTime = System.currentTimeMillis();

		Matrix m = new Matrix(r, c);
	    double[][] data2DArray= m.oneDto2dArray(data, r,c);
		//Matrix A1 = m.random(r, c);
		

	   Matrix A = m.arrayToMatrix(data2DArray);
		
		Matrix identity = m.identity(c);
		

		Matrix negA = m.negativeMatrixA(A);
		

		
		
		Matrix M = m.matrixM(A, identity, negA, total);
		

		Matrix St = m.generateS(r, c);
		

		Matrix StM = m.StransposeM(St, M);
	

		Matrix Rt = m.generateR(r, c);
		

		Matrix q = m.generateQ(r, c);
		
		Matrix sumRtStm = m.RtSumStM(Rt, StM);
		

		double[] SumOfRtM = m.convertMatrixTo1D(sumRtStm); // converting coefficients of Z in one d array
		// Cplex cplex= new Cplex();
		// Cplex.solveModel(total, m, c, A, b);

		// Matrix z = m.subjectedConstraints(M);
		// System.out.println("Z is ");
		// m.printMatrix(z);

		// got the rhs of equation which is neg of q from M*Z+Q>0 ~ M*Z > -Q
		Matrix negQ = m.generateNegQ(q);
		
		double[] negQarr = m.convertMatrixTo1D(negQ);
		//System.out.println("negQ after conversion of it to 1d array : ");
		//m.printArray(negQarr);// converting 2d to 1d

		// converting from matrix to 2d format
		double[][] arraymatrixM = m.Matrixto2dArray(M);
		//System.out.println("Matrix m after conversition to 2d array is ");
		//m.print2DArray(arraymatrixM);

		// Define the coefficient matrix for the constraint

		 linearProgramming.solveSimpleLinear(total, total, SumOfRtM, arraymatrixM, negQarr);
                endTime = System.currentTimeMillis();
                System.out.println("Time execution:"+(endTime - startTime)+" milliseconds");
                System.out.println("Matrix A : ");
                m.printMatrix(A);
         		System.out.println();
         		System.out.println("Identity Matrix is");
        		m.printMatrix(identity);
        		System.out.println("Negative of matrix A is ");
        		m.printMatrix(negA);
        		System.out.println("n+2*m = " + total);
        		m.printMatrix(M);
        		System.out.println("S Matrix is St: ");
        		m.printMatrix(St);
        		System.out.println("S  * M  is St*M: : ");
        		m.printMatrix(StM);
        		System.out.println("R Matrix is  Rt: ");
        		m.printMatrix(Rt);
        		System.out.println("Q is ");
        		m.printMatrix(q);
        		System.out.println("Rt + St*M  : Coeffiecients Of Z ");
        		m.printMatrix(sumRtStm);
        		System.out.println("neg of q is :");
        		m.printMatrix(negQ);

                 
                
                
	}
	
	// reading the text file and converting it to integer array
	
	
			public static double[] readFiles(String file) {
				try {
					File f= new File(file);
					Scanner sc= new Scanner(f);
					int count=0;
					while (sc.hasNextDouble()) {
						count++;
						sc.nextDouble();
						
					}
					
					double[] arr= new double[count];
					 
					Scanner s = new Scanner(f);
					for (int i = 0; i < arr.length; i++) {
							arr[i] = s.nextDouble();
							if(arr[i]!=0 && arr[i]!=1) {
								System.out.println("Wrong input ");
							
						}
					}
					return arr;
					
				} catch (Exception e) {
					
					return null;
					// TODO: handle exception
				}
			
			}
}
