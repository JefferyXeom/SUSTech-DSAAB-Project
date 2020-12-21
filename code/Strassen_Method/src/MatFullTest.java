public class MatFullTest {
    static Matrix mata1,mata2,matzeros;
    static Matrix naiveMult,StrassenMult,MKLMult;
    static int order0=102,threshold=32;
    static long t1=0,t2=0,ts=0,tm=0;
    static double TN,TS,TM;

    public static void main(String[] args) throws Exception {
        System.out.println("Strassen Threshold: 64");
        MatrixMethod.setStranssenThreshold(true);
        MatrixMethod.setThreshold(64);
        System.out.println("   Size   T(N)    T(S)    T(M)");
//        for (int i = 2; i < 512 ; i+=51) {
//            mata1=new Matrix(i,true);
//            mata2=new Matrix(i,true);
//            matzeros=new Matrix(i,false);
//            t1=System.currentTimeMillis();
//            naiveMult=MatrixMethod.Multiple(mata1, mata2);
//            t2=System.currentTimeMillis();
//            TN=(double)(t2-t1)/1000.0;
//            t1=0;
//            t1=System.currentTimeMillis();
//            StrassenMult=MatrixMethod.stranssenMultiple(mata1, mata2);
//            ts=System.currentTimeMillis();
//            TS=(double)(ts-t1)/1000.0;
//            t1=0;
//            t1=System.currentTimeMillis();
//            MKLMult=MKLOperate.MKLMultiple(mata1, mata2);
//            tm=System.currentTimeMillis();
//            TM=(double)(tm-t1)/1000.0;
//            naiveMult=null;
//            StrassenMult=null;
//            MKLMult = null;
//            System.out.printf("   %-4d   %-3.3f   %-3.3f   %-3.3f\n",i,TN,TS,TM);
//        }
//        for (int i = 512; i < 1024 ; i+=50) {
//            mata1=new Matrix(i,true);
//            mata2=new Matrix(i,true);
//            matzeros=new Matrix(i,false);
//            t1=System.currentTimeMillis();
//            naiveMult=MatrixMethod.Multiple(mata1, mata2);
//            t2=System.currentTimeMillis();
//            TN=(double)(t2-t1)/1000.0;
//            t1=0;
//            t1=System.currentTimeMillis();
//            StrassenMult=MatrixMethod.stranssenMultiple(mata1, mata2);
//            ts=System.currentTimeMillis();
//            TS=(double)(ts-t1)/1000.0;
//            t1=0;
//            t1=System.currentTimeMillis();
//            MKLMult=MKLOperate.MKLMultiple(mata1, mata2);
//            tm=System.currentTimeMillis();
//            TM=(double)(tm-t1)/1000.0;
//            naiveMult=null;
//            StrassenMult=null;
//            MKLMult = null;
//            System.out.printf("   %-4d   %-3.3f   %-3.3f   %-3.3f\n",i,TN,TS,TM);
//        }
//        for (int i = 1024; i < 2048 ; i+=100) {
//            mata1=new Matrix(i,true);
//            mata2=new Matrix(i,true);
//            matzeros=new Matrix(i,false);
//            t1=System.currentTimeMillis();
//            naiveMult=MatrixMethod.Multiple(mata1, mata2);
//            t2=System.currentTimeMillis();
//            TN=(double)(t2-t1)/1000.0;
//            t1=0;
//            t1=System.currentTimeMillis();
//            StrassenMult=MatrixMethod.stranssenMultiple(mata1, mata2);
//            ts=System.currentTimeMillis();
//            TS=(double)(ts-t1)/1000.0;
//            t1=0;
//            t1=System.currentTimeMillis();
//            MKLMult=MKLOperate.MKLMultiple(mata1, mata2);
//            tm=System.currentTimeMillis();
//            TM=(double)(tm-t1)/1000.0;
//            naiveMult=null;
//            StrassenMult=null;
//            MKLMult = null;
//            System.out.printf("   %-4d   %-3.3f   %-3.3f   %-3.3f\n",i,TN,TS,TM);
//        }
//        for (int i = 2048; i < 4096 ; i+=200) {
//            mata1=new Matrix(i,true);
//            mata2=new Matrix(i,true);
//            matzeros=new Matrix(i,false);
//            t1=System.currentTimeMillis();
//            naiveMult=MatrixMethod.Multiple(mata1, mata2);
//            t2=System.currentTimeMillis();
//            TN=(double)(t2-t1)/1000.0;
//            t1=0;
//            t1=System.currentTimeMillis();
//            StrassenMult=MatrixMethod.stranssenMultiple(mata1, mata2);
//            ts=System.currentTimeMillis();
//            TS=(double)(ts-t1)/1000.0;
//            t1=0;
//            t1=System.currentTimeMillis();
//            MKLMult=MKLOperate.MKLMultiple(mata1, mata2);
//            tm=System.currentTimeMillis();
//            TM=(double)(tm-t1)/1000.0;
//            naiveMult=null;
//            StrassenMult=null;
//            MKLMult = null;
//            System.out.printf("   %-4d   %-3.3f   %-3.3f   %-3.3f\n",i,TN,TS,TM);
//        }
        for (int i = 6096; i < 7096 ; i+=1000) {
            mata1=new Matrix(i,true);
            mata2=new Matrix(i,true);
            matzeros=new Matrix(i,false);
            t1=System.currentTimeMillis();
            naiveMult=MatrixMethod.Multiple(mata1, mata2);
            t2=System.currentTimeMillis();
            TN=(double)(t2-t1)/1000.0;
            t1=0;
            t1=System.currentTimeMillis();
            StrassenMult=MatrixMethod.stranssenMultiple(mata1, mata2);
            ts=System.currentTimeMillis();
            TS=(double)(ts-t1)/1000.0;
            t1=0;
            t1=System.currentTimeMillis();
            MKLMult=MKLOperate.MKLMultiple(mata1, mata2);
            tm=System.currentTimeMillis();
            TM=(double)(tm-t1)/1000.0;
            naiveMult=null;
            StrassenMult=null;
            MKLMult = null;
            System.out.printf("   %-4d   %-3.3f   %-3.3f   %-3.3f\n",i,TN,TS,TM);
        }
    }
}
