import java.util.Date;

public class Test2 {
    public static void main(String[] args) throws Exception {
        Algorithm algorithm = new Algorithm();
        double[][] A = algorithm.createMatrix(2,2);
        double[][] B = algorithm.createMatrix(2,2);
        double[][] C = algorithm.multiply(A,B);
        double[][] D = algorithm.strassenSquareMultiply(A,B);
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
