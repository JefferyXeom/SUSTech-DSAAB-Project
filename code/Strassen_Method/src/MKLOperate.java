// MKL operation interface
public class MKLOperate {


    // construction of the method
    public MKLOperate() {
    }

    public native double[] mklAdd(double[] Matrix1, double[] Matrix2);

    public native double[] mklMultiple(double[] Matrix1, double[] Matrix2);


    public static Matrix MKLAdd(Matrix matrix1, Matrix matrix2) {
        return new Matrix(MKLAdds(matrix1.getMatrix(), matrix2.getMatrix()));
    }

    private static double[] MKLAdds(double[] Matrix1, double[] Matrix2) {
        MKLOperate mkl = new MKLOperate();
        return mkl.mklAdd(Matrix1, Matrix2);
    }


    public static Matrix MKLMultiple(Matrix matrix1, Matrix matrix2) {
        return new Matrix(MKLMultiples(matrix1.getMatrix(), matrix2.getMatrix()));

    }

    public static double[] MKLMultiples(double[] Matrix1, double[] Matrix2) {
        MKLOperate mkl = new MKLOperate();
        return mkl.mklMultiple(Matrix1, Matrix2);
    }
}