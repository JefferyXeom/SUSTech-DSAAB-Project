
// this includes all the method we need here
public class MatrixMethod {


    // construction
    public MatrixMethod() {
    }


    // naiveAdd method
    public static Matrix Add(Matrix A, Matrix B) {
        return new Matrix(naiveAdd(A.getMatrix(), B.getMatrix()));
    }

    private static double[] naiveAdd(double[] A, double[] B) {
        int n = (int) Math.sqrt(A.length);
        double[] C = new double[A.length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i * n + j] = A[i * n + j] + B[i * n + j];
            }
        }
        return C;
    }

    public static Matrix Minus(Matrix A, Matrix B) {
        return new Matrix(naiveMinus(A.getMatrix(), B.getMatrix()));
    }

    private static double[] naiveMinus(double[] A, double[] B) {
        int n = (int) Math.sqrt(A.length);
        double[] C = new double[A.length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i * n + j] = A[i * n + j] - B[i * n + j];
                if (Math.abs(C[i * n + j]) < 0.0000001)
                    C[i * n + j] = 0;
            }

        }
        return C;
    }

    // multiple method
    public static Matrix Multiple(Matrix A, Matrix B) {
        return new Matrix(naiveMultiple(A.getMatrix(), B.getMatrix()));
    }

    private static double[] naiveMultiple(double[] A, double[] B) {
        int n = (int) Math.sqrt(A.length);
        double[] C = new double[A.length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i * n + j] += A[i * n + k] * B[k * n + j];
                }
            }
        }
        return C;
    }

    // stranssen multiple method
    public static Matrix stranssenMultiple(Matrix A, Matrix B) throws Exception {
        return new Matrix(stranssenMultiples(A.getMatrix(), B.getMatrix()));
    }

    private static double[] stranssenMultiples(double[] A, double[] B) throws Exception {
        int n = (int) Math.sqrt(A.length);
        double[] C = new double[A.length];

        if (n == 1) {
            C[0] = A[0] * B[0];
            return C;
        }

        double[] A11 = divideSquareMatrix(A, 1);
        double[] A12 = divideSquareMatrix(A, 2);
        double[] A21 = divideSquareMatrix(A, 3);
        double[] A22 = divideSquareMatrix(A, 4);
        double[] B11 = divideSquareMatrix(B, 1);
        double[] B12 = divideSquareMatrix(B, 2);
        double[] B21 = divideSquareMatrix(B, 3);
        double[] B22 = divideSquareMatrix(B, 4);

        int m = n / 2;
        double[][] S = new double[10][m * m];
        S[0] = naiveMinus(B12, B22);
        S[1] = naiveAdd(A11, A12);
        S[2] = naiveAdd(A21, A22);
        S[3] = naiveMinus(B21, B11);
        S[4] = naiveAdd(A11, A22);
        S[5] = naiveAdd(B11, B22);
        S[6] = naiveMinus(A12, A22);
        S[7] = naiveAdd(B21, B22);
        S[8] = naiveMinus(A11, A21);
        S[9] = naiveAdd(B11, B12);

        double[][] P = new double[7][m * m];
        P[0] = stranssenMultiples(A11, S[0]);
        P[1] = stranssenMultiples(S[1], B22);
        P[2] = stranssenMultiples(S[2], B11);
        P[3] = stranssenMultiples(A22, S[3]);
        P[4] = stranssenMultiples(S[4], S[5]);
        P[5] = stranssenMultiples(S[6], S[7]);
        P[6] = stranssenMultiples(S[8], S[9]);

        double[] C11 = naiveAdd(naiveMinus(naiveAdd(P[3], P[4]), P[1]), P[5]);
        double[] C12 = naiveAdd(P[0], P[1]);
        double[] C21 = naiveAdd(P[2], P[3]);
        double[] C22 = naiveMinus(naiveMinus(naiveAdd(P[4], P[0]), P[2]), P[6]);
        C = mergeFourMatrix(C11, C12, C21, C22);

        return C;

    }

    public static double[] divideSquareMatrix(double[] A, int k) throws Exception {
        int n = (int) Math.sqrt(A.length);
//        if (m != n) {
//            throw new Exception("This matrix is not square !");
//        }
//      int row = m / 2,
        int m = n / 2;
        double[] result = new double[m * m];
        if (k == 1) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    result[i * m + j] = A[i * 2 * m + j];
                }
            }
        } else if (k == 2) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n - m; j++) {
                    result[i * m + j] = A[i * 2 * m + m + j];
                }
            }
        } else if (k == 3) {
            for (int i = 0; i < n - m; i++) {
                for (int j = 0; j < m; j++) {
                    result[i * m + j] = A[(m + i) * 2 * m + j];
                }
            }
        } else if (k == 4) {
            for (int i = 0; i < n - m; i++) {
                for (int j = 0; j < n - m; j++) {
                    result[i * m + j] = A[(m + i) * 2 * m + m + j];
                }
            }
        } else {
            throw new Exception("The parameter k is not correct !");
        }
        return result;
    }


    public static double[] mergeFourMatrix(double[] A11, double[] A12, double[] A21, double[] A22) throws Exception {
        int m = A11.length; // m = total length
        double[] result = new double[4 * m];
        m = (int) Math.sqrt(m); // m = size
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                result[i * 2 * m + j] = A11[i * m + j];
                result[i * 2 * m + m + j] = A12[i * m + j];
                result[(m + i) * 2 * m + j] = A21[i * m + j];
                result[(m + i) * 2 * m + m + j] = A22[i * m + j];
            }
        }
        return result;
    }

}
