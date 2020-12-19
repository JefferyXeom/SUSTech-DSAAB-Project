import java.lang.management.MemoryManagerMXBean;

public class FiTest {
    public static void main(String[] args) throws Exception {
        Matrix matrix1 = new Matrix(8, true);
        Matrix matrix2 = new Matrix(8, true);
        Matrix matrix3;
        Matrix matrix4;
        Matrix matrix5;
        Matrix matrix6;
        matrix3 = MatrixMethod.Add(matrix1, matrix2);
        matrix3.print();
        System.out.println();
        matrix4 = MatrixMethod.Multiple(matrix1, matrix2);
        matrix4.print();
        System.out.println();
        matrix5 = MatrixMethod.stranssenMultiple(matrix1, matrix2);
        matrix5.print();
        System.out.println();
        matrix6 = MatrixMethod.Minus(matrix4, matrix5);
        matrix6.print();


//        MKLOperate.MKLAdd(matrix1,matrix2);
//        MKLOperate.MKLMultiple(matrix1,matrix2);


    }

}
