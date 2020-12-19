
public class Algorithm {

    /**
    *@Description Initial a matrix, each element is between -1 and 1
    *@Param m is the number of rows, and n is the number of columns
    *@Return A random matrix
    *@Author Sherlock.Xiao
    */
    public double[][] createMatrix (int m,int n)throws Exception{
        if (n <= 0 || m <= 0){
            throw new Exception("The dimension of matrix should be positive !");
        }
        double[][] matrix  = new double[m][n];
        for (int i = 0; i < m; i++){
           for (int j = 0; j < n; j++){
               double sign = Math.random();
               matrix[i][j] = (sign > 0.5) ? Math.random() : Math.random();
           }
       }
       return matrix;
    }

    /**
    *@Description Calculate the product of Matrix A and B by BruteForce method
    *@Param Matrix A and B
    *@Return Product of A and B
    *@Author Sherlock.Xiao
    */
    public double[][] multiply(double[][] A, double[][] B)throws Exception{
        int rowOfA = A.length, colOfA = A[0].length;
        int rowOfB = B.length, colOfB = B[0].length;

        if (colOfA != rowOfB){
            throw new Exception("These two matrice can not multiply due to the difference of dimension !");
        }

        double[][] result = new double[rowOfA][colOfB];
        for (int i = 0; i < rowOfA; i++){
            for (int j = 0; j < colOfB; j++){
                for (int k = 0; k < colOfA; k++){
                    result[i][j] = A[i][k]*B[k][j];
                }
            }
        }
        return result;
    }

    /**
    *@Description Calculate the product of square matrix A and B by Strassen's Method.
    *@Param Matrix A and B must be square, otherwise we have to transform it into a square form before executing
    *@Return Product
    *@Author Sherlock.Xiao
    */
    public double[][] strassenSquareMultiply(double[][] A, double[][] B) throws Exception {
        int rowA = A.length, colA = A[0].length;
        int rowB = B.length, colB = B[0].length;

        if (rowA != colA || rowB != colB || rowA != rowB){
            throw new Exception("The matrice are not both square or the same size !");
        }
        double[][] C = new double[rowA][colA];
        if (rowA == 1){
            C[0][0] = A[0][0]*B[0][0];
            return C;
        }

//        //in case of n = 2, the BruteForce method is faster
//        if (rowA == 2 && rowB == 2){
//            return multiply(A,B);
//        }


        double[][] A11 = divideSquareMatrix(A,1);
        double[][] A12 = divideSquareMatrix(A,2);
        double[][] A21 = divideSquareMatrix(A,3);
        double[][] A22 = divideSquareMatrix(A,4);
        double[][] B11 = divideSquareMatrix(B,1);
        double[][] B12 = divideSquareMatrix(B,2);
        double[][] B21 = divideSquareMatrix(B,3);
        double[][] B22 = divideSquareMatrix(B,4);

        int n = rowA/2;
        double[][][] S = new double[10][n][n];
        S[0] = minus(B12,B22);
        S[1] = add(A11,A12);
        S[2] = add(A21,A22);
        S[3] = minus(B21,B11);
        S[4] = add(A11,A22);
        S[5] = add(B11,B22);
        S[6] = minus(A12,A22);
        S[7] = add(B21,B22);
        S[8] = minus(A11,A21);
        S[9] = add(B11,B12);

        double[][][] P = new double[7][n][n];
        P[0] = strassenSquareMultiply(A11,S[0]);
        P[1] = strassenSquareMultiply(S[1],B22);
        P[2] = strassenSquareMultiply(S[2],B11);
        P[3] = strassenSquareMultiply(A22,S[3]);
        P[4] = strassenSquareMultiply(S[4],S[5]);
        P[5] = strassenSquareMultiply(S[6],S[7]);
        P[6] = strassenSquareMultiply(S[8],S[9]);

        double[][] C11 = add(minus(add(P[3],P[4]),P[1]),P[5]);
        double[][] C12 = add(P[0],P[1]);
        double[][] C21 = add(P[2],P[3]);
        double[][] C22 = minus(minus(add(P[4],P[0]),P[2]),P[6]);
        C = mergeFourMatrix(C11,C12,C21,C22);

        return C;
    }

    public double[][] add(double[][] M, double[][] N)throws Exception{
        int rowM = M.length, colM = M[0].length;
        int rowN = N.length, colN = N[0].length;

        if (rowM != rowN || colM != colN){
            throw new Exception("The dimensions of the matrice should be the same!");
        }

        double[][] result = new double[rowM][colM];
        for (int i = 0; i < rowM; i++){
            for (int j = 0; j < colM; j++){
                result[i][j] = M[i][j] + N[i][j];
            }
        }
        return result;
    }

    public double[][] minus(double[][] M, double[][] N)throws Exception{
        int rowOfM = M.length, colOfM = M[0].length;
        int rowOfN = N.length, colOfN = N[0].length;

        if (rowOfM != rowOfN || colOfM != colOfN){
            throw new Exception("The dimensions of the matrice should be the same!");
        }

        double[][] result = new double[rowOfM][colOfM];
        for (int i = 0; i < rowOfM; i++){
            for (int j = 0; j < colOfM; j++){
                result[i][j] = M[i][j] - N[i][j];
            }
        }
        return result;
    }

    /**
    *@Description Divide the matrix into 4 submatrice
    *@Param A is the matrix to be divided, k determines which block of matrix will be returned.
     * k = 1  --> UpperLeft block submatrix A11
     * k = 2  --> UpperRight block submatrix A12
     * k = 3  --> LowerLeft block submatrix A12
     * k = 4  --> LowerRight block submatrix A22
    *@Return submatrix
    *@Author Sherlock.Xiao
    */
    public double[][] divideSquareMatrix(double[][] A, int k)throws Exception{
        int m = A.length, n = A[0].length;
        if (m != n){
            throw new Exception("This matrix is not square !");
        }
        int row = m/2, col = n/2;
        double[][] result = new double[row][col];
        if (k == 1){
            for (int i = 0; i < row; i++){
                for (int j = 0; j < col; j++){
                    result[i][j] = A[i][j];
                }
            }
        }else if (k == 2){
            for (int i = 0; i < row; i++){
                for (int j = 0; j < n - col; j++){
                    result[i][j] = A[i][col+j];
                }
            }
        }else if (k == 3){
            for (int i = 0; i < m - row; i++){
                for (int j = 0; j < col; j++){
                    result[i][j] = A[row+i][j];
                }
            }
        }else if (k == 4){
            for (int i = 0; i < m - row; i++){
                for (int j = 0; j < n - col; j++){
                    result[i][j] = A[row+i][col+j];
                }
            }
        }else {
            throw new Exception("The parameter k is not correct !");
        }
        return result;
    }

    /**
    *@Description Combine 4 block submatrix into a big matrix.
    *@Param
     * UpperLeft block submatrix A11
     * UpperRight block submatrix A12
     * LowerLeft block submatrix A12
     * LowerRight block submatrix A22
    *@Return Matrix
    *@Author Sherlock.Xiao
    */
    public double[][] mergeFourMatrix(double[][] A11, double[][] A12, double[][] A21, double[][] A22)throws Exception{
        int m = A11.length;
        int n = A11[0].length;
        double[][] result = new double[2*m][2*n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                result[i][j] = A11[i][j];
                result[i][n + j] = A12[i][j];
                result[m + i][j] = A21[i][j];
                result[m + i][n + j] = A22[i][j];
            }
        }
        return result;
    }

}
