#include "vmv.h"
#include "randgen.h"

void vmv(int sizeofrow,int sizeofcol)
{
    float *A, *B, *C;
    int m = sizeofrow, n = sizeofcol, a = 1;
    A = (float *) MKL_malloc(m * n * sizeof(float), 64);
    B = (float *) MKL_malloc(m * sizeof(float), 64);
    C = (float *) MKL_malloc(n * sizeof(float), 64);
    for (int i = 0; i < m * n; i++) {
        A[i] = randgen(100,300);
        }
    for (int i = 0; i < m; i++) {
        B[i] = randgen(200,400);
        }
    for (int i = 0; i < n; i++) {
        C[i] = randgen(300,800);
        }
    printf("\n");
    cblas_sger(CblasRowMajor, m, n, a, B, 1, C, 1, A, n);
    mkl_free(A);
    mkl_free(B);
    mkl_free(C);
}
