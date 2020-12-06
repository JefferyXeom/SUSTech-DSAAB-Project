public class Matrix {
    double[] matrix;
    int row=0,col=0;
    public Matrix(double[][] mat){
        matrix = new double[mat.length*mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                matrix[i*mat[0].length+j]=mat[i][j];
            }
        }
    }
}
