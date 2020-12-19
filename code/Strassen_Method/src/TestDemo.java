public class TestDemo {
    public static void main(String[] args) throws Exception {
        MatrixInt matrixInt = new MatrixInt();
        int[][] A = matrixInt.createMatrix(4,4);
        int[][] B = matrixInt.createMatrix(4,4);
        long t0 = System.currentTimeMillis();
        int[][] C = matrixInt.multiply(A,B);
        System.out.println(System.currentTimeMillis()-t0);
        long t1 = System.currentTimeMillis();
        int[][] D = matrixInt.strassenSquareMultiply(A,B);
        System.out.println(System.currentTimeMillis()-t1);
        System.out.println("The matrix A: ");
        for (int i = 0; i < A.length; i++){
            for (int j = 0; j < A[0].length; j++){
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }




        System.out.println("\nThe matrix B: ");
        for (int i = 0; i < B.length; i++){
            for (int j = 0; j < B[0].length; j++){
                System.out.print(B[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nThe matrix C: ");
        for (int i = 0; i < C.length; i++){
            for (int j = 0; j < C[0].length; j++){
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nThe matrix D: ");
        for (int i = 0; i < D.length; i++){
            for (int j = 0; j < D[0].length; j++){
                System.out.print(D[i][j] + " ");
            }
            System.out.println();
        }
    }
}
