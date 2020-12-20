#include <cstdlib>
#include <cstdio>
#include <mkl.h>
#include <windows.h>


#include "eigensgemm.h"
#include "mkldgemm.h"
#include "randgen.h"
#include "vmv.h"
#include "zgemv.h"
void printgemm(int r, int i, int c) {
    printf_s("Rows of A:%d, Cols of A:%d\nRows of B:%d, Cols of B:%d ", r, i, i, c);
    }
void printmv(int r, int c) {
    printf_s("Rows of A:%d, Cols of A:%d\n", r, c);
    }
void printvv(int len) {
    printf_s("Length of a and b:%d\n",len);
    }
int main(int argc, char *argv[]) {
    int turns = 10, rows = 0, cols = 0, interm = 0;
    randinit();
    for (int i = 0; i <= turns; i++) {
        LARGE_INTEGER t1, t2, tc;
        QueryPerformanceFrequency(&tc);
        QueryPerformanceCounter(&t1);

        rows = 0 + 2 * i;
        cols = rows;
        interm = (int) randgen(2000, 3000);
        mkldgemm(rows, cols, interm);  //需计时的函数
        printgemm(rows, interm, cols);

        QueryPerformanceCounter(&t2);
        double time = (double) (t2.QuadPart - t1.QuadPart) / (double) tc.QuadPart;
        printf_s("time=%f ms\n", time * (double) 1000);
        }
    getchar();
    return 0;
    }
