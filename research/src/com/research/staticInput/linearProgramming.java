package com.research.staticInput;

/**
 * 
 */

import ilog.concert.*;
import ilog.cplex.*;

/**
 * @author eeeps
 *
 */
public class linearProgramming {

	/**
	 * @param args 
	 */
//	public static void main(String[] args) {
//		int numberOfDecisionVariables = 7; // number of variables
//		int m = 7; // number of constraints
//		double[] c = { 2, 3, 2, 0, 0, 1, 1 }; // coefficients of Z
//		double[][] A = { { -1, 0, 0, 0, 0, 0, 0 }, { 0, -1, 0, 0, 0, 0, 0 }, { 0, 0, -1, 0, 0, 0, 0 },
//				{ 1, 1, 1, 0, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0, 0 }, { -1, -1, -1, 0, 0, 0, 0 },
//				{ 0, -1, 0, 0, 0, 0, 0 } };
//		double[] b = { -1, -1, -1, 1, 1, -1, -1 }; 
//		solveSimpleLinear(numberOfDecisionVariables, m, c, A, b);
//
//	}

	public static void solveSimpleLinear(int n, int m, double[] coefficientOfZ, double[][] M, double[] negQ) {
 
		try {
			IloCplex model = new IloCplex();

			IloNumVar[] z = new IloNumVar[n];
			for (int i = 0; i < n; i++) {
				z[i] = model.numVar(0, Double.MAX_VALUE);

			}

			IloLinearNumExpr obj = model.linearNumExpr();
			for (int i = 0; i < n; i++) {

				obj.addTerm(coefficientOfZ[i], z[i]);
			}
			model.addMinimize(obj);
			for (int i = 0; i < m; i++) {
				IloLinearNumExpr constraint = model.linearNumExpr();
				for (int j = 0; j < n; j++) {
					constraint.addTerm(M[i][j], z[j]);
				}
				model.addGe(constraint, negQ[i]);
			}
			boolean isSolved = model.solve();

			if (isSolved) {
				double objVal = model.getObjValue();
				System.out.println("Objective value is " + objVal);
				for (int i = 0; i < n; i++) {
					System.out.println("z[" + (i + 1 + "] = " + model.getValue(z[i])));
				}
			} else {
				System.out.println("model is not solved ");
			}
		} catch (IloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

