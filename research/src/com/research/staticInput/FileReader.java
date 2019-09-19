//package com.research.staticInput;
//
//import java.io.File;
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class FileReader {
//	
////	public static void main(String[] args) {
////		double[] data = readFiles("InputFile.txt");
////		System.out.println("Hi ");
////		System.out.println(Arrays.toString(data));
////	}
//	
//	// reading the text file and converting it to integer array
//	
//	
//		public double[] readFiles(String file) {
//			try {
//				File f= new File(file);
//				Scanner sc= new Scanner(f);
//				int count=0;
//				while (sc.hasNextDouble()) {
//					count++;
//					sc.nextDouble();
//					
//				}
//				
//				double[] arr= new double[count];
//				 
//				Scanner s = new Scanner(f);
//				for (int i = 0; i < arr.length; i++) {
//						arr[i] = s.nextDouble();
//						if(arr[i]!=0 && arr[i]!=1) {
//							System.out.println("Wrong input ");
//						
//					}
//				}
//				return arr;
//				
//			} catch (Exception e) {
//				
//				return null;
//				// TODO: handle exception
//			}
//		
//		}
//
//}
