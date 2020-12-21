#include <cstdlib>
#include <cstdio>
#include <mkl.h>
#include <windows.h>


#include "eigensgemm.h"
#include "mkldgemm.h"
#include "randgen.h"
#include "Strassen.h"
#include "vmv.h"
#include "zgemv.h"
void printgemm(int r, int i, int c) {
    printf_s("Rows of A:%d, Cols of A:%d\nRows of B:%d, Cols of B:%d ", r, i, i, c);
    }
void printmv(int r, int c) {
    printf_s("Rows of A:%d, Cols of A:%d\n", r, c);
    }
void printvv(int len) {
    printf_s("Length of a and b:%d ",len);
    }
int main(int argc, char *argv[]) {
    int turns = 10, rows = 2, cols = 2, interm = 0;
    randinit();
    for (int i = 0; i <= turns; i++) {
        LARGE_INTEGER t1, t2, tc;
        QueryPerformanceFrequency(&tc);
        QueryPerformanceCounter(&t1);

        rows = rows*2;
        cols = rows;
        printf_s("%3d   ", rows);
        interm = (int) randgen(2000, 3000);
        //mkldgemm(rows, cols, interm);  //需计时的函数
        TestNormal(rows, cols);
        //printgemm(rows, cols, cols);

        QueryPerformanceCounter(&t2);
        double time = (double) (t2.QuadPart - t1.QuadPart) / (double) tc.QuadPart;
        printf_s("%.3f   ", time);
        QueryPerformanceFrequency(&tc);
        QueryPerformanceCounter(&t1);

        interm = (int) randgen(2000, 3000);
        //mkldgemm(rows, cols, interm);  //需计时的函数
        TestStrassen(rows, cols);

        QueryPerformanceCounter(&t2);
        time = (double) (t2.QuadPart - t1.QuadPart) / (double) tc.QuadPart;
        printf_s("%.3f\n", time);
        }
    getchar();
    return 0;
    }
