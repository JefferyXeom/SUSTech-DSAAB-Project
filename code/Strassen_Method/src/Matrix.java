import java.util.Random;

public class Matrix {
    double[] matrix;
    int row, col;


    // generate random matrices ( all zero matrix and all non-zero matrix )
    public Matrix(int n, boolean isFilled) {
        row = n;
        col = n;
        Random random = new Random();
        matrix = new double[n * n];
        for (int i = 0; i < n * n; i++) {
            matrix[i] = isFilled ? random.nextDouble() : 0;
        }
    }


    // generate matrix by desired 1-D array
    public Matrix(double[] matrix) {
        if (isSquare(matrix.length)) {
            this.matrix = matrix;
        }
    }

    // is square test
    private static boolean isSquare(int num) {
        double res = Math.sqrt(num);
        int chk = (int) res;
        return Math.abs(res - (double) chk) <= 0.00000001;
    }


    // return the double[] array for each matrix
    public double[] getMatrix() {
        return matrix;
    }

    public void print() {
        int n = (int) Math.sqrt(matrix.length);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i * n + j] + " ");
            }
            System.out.println();
        }
    }
}