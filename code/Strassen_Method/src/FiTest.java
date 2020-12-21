
public class FiTest {
    public static void main(String[] args) throws Exception {
        Matrix matrix1 = new Matrix(1000, true);
        Matrix matrix2 = new Matrix(1000, true);
        Matrix matrix3;
        Matrix matrix4;
        Matrix matrix5;
        Matrix matrix6;
        Matrix matrix7;
        Matrix matrix8;
//        matrix3 = MatrixMethod.Add(matrix1, matrix2);
//        matrix3.print();
//        System.out.println();

//        System.out.println("matrix1");
//        long timer1 = System.currentTimeMillis();
//        System.out.println(timer1);
//        matrix4 = MatrixMethod.Multiple(matrix1, matrix2); // matrix4
////        matrix4.print();
////        System.out.println();
//        long timer2 = System.currentTimeMillis();
//        System.out.println(timer2);
//        System.out.println(timer2 - timer1);
//
//        System.out.println("matrix2");
//        long timer3 = System.currentTimeMillis();
//        System.out.println(timer3);
//        MatrixMethod.setStranssenThreshold(true);
//        MatrixMethod.setThreshold(32);
//        matrix5 = new Matrix(MatrixMethod.naiveSubMultiple(matrix1.getMatrix(), matrix2.getMatrix()));// matrix5
//        long timer4 = System.currentTimeMillis();
//        System.out.println(timer4);
//        System.out.println(timer4 - timer3);
////        matrix5.print();
////        System.out.println();
//

        System.out.println("matrix3");
        long timer5 = System.currentTimeMillis();
        System.out.println(timer5);
        MatrixMethod.setStranssenThreshold(false);
        MatrixMethod.setThreshold(32);
        matrix7 = MatrixMethod.stranssenMultiple(matrix1, matrix2);// matrix7

        long timer6 = System.currentTimeMillis();
        System.out.println(timer5);
        System.out.println(timer6 - timer5);


//        matrix6 = MatrixMethod.Minus(matrix4, matrix5);
//        System.out.println("matrix6");
//        System.out.println(MatrixMethod.testAccuracy(matrix6));
//
//        matrix8 = MatrixMethod.Minus(matrix4,matrix7);
//        System.out.println(MatrixMethod.testAccuracy(matrix8));

//       matrix6.print();


        /* odd test
        System.out.println("\n");
        System.out.println("test2 on");
        Matrix matrix11 = new Matrix(53, true);
        Matrix matrix22 = new Matrix(53, true);
        Matrix matrix44;
        Matrix matrix55;
        Matrix matrix66;

        matrix44 = MatrixMethod.Multiple(matrix11, matrix22);
//        System.out.println("matrix44");
//        matrix44.print();
//        System.out.println();

        matrix55 = MatrixMethod.stranssenMultiple(matrix11, matrix22);
        System.out.println("matrix55");
//        matrix55.print();
//        System.out.println();

//        System.out.println("extended test");
//        System.out.println(MatrixMethod.getExtended());
//        System.out.println();

        matrix66 = MatrixMethod.Minus(matrix44, matrix55);
        System.out.println("matrix66");
        matrix66.print();
        */
        //odd test end

//        System.out.println("extended test2");
//        System.out.println(MatrixMethod.getExtended());
//        MKLOperate.MKLAdd(matrix1,matrix2);
//        MKLOperate.MKLMultiple(matrix1,matrix2);


    }
}
