package com.research.multiplication.practice.pre;
public class Arraymatrix {

	public static void printRow_A(int[] row) {
        for (int i : row) {
            System.out.print(i);
            System.out.print("\t");
        }
        System.out.println();
    }
	private static int[][] appendArrays(int[][] matrix_I, int[][] matrix_A) {
	    int[][] ret = new int[matrix_I.length + matrix_A.length][];
	    int i = 0;
	    for (;i<matrix_I.length;i++) {
	        ret[i] = matrix_I[i];
	    }
	    for (int j = 0;j<matrix_A.length;j++) {
	        ret[i++] = matrix_A[j];
	    }
	    return ret;
	}
		// TODO Auto-generated constructor stub
	public static void main(String[] agrs){
		final long startTime = System.nanoTime();
		int[][] matrix_A = new int[6][16];
		int[][] matrix_Anegative = new int[6][16];
		int[] matrix_b = new int [6];
		int[] matrix_q = new int[28];
		int[][] matrix_I = new int[16][16];
		int[][][] matrix_M = new int[3][6][16];
		System.out.println("Matrix A=");		
		for (int column=0; column<matrix_A.length; column++){
			for (int row = 0; row < matrix_A[column].length; row++) {
			if(column==0 && row==11) {matrix_A[column][row]=1;}
			else if(column==1 && row==3) {matrix_A[column][row]=1;}
			else if(column==2 && row==3) {matrix_A[column][row]=1; }
			else if(column==3 && row==5) {matrix_A[column][row]=1;}
			else if(column==4 && row==13) {matrix_A[column][row]=1;}
			else if(column==5 && row==11) {matrix_A[column][row]=1;}
			else{matrix_A[column][row]=0;}
			}
		}
		for(int[] row : matrix_A){
			printRow_A(row);
		}//matrix A ends
		
		System.out.println("Matrix b=");
		for (int column=0; column<matrix_b.length; column++){
			matrix_b[column]=1;
		}
		for (int i : matrix_b) {
			System.out.println(i);
		}//matrix b ends
		
		System.out.println("Matrix q=");
		for (int column=0; column<matrix_q.length; column++){
			if(column< 16){matrix_q[column]=1;}
			else if(column>=16 && column<22) {matrix_q[column]=-1;}
			else {matrix_q[column]=1;}
		}
		for (int i : matrix_q) {
			System.out.println(i);
			
		}//matrix q ends
		
		//Calculation of time used to create the matrix
		final long duration1 = System.nanoTime() - startTime;
		System.out.println("Time Taken = " + duration1);
		
		//Matrix -I Start
		System.out.println("Matrix -I=");
		for (int column=0; column<matrix_I.length; column++) {
			for (int row = 0; row < matrix_I[column].length; row++) {
				if (column == row) {matrix_I[column][row]= -1; }
				else {matrix_I[column][row]= 0;}
			}
		}
		for(int[] row : matrix_I){
			printRow_A(row);
		}//Matrix -I ends
		
		//Matrix -A start
		System.out.println("Matrix A=");		
		for (int column=0; column<matrix_A.length; column++){
			for (int row = 0; row < matrix_A[column].length; row++) {
				if(matrix_A[column][row]==1) {matrix_A[column][row]=-1; }
			}
		}
		for(int[] row : matrix_A){
			printRow_A(row);
		}//Matrix -A end
		
		int[][] appended = appendArrays(matrix_I, matrix_A);
	    System.out.println("Matrix M");
	    for (int i = 0; i < appended.length; i++) {
	        for (int j = 0; j < appended[i].length; j++) {
	            System.out.print(appended[i][j]+", ");
	        }
	        System.out.println();
	        
	    }

		final long duration2 = System.nanoTime() - startTime;
		System.out.println("Time Taken = " + duration2);
	
	}//main ends
	

}
