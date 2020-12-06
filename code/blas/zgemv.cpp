#include "zgemv.h"

int zgemv(int rows,int cols) {
    using namespace std;
    typedef complex<double> Comp; // 定义复数类型
    int Nr = rows, Nc = cols; // 矩阵行数和列数
    // 矩阵和矢量分配内存
    Comp *a = new Comp[Nr * Nc];
    Comp *x = new Comp[Nc];
    Comp *y = new Comp[Nr];
    Comp alpha(1, 0), beta(0, 0);
    // x 矢量赋值
    for (int i = 0; i < Nc; ++i) {
        x[i] = Comp(randgen(i*-100,i*919)+102*i, randgen(i*-298,i*451)*1.3*i);
        }
    // a 矩阵赋值
    for (int i = 0; i < Nr * Nc; ++i) {
        a[i] = Comp(randgen(i*-234,i*628)*0.78*i, randgen(i*-729,i*133) + 2*34.5*i);
        }
    // 做乘法
    cblas_zgemv(CblasRowMajor, CblasNoTrans, Nr, Nc, &alpha, a,
        Nr, x, 1, &beta, y, 1);

    free(a);
    free(x);
    free(y);
    return 0;
    }