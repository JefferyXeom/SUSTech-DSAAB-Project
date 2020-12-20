// dllmain.cpp : 定义 DLL 应用程序的入口点。
#include "pch.h"
#include "MKLOperate.h"
using namespace std;

JNIEXPORT jdoubleArray JNICALL Java_MKLOperate_mklAdd(JNIEnv *env, jobject jobj, jdoubleArray mat1, jdoubleArray mat2)
{
    double *A, *B, *C;
    int a = 1, b = 0;
    int length = env->GetArrayLength(mat1);
    int rowa = sqrt(length);
    int rowb = rowa;
    A = (double *) MKL_malloc(rowa * rowa * sizeof(double), 64);
    B = (double *) MKL_malloc(rowa * rowb * sizeof(double), 64);
    C = (double *) MKL_malloc(rowb * rowb * sizeof(double), 64);
    double *buffer = env->GetDoubleArrayElements(mat1, NULL);
    for (int i = 0; i < length; ++i)
    {
        A[i] = buffer[i];
    }
    double *buffer2 = env->GetDoubleArrayElements(mat2, NULL);
    for (int i = 0; i < length; ++i)
    {
        B[i] = buffer2[i];
    }
    env->ReleaseDoubleArrayElements(mat1,buffer, JNI_COMMIT);
    env->ReleaseDoubleArrayElements(mat2, buffer2, JNI_COMMIT);
    cblas_dgemm(CblasRowMajor, CblasNoTrans, CblasNoTrans, rowa, rowb, rowa, 1, A, rowa, B, rowb, b, C, rowb);
    jdoubleArray result = env->NewDoubleArray(length);
    env->SetDoubleArrayRegion(result, 0, length, C);
    MKL_free(A);
    A = nullptr;
    MKL_free(B);
    B = nullptr;
    MKL_free(C);
    C = nullptr;
    return result;
}
JNIEXPORT jdoubleArray JNICALL Java_MKLOperate_mklMultiple(JNIEnv *env, jobject obj, jdoubleArray mat1, jdoubleArray mat2)
{
    int length = env->GetArrayLength(mat1);
    jdoubleArray result = env->NewDoubleArray(length);
    double *Matr1 = env->GetDoubleArrayElements(mat1, NULL);
    double *Matr2 = env->GetDoubleArrayElements(mat2, NULL);
    double *Res = (double*)malloc(length * sizeof(double));
    for (int i = 0; i < length; ++i)
    {
        Res[i] = Matr1[i] + Matr2[i];
    }
    env->SetDoubleArrayRegion(result, 0, length, Res);
    env->ReleaseDoubleArrayElements(mat1, Matr1, JNI_COMMIT);
    env->ReleaseDoubleArrayElements(mat2, Matr2, JNI_COMMIT);
    free(Res);
    Res = nullptr;
    return result;
    
}
