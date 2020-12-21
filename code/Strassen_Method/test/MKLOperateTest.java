import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MKLOperateTest {
    static Matrix[] mat1,mat2,mat3;
    static Matrix matAdd,matMult,MatAddNative,MatMultNative;
    @BeforeAll
    static void beforeAll() {
        mat1=new Matrix[20];
        mat2 = new Matrix[20];
        mat3 = new Matrix[20];
        for (int i = 0; i < 20; i++) {
            mat1[i] = new Matrix(i*83+6,true);
            mat2[i] = new Matrix(i*83+6,true);
            mat3[i] = new Matrix(i*83+6, false);
        }
    }

    @Test
    void mklAdd() {
        for (int i = 0; i < mat1.length; i++) {
            MatAddNative=MatrixMethod.Add(mat1[i], mat2[i]);
            matAdd=MKLOperate.MKLAdd(mat1[i], mat2[i]);
            for (int j = 0; j < MatAddNative.getMatrix().length; j++) {
                assertEquals(MatAddNative.getMatrix()[j], matAdd.getMatrix()[j]);
            }
            MatAddNative=null;
            matAdd=null;
        }
    }

    @org.junit.jupiter.api.Test
    void mklMultiple() {
        for (int i = 0; i < mat1.length; i++) {
            MatMultNative=MatrixMethod.Multiple(mat1[i], mat2[i]);
            matMult=MKLOperate.MKLMultiple(mat1[i], mat2[i]);
            for (int j = 0; j < MatMultNative.getMatrix().length; j++) {
                assertEquals(Math.abs(MatMultNative.getMatrix()[j]-matMult.getMatrix()[j])<1E-10,true );
            }
            MatMultNative=null;
            matMult=null;
        }
    }
    @Test
    void mklMultTest(){
        for (int i = 0; i < mat1.length; i++) {
            long t1=System.currentTimeMillis();
            Matrix MKLMult=MKLOperate.MKLMultiple(mat1[i], mat2[i]);
            MKLMult=null;
            long tm=System.currentTimeMillis();
            System.out.println((double)(tm-t1)/1000.0+"s");
        }
        MatMultNative=MatrixMethod.Multiple(mat1[0], mat2[0]);
        Matrix MKLMult=MKLOperate.MKLMultiple(mat1[0], mat2[0]);
        Matrix Subst=MatrixMethod.Minus(MatMultNative,MKLMult);
        Subst.print();
        Subst=MKLOperate.MKLMinus(MatMultNative,MKLMult);
        Subst.print();
    }

}