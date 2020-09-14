package com.project.algo;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Matrix implements Cloneable, java.io.Serializable {


	   private double[][] A;

	   private int m, n;


	   public Matrix (int m, int n) {
	      this.m = m;
	      this.n = n;
	      A = new double[m][n];
	   }


	   public Matrix (int m, int n, double s) {
	      this.m = m;
	      this.n = n;
	      A = new double[m][n];
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            A[i][j] = s;
	         }
	      }
	   }

	 
	   public Matrix (double[][] A) {
	      m = A.length;
	      n = A[0].length;
	      for (int i = 0; i < m; i++) {
	         if (A[i].length != n) {
	            throw new IllegalArgumentException("All rows must have the same length.");
	         }
	      }
	      this.A = A;
	   }

	 

	   public Matrix (double[][] A, int m, int n) {
	      this.A = A;
	      this.m = m;
	      this.n = n;
	   }

	 
	   public Matrix (double vals[], int m) {
	      this.m = m;
	      n = (m != 0 ? vals.length/m : 0);
	      if (m*n != vals.length) {
	         throw new IllegalArgumentException("Array length must be a multiple of m.");
	      }
	      A = new double[m][n];
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            A[i][j] = vals[i+j*m];
	         }
	      }
	   }


	   public static Matrix constructWithCopy(double[][] A) {
	      int m = A.length;
	      int n = A[0].length;
	      Matrix X = new Matrix(m,n);
	      double[][] C = X.getArray();
	      for (int i = 0; i < m; i++) {
	         if (A[i].length != n) {
	            throw new IllegalArgumentException
	               ("All rows must have the same length.");
	         }
	         for (int j = 0; j < n; j++) {
	            C[i][j] = A[i][j];
	         }
	      }
	      return X;
	   }


	   public Matrix copy () {
	      Matrix X = new Matrix(m,n);
	      double[][] C = X.getArray();
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            C[i][j] = A[i][j];
	         }
	      }
	      return X;
	   }


	   public Object clone () {
	      return this.copy();
	   }

	  
	   
	   public double[][] getArrayCopy () {
	      double[][] C = new double[m][n];
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            C[i][j] = A[i][j];
	         }
	      }
	      return C;
	   }


	   public double[] getColumnPackedCopy () {
	      double[] vals = new double[m*n];
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            vals[i+j*m] = A[i][j];
	         }
	      }
	      return vals;
	   }

	  

	   public double[] getRowPackedCopy () {
	      double[] vals = new double[m*n];
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            vals[i*n+j] = A[i][j];
	         }
	      }
	      return vals;
	   }

	 

	  

	   public double get (int i, int j) {
	      return A[i][j];
	   }


	   public Matrix getMatrix (int i0, int i1, int j0, int j1) {
	      Matrix X = new Matrix(i1-i0+1,j1-j0+1);
	      double[][] B = X.getArray();
	      try {
	         for (int i = i0; i <= i1; i++) {
	            for (int j = j0; j <= j1; j++) {
	               B[i-i0][j-j0] = A[i][j];
	            }
	         }
	      } catch(ArrayIndexOutOfBoundsException e) {
	         throw new ArrayIndexOutOfBoundsException("Submatrix indices");
	      }
	      return X;
	   }

	  

	   public Matrix getMatrix (int[] r, int[] c) {
	      Matrix X = new Matrix(r.length,c.length);
	      double[][] B = X.getArray();
	      try {
	         for (int i = 0; i < r.length; i++) {
	            for (int j = 0; j < c.length; j++) {
	               B[i][j] = A[r[i]][c[j]];
	            }
	         }
	      } catch(ArrayIndexOutOfBoundsException e) {
	         throw new ArrayIndexOutOfBoundsException("Submatrix indices");
	      }
	      return X;
	   }


	   public Matrix getMatrix (int i0, int i1, int[] c) {
	      Matrix X = new Matrix(i1-i0+1,c.length);
	      double[][] B = X.getArray();
	      try {
	         for (int i = i0; i <= i1; i++) {
	            for (int j = 0; j < c.length; j++) {
	               B[i-i0][j] = A[i][c[j]];
	            }
	         }
	      } catch(ArrayIndexOutOfBoundsException e) {
	         throw new ArrayIndexOutOfBoundsException("Submatrix indices");
	      }
	      return X;
	   }

	  
	   public Matrix getMatrix (int[] r, int j0, int j1) {
	      Matrix X = new Matrix(r.length,j1-j0+1);
	      double[][] B = X.getArray();
	      try {
	         for (int i = 0; i < r.length; i++) {
	            for (int j = j0; j <= j1; j++) {
	               B[i][j-j0] = A[r[i]][j];
	            }
	         }
	      } catch(ArrayIndexOutOfBoundsException e) {
	         throw new ArrayIndexOutOfBoundsException("Submatrix indices");
	      }
	      return X;
	   }

	  

	   public void set (int i, int j, double s) {
	      A[i][j] = s;
	   }

	 

	   public void setMatrix (int i0, int i1, int j0, int j1, Matrix X) {
	      try {
	         for (int i = i0; i <= i1; i++) {
	            for (int j = j0; j <= j1; j++) {
	               A[i][j] = X.get(i-i0,j-j0);
	            }
	         }
	      } catch(ArrayIndexOutOfBoundsException e) {
	         throw new ArrayIndexOutOfBoundsException("Submatrix indices");
	      }
	   }


	   public void setMatrix (int[] r, int[] c, Matrix X) {
	      try {
	         for (int i = 0; i < r.length; i++) {
	            for (int j = 0; j < c.length; j++) {
	               A[r[i]][c[j]] = X.get(i,j);
	            }
	         }
	      } catch(ArrayIndexOutOfBoundsException e) {
	         throw new ArrayIndexOutOfBoundsException("Submatrix indices");
	      }
	   }

	  

	   public void setMatrix (int[] r, int j0, int j1, Matrix X) {
	      try {
	         for (int i = 0; i < r.length; i++) {
	            for (int j = j0; j <= j1; j++) {
	               A[r[i]][j] = X.get(i,j-j0);
	            }
	         }
	      } catch(ArrayIndexOutOfBoundsException e) {
	         throw new ArrayIndexOutOfBoundsException("Submatrix indices");
	      }
	   }

	  

	   public void setMatrix (int i0, int i1, int[] c, Matrix X) {
	      try {
	         for (int i = i0; i <= i1; i++) {
	            for (int j = 0; j < c.length; j++) {
	               A[i][c[j]] = X.get(i-i0,j);
	            }
	         }
	      } catch(ArrayIndexOutOfBoundsException e) {
	         throw new ArrayIndexOutOfBoundsException("Submatrix indices");
	      }
	   }

	 
	   public Matrix transpose1 () {
	      Matrix X = new Matrix(n,m);
	      double[][] C = X.getArray();
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            C[j][i] = A[i][j];
	         }
	      }
	      return X;
	   }

	  

	   public double norm1 () {
	      double f = 0;
	      for (int j = 0; j < n; j++) {
	         double s = 0;
	         for (int i = 0; i < m; i++) {
	            s += Math.abs(A[i][j]);
	         }
	         f = Math.max(f,s);
	      }
	      return f;
	   }

	  

	   public double normInf () {
	      double f = 0;
	      for (int i = 0; i < m; i++) {
	         double s = 0;
	         for (int j = 0; j < n; j++) {
	            s += Math.abs(A[i][j]);
	         }
	         f = Math.max(f,s);
	      }
	      return f;
	   }

	 
	   public double normF () {
	      double f = 0;
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            f = Maths.hypot(f, A[i][j]);
	         }
	      }
	      return f;
	   }

	  

	   public Matrix uminus () {
	      Matrix X = new Matrix(m,n);
	      double[][] C = X.getArray();
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            C[i][j] = -A[i][j];
	         }
	      }
	      return X;
	   }

	   

	   public Matrix plus (Matrix B) {
	      checkMatrixDimensions(B);
	      Matrix X = new Matrix(m,n);
	      double[][] C = X.getArray();
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            C[i][j] = A[i][j] + B.A[i][j];
	         }
	      }
	      return X;
	   }

	  

	   public Matrix plusEquals (Matrix B) {
	      checkMatrixDimensions(B);
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            A[i][j] = A[i][j] + B.A[i][j];
	         }
	      }
	      return this;
	   }


	   public Matrix minus1 (Matrix B) {
	      checkMatrixDimensions(B);
	      Matrix X = new Matrix(m,n);
	      double[][] C = X.getArray();
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            C[i][j] = A[i][j] - B.A[i][j];
	         }
	      }
	      return X;
	   }


	   public Matrix minusEquals (Matrix B) {
	      checkMatrixDimensions(B);
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            A[i][j] = A[i][j] - B.A[i][j];
	         }
	      }
	      return this;
	   }

	  
	   public Matrix arrayTimes (Matrix B) {
	      checkMatrixDimensions(B);
	      Matrix X = new Matrix(m,n);
	      double[][] C = X.getArray();
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            C[i][j] = A[i][j] * B.A[i][j];
	         }
	      }
	      return X;
	   }

	   

	   public Matrix arrayTimesEquals (Matrix B) {
	      checkMatrixDimensions(B);
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            A[i][j] = A[i][j] * B.A[i][j];
	         }
	      }
	      return this;
	   }

	   

	   public Matrix arrayRightDivide (Matrix B) {
	      checkMatrixDimensions(B);
	      Matrix X = new Matrix(m,n);
	      double[][] C = X.getArray();
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            C[i][j] = A[i][j] / B.A[i][j];
	         }
	      }
	      return X;
	   }

	  

	   public Matrix arrayRightDivideEquals (Matrix B) {
	      checkMatrixDimensions(B);
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            A[i][j] = A[i][j] / B.A[i][j];
	         }
	      }
	      return this;
	   }


	   public Matrix arrayLeftDivide (Matrix B) {
	      checkMatrixDimensions(B);
	      Matrix X = new Matrix(m,n);
	      double[][] C = X.getArray();
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            C[i][j] = B.A[i][j] / A[i][j];
	         }
	      }
	      return X;
	   }

	  
	   public Matrix arrayLeftDivideEquals (Matrix B) {
	      checkMatrixDimensions(B);
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            A[i][j] = B.A[i][j] / A[i][j];
	         }
	      }
	      return this;
	   }

	  
	   public Matrix times (double s) {
	      Matrix X = new Matrix(m,n);
	      double[][] C = X.getArray();
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            C[i][j] = s*A[i][j];
	         }
	      }
	      return X;
	   }

	  

	   public Matrix timesEquals (double s) {
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            A[i][j] = s*A[i][j];
	         }
	      }
	      return this;
	   }

	   public Matrix times (Matrix B) {
	      if (B.m != n) {
	         throw new IllegalArgumentException("Matrix inner dimensions must agree.");
	      }
	      Matrix X = new Matrix(m,B.n);
	      double[][] C = X.getArray();
	      double[] Bcolj = new double[n];
	      for (int j = 0; j < B.n; j++) {
	         for (int k = 0; k < n; k++) {
	            Bcolj[k] = B.A[k][j];
	         }
	         for (int i = 0; i < m; i++) {
	            double[] Arowi = A[i];
	            double s = 0;
	            for (int k = 0; k < n; k++) {
	               s += Arowi[k]*Bcolj[k];
	            }
	            C[i][j] = s;
	         }
	      }
	      return X;
	   }

	 
	   

	  


	   public double trace () {
	      double t = 0;
	      for (int i = 0; i < Math.min(m,n); i++) {
	         t += A[i][i];
	      }
	      return t;
	   }


	   public static Matrix random (int m, int n) {
	      Matrix A = new Matrix(m,n);
	      double[][] X = A.getArray();
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            X[i][j] = Math.random();
	         }
	      }
	      return A;
	   }

	  

	   public static Matrix identity (int m, int n) {
	      Matrix A = new Matrix(m,n);
	      double[][] X = A.getArray();
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            X[i][j] = (i == j ? 1.0 : 0.0);
	         }
	      }
	      return A;
	   }



	   public void print (int w, int d) {
	      print(new PrintWriter(System.out,true),w,d); }


	   public void print (PrintWriter output, int w, int d) {
	      DecimalFormat format = new DecimalFormat();
	      format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
	      format.setMinimumIntegerDigits(1);
	      format.setMaximumFractionDigits(d);
	      format.setMinimumFractionDigits(d);
	      format.setGroupingUsed(false);
	      print(output,format,w+2);
	   }

	   

	   public void print (NumberFormat format, int width) {
	      print(new PrintWriter(System.out,true),format,width); }

	  

	   public void print (PrintWriter output, NumberFormat format, int width) {
	      output.println();  // start on new line.
	      for (int i = 0; i < m; i++) {
	         for (int j = 0; j < n; j++) {
	            String s = format.format(A[i][j]); // format the number
	            int padding = Math.max(1,width-s.length()); // At _least_ 1 space
	            for (int k = 0; k < padding; k++)
	               output.print(' ');
	            output.print(s);
	         }
	         output.println();
	      }
	      output.println();   // end with blank line.
	   }

	 

	   public static Matrix read (BufferedReader input) throws java.io.IOException {
	      StreamTokenizer tokenizer= new StreamTokenizer(input);


	      tokenizer.resetSyntax();
	      tokenizer.wordChars(0,255);
	      tokenizer.whitespaceChars(0, ' ');
	      tokenizer.eolIsSignificant(true);
	      java.util.Vector<Double> vD = new java.util.Vector<Double>();

	      // Ignore initial empty lines
	      while (tokenizer.nextToken() == StreamTokenizer.TT_EOL);
	      if (tokenizer.ttype == StreamTokenizer.TT_EOF)
		throw new java.io.IOException("Unexpected EOF on matrix read.");
	      do {
	         vD.addElement(Double.valueOf(tokenizer.sval)); // Read & store 1st row.
	      } while (tokenizer.nextToken() == StreamTokenizer.TT_WORD);

	      int n = vD.size();  // Now we've got the number of columns!
	      double row[] = new double[n];
	      for (int j=0; j<n; j++)  // extract the elements of the 1st row.
	         row[j]=vD.elementAt(j).doubleValue();
	      java.util.Vector<double[]> v = new java.util.Vector<double[]>();
	      v.addElement(row);  // Start storing rows instead of columns.
	      while (tokenizer.nextToken() == StreamTokenizer.TT_WORD) {
	         // While non-empty lines
	         v.addElement(row = new double[n]);
	         int j = 0;
	         do {
	            if (j >= n) throw new java.io.IOException
	               ("Row " + v.size() + " is too long.");
	            row[j++] = Double.valueOf(tokenizer.sval).doubleValue();
	         } while (tokenizer.nextToken() == StreamTokenizer.TT_WORD);
	         if (j < n) throw new java.io.IOException
	            ("Row " + v.size() + " is too short.");
	      }
	      int m = v.size();  // Now we've got the number of rows.
	      double[][] A = new double[m][];
	      v.copyInto(A);  // copy the rows out of the vector
	      return new Matrix(A);
	   }
	   
	//   public Matrix covariance(){
//		   // m - number of rows
//		   // n - number of columns
//		   double[] meanColumn = new double[n];
//		   int i,j;
//		   for(i = 0; i < n; i ++){
//			   int temp = 0;
//			   for(j = 0; j < m; j++)
//				   temp += A[j][i];
//			   meanColumn[i] = (double)temp / m;
//		   }
//		   
//		   double[][] meanA = new double[m][n];
//		   for(j = 0; j < m; j ++){
//			   for(i = 0; i < n; i++)
//				   meanA[j][i] = meanColumn[i];
//		   }
	//	
//		   Matrix X = this.minus(new Matrix(meanA));
//		   Matrix XT = X.transpose();
//		   return XT.times(X).times((double)1/m);
	//   }
	   

	/* ------------------------
	   Private Methods
	 * ------------------------ */

	   /** Check if size(A) == size(B) **/

	   private void checkMatrixDimensions (Matrix B) {
	      if (B.m != m || B.n != n) {
	         throw new IllegalArgumentException("Matrix dimensions must agree.");
	      }
	   }

	  private static final long serialVersionUID = 1;


	public EigenvalueDecomposition eig() {
		// TODO Auto-generated method stub
		return null;
	}
	


	public double[][] getArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getColumnDimension() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object transpose() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object minus(Matrix meanMatrix) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getRowDimension() {
		// TODO Auto-generated method stub
		return 0;
	}

}
