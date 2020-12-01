#include <cstdlib>
#include <cstdio>
#include <mkl.h>

int main(int argc, char* argv[])
{
    float* A, * B, * C;
    int m = 2, n = 5, a = 1;
    A = (float*)MKL_malloc(m * n * sizeof(float), 64);
    B = (float*)MKL_malloc(m * sizeof(float), 64);
    C = (float*)MKL_malloc(n * sizeof(float), 64);
    printf("数组为\n");
    for (int i = 0; i < m * n; i++) {
        if (i % n == 0 && i != 0)
            printf("\n");

        A[i] = 1;
        printf("%2.0f", A[i]);
    }
    printf("\n");
    printf("向量1为\n");
    for (int i = 0; i < m; i++)
    {
        B[i] = i + 1;
        printf("%2.0f", B[i]);
    }
    printf("\n");
    printf("向量2为\n");
    for (int i = 0; i < n; i++)
    {
        C[i] = i + 2;
        printf("%2.0f", C[i]);
    }
    printf("\n");
    cblas_sger(CblasRowMajor, m, n, a, B, 1, C, 1, A, n);
    printf("向量-向量相乘+矩阵的结果\n");
    for (int i = 0; i < m * n; i++) {
        if (i % n == 0 && i != 0)
            printf("\n");

        printf("%2.0f ", A[i]);
    }
    mkl_free(A);
    mkl_free(B);
    mkl_free(C);
    getchar();
    return 0;
}
