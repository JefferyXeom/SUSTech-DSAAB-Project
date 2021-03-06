#include "mkldgemm.h"

int mkldgemm(int rowa, int cola, int colb) {
    using namespace std;
    double *A, *B, *C;
    int a = 1, b = 1;
    A = (double *) MKL_malloc(rowa * cola * sizeof(double), 64);
    B = (double *) MKL_malloc(cola * colb * sizeof(double), 64);
    C = (double *) MKL_malloc(rowa * colb * sizeof(double), 64);
    for (int i = 0; i < rowa * cola; ++i) {
        A[i] = randgen(-500, 500);
        }
    for (int i = 0; i < cola * colb; ++i) {
        B[i] = randgen(-500, 500);
        }
    for (int i = 0; i < rowa * colb; ++i) {
        C[i] = randgen(-650, 390) * randgen(-500, 500);
        }
    cblas_dgemm(CblasRowMajor, CblasNoTrans, CblasNoTrans, rowa, colb, cola, 1, A, cola, B, colb, b, C, colb);
    MKL_free(A);
    MKL_free(B);
    MKL_free(C);
    return 0;
    }