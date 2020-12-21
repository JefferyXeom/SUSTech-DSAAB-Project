#pragma once
#ifndef __STRASSEN_H
#define __STRASSEN_H
#include <iostream>
#include <string.h>
#include <mkl.h>

double &GetArrayVal(double *pM, int nCol, int i, int j);
void CreateMatrix(double **pM, int nRow, int nCol);
void DeleteMatrix(double **pM);
void MatrixAddOrSub(double *pM1, int nLeftIndex1, int nTopIndex1, int nTotalCol1,
    double *pM2, int nLeftIndex2, int nTopIndex2, int nTotalCol2,
    int nCount, double **pResult, bool bAdd);
void MatrixMulti(double *pM1, int nLeftIndex1, int nTopIndex1, int nTotalCol1,
    double *pM2, int nLeftIndex2, int nTopIndex2, int nTotalCol2,
    int nCount, double **pResult);
void StrassenMatrix(double *pM1, int nLeftIndex1, int nTopIndex1, int nTotalCol1,
    double *pM2, int nLeftIndex2, int nTopIndex2, int nTotalCol2,
    int nCount, double **pResult);
void PrintMatrix(double *pM, int nRow, int nCol);
int TestStrassen(int nRow1, int nCol2);
int TestNormal(int nRow1, int nCol2);
#endif
