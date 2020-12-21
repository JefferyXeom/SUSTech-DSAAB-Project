#include <iostream>
#include <string.h>
#include <mkl.h>
#include "randgen.h"

using namespace std;

/*****************************************************************
* 函数描述： 析取矩阵元素
* 函数参数： pM——矩阵指针
			nCol——使用矩阵列大小（指针取值使用）
			i——索引横坐标
			j——索引纵坐标
* 函数返回： 矩阵对应位置的元素值
*****************************************************************/
double &GetArrayVal(double *pM, int nCol, int i, int j) {
	return *(pM + i * nCol + j);
	}


/*****************************************************************
* 函数描述： 创建矩阵
			矩阵元素为 [0,5] 范围内的随机双精度浮点数
* 函数参数： pM——矩阵指针
			nRow——创建矩阵的行规模
			nCol——创建矩阵的列规模
* 函数返回： void
*****************************************************************/
void CreateMatrix(double **pM, int nRow, int nCol) {
	*pM = new double[nRow * nCol];
	memset(*pM, 0, sizeof(int *) * nRow * nCol);

	for (int i = 0; i < nRow; ++i) {
		for (int j = 0; j < nCol; ++j) {
			GetArrayVal(*pM, nCol, i, j) = randgen(-500,500);
			}
		}
	}


/*****************************************************************
* 函数描述： 销毁矩阵(内存管理)
* 函数参数： pM——矩阵指针
* 函数返回：void
*****************************************************************/
void DeleteMatrix(double **pM) {
	if (NULL != *pM) {
		delete *pM;
		*pM = NULL;
		}
	}


/*****************************************************************
* 函数描述： 矩阵加减法（n阶方阵）
* 函数参数： pM1——矩阵1
			nLeftIndex1，nTopIndex1——矩阵1左上角索引点（相对于源矩阵pMl）
			nTotalCol1——矩阵1实际使用的列数
			pM2——矩阵2
			nLeftIndex2, nTopIndex2——矩阵2左上角索引点（相对于源矩阵pM2）
			nTotalCol2——矩阵2实际使用的列数
			nCount——方阵阶数n
			pResult——运算结果矩阵
			bAdd——加减标记
* 函数返回：void
*****************************************************************/
void MatrixAddOrSub(double *pM1, int nLeftIndex1, int nTopIndex1, int nTotalCol1,
	double *pM2, int nLeftIndex2, int nTopIndex2, int nTotalCol2,
	int nCount, double **pResult, bool bAdd) {
	*pResult = new double[nCount * nCount];
	for (int i = 0; i < nCount; ++i) {
		for (int j = 0; j < nCount; ++j) {
			if (bAdd)	// 加法
				{
				GetArrayVal(*pResult, nCount, i, j) = GetArrayVal(pM1, nTotalCol1, nLeftIndex1 + i, nTopIndex1 + j)
					+ GetArrayVal(pM2, nTotalCol2, nLeftIndex2 + i, nTopIndex2 + j);
				} else		// 减法
				{
				GetArrayVal(*pResult, nCount, i, j) = GetArrayVal(pM1, nTotalCol1, nLeftIndex1 + i, nTopIndex1 + j)
					- GetArrayVal(pM2, nTotalCol2, nLeftIndex2 + i, nTopIndex2 + j);
				}

			}
		}
	}


/*****************************************************************
* 函数描述： 矩阵乘法（n阶方阵）
* 函数参数： pM1——矩阵1
			nLeftIndex1，nTopIndex1——矩阵1左上角索引点（相对于源矩阵pMl）
			nTotalCol1——矩阵1实际使用的列数
			pM2——矩阵2
			nLeftIndex2, nTopIndex2——矩阵2左上角索引点（相对于源矩阵pM2）
			nTotalCol2——矩阵2实际使用的列数
			nCount——方阵阶数n
			pResult——运算结果矩阵
* 函数返回：void
*****************************************************************/
void MatrixMulti(double *pM1, int nLeftIndex1, int nTopIndex1, int nTotalCol1,
	double *pM2, int nLeftIndex2, int nTopIndex2, int nTotalCol2,
	int nCount, double **pResult) {

	*pResult = new double[nCount * nCount];
	for (int i = 0; i < nCount; ++i) {
        for (int k = 0; k < nCount; ++k) {
			double s = GetArrayVal(pM1, nTotalCol1, nLeftIndex1 + i, nTopIndex1 + k);
			for (int j = 0; j < nCount; ++j) {
				GetArrayVal(*pResult, nCount, i, j) += s* GetArrayVal(pM2, nTotalCol2, nLeftIndex2 + k, nTopIndex2 + j);
				}
			}
		}
	}
/*
* 函数描述：使用Math Kernel Library加速矩阵乘法，默认矩阵为行主序存储、索引点均在(0,0)处
*
*/


/*****************************************************************
* 函数描述： 递归实现斯特拉森矩阵乘法（n阶方阵）
* 函数参数： pM1——矩阵1
			nLeftIndex1，nTopIndex1——矩阵1左上角索引点（相对于源矩阵pMl）
			nTotalCol1——矩阵1实际使用的列数
			pM2——矩阵2
			nLeftIndex2, nTopIndex2——矩阵2左上角索引点（相对于源矩阵pM2）
			nTotalCol2——矩阵2实际使用的列数
			nCount——方阵阶数n
			pResult——运算结果矩阵
* 函数返回：void
*****************************************************************/
void StrassenMatrix(double *pM1, int nLeftIndex1, int nTopIndex1, int nTotalCol1,
	double *pM2, int nLeftIndex2, int nTopIndex2, int nTotalCol2,
	int nCount, double **pResult) {
	if (nCount <= 64) // 如果当前为2阶，不能继续划分则跳出迭代
		{
		MatrixMulti(pM1, nLeftIndex1, nTopIndex1, nTotalCol1,
			pM2, nLeftIndex2, nTopIndex2, nTotalCol2, nCount, pResult);
		} else	// 如果当前大于2阶，拆分成4个大小相等的子矩阵，分别进行迭代
		{
		double *pResultM1 = NULL;
		double *pResultM2 = NULL;
		double *pResultM3 = NULL;
		double *pResultM4 = NULL;
		double *pResultM5 = NULL;
		double *pResultM6 = NULL;
		double *pResultM7 = NULL;

		// M1 = A11 * (B12 - B22)
		double *pB12_B22 = NULL;
		MatrixAddOrSub(pM2, nLeftIndex2, nTopIndex2 + nCount / 2, nTotalCol2,
			pM2, nLeftIndex2 + nCount / 2, nTopIndex2 + nCount / 2, nTotalCol2, nCount / 2, &pB12_B22, false);
		StrassenMatrix(pM1, nLeftIndex1, nTopIndex1, nTotalCol1,
			pB12_B22, 0, 0, nCount / 2, nCount / 2, &pResultM1);

		// M2 = (A11 + A12) * B22;
		double *pA11_A12 = NULL;
		MatrixAddOrSub(pM1, nLeftIndex1, nTopIndex1, nTotalCol1,
			pM1, nLeftIndex1, nTopIndex1 + nCount / 2, nTotalCol1, nCount / 2, &pA11_A12, true);
		StrassenMatrix(pA11_A12, 0, 0, nCount / 2,
			pM2, nLeftIndex2 + nCount / 2, nTopIndex2 + nCount / 2, nTotalCol2, nCount / 2, &pResultM2);

		// M3 = (A21 + A22) * B11;
		double *pA21_A22 = NULL;
		MatrixAddOrSub(pM1, nLeftIndex1 + nCount / 2, nTopIndex1, nTotalCol1,
			pM1, nLeftIndex1 + nCount / 2, nTopIndex1 + nCount / 2, nTotalCol1, nCount / 2, &pA21_A22, true);
		StrassenMatrix(pA21_A22, 0, 0, nCount / 2,
			pM2, nLeftIndex2, nTopIndex2, nTotalCol2, nCount / 2, &pResultM3);

		// M4 = A22 * (B21 - B11)
		double *pB21_B11 = NULL;
		MatrixAddOrSub(pM2, nLeftIndex2 + nCount / 2, nTopIndex2, nTotalCol2,
			pM2, nLeftIndex2, nTopIndex2, nTotalCol2, nCount / 2, &pB21_B11, false);
		StrassenMatrix(pM1, nLeftIndex1 + nCount / 2, nTopIndex1 + nCount / 2, nTotalCol1,
			pB21_B11, 0, 0, nCount / 2, nCount / 2, &pResultM4);


		// M5 = (A11 + A22) * (B11 + B22)
		double *pA11_A22 = NULL;
		double *pB11_B22 = NULL;
		MatrixAddOrSub(pM1, nLeftIndex1, nTopIndex1, nTotalCol1,
			pM1, nLeftIndex1 + nCount / 2, nTopIndex1 + nCount / 2, nTotalCol1, nCount / 2, &pA11_A22, true);
		MatrixAddOrSub(pM2, nLeftIndex2, nTopIndex2, nTotalCol2,
			pM2, nLeftIndex2 + nCount / 2, nTopIndex2 + nCount / 2, nTotalCol2, nCount / 2, &pB11_B22, true);
		StrassenMatrix(pA11_A22, 0, 0, nCount / 2,
			pB11_B22, 0, 0, nCount / 2, nCount / 2, &pResultM5);

		// M6 = (A12 - A22) * (B21 + B22)
		double *pA12_A22 = NULL;
		double *pB21_B22 = NULL;
		MatrixAddOrSub(pM1, nLeftIndex1, nTopIndex1 + nCount / 2, nTotalCol1,
			pM1, nLeftIndex1 + nCount / 2, nTopIndex1 + nCount / 2, nTotalCol1, nCount / 2, &pA12_A22, false);
		MatrixAddOrSub(pM2, nLeftIndex2 + nCount / 2, nTopIndex2, nTotalCol2,
			pM2, nLeftIndex2 + nCount / 2, nTopIndex2 + nCount / 2, nTotalCol2, nCount / 2, &pB21_B22, true);
		StrassenMatrix(pA12_A22, 0, 0, nCount / 2,
			pB21_B22, 0, 0, nCount / 2, nCount / 2, &pResultM6);

		// M7 = (A11 - A21) * (B11 + B12)
		double *pA11_A21 = NULL;
		double *pB11_B12 = NULL;
		MatrixAddOrSub(pM1, nLeftIndex1, nTopIndex1, nTotalCol1,
			pM1, nLeftIndex1 + nCount / 2, nTopIndex1, nTotalCol1, nCount / 2, &pA11_A21, false);
		MatrixAddOrSub(pM2, nLeftIndex2, nTopIndex2, nTotalCol2,
			pM2, nLeftIndex2, nTopIndex2 + nCount / 2, nTotalCol2, nCount / 2, &pB11_B12, true);
		StrassenMatrix(pA11_A21, 0, 0, nCount / 2,
			pB11_B12, 0, 0, nCount / 2, nCount / 2, &pResultM7);


		double *pResultC11 = NULL;
		double *pResultC12 = NULL;
		double *pResultC21 = NULL;
		double *pResultC22 = NULL;
		double *pResultTemp1 = NULL;
		double *pResultTemp2 = NULL;

		// C11 = M5 + M4 - M2 + M6
		MatrixAddOrSub(pResultM5, 0, 0, nCount / 2,
			pResultM4, 0, 0, nCount / 2, nCount / 2, &pResultTemp1, true);
		MatrixAddOrSub(pResultTemp1, 0, 0, nCount / 2,
			pResultM2, 0, 0, nCount / 2, nCount / 2, &pResultTemp2, false);
		MatrixAddOrSub(pResultTemp2, 0, 0, nCount / 2,
			pResultM6, 0, 0, nCount / 2, nCount / 2, &pResultC11, true);


		// C12 = M1 + M2
		MatrixAddOrSub(pResultM1, 0, 0, nCount / 2,
			pResultM2, 0, 0, nCount / 2, nCount / 2, &pResultC12, true);

		// C21 = M3 + M4 
		MatrixAddOrSub(pResultM3, 0, 0, nCount / 2,
			pResultM4, 0, 0, nCount / 2, nCount / 2, &pResultC21, true);

		// C22 = M5 + M1 - M3 - M7
		MatrixAddOrSub(pResultM5, 0, 0, nCount / 2,
			pResultM1, 0, 0, nCount / 2, nCount / 2, &pResultTemp1, true);
		MatrixAddOrSub(pResultTemp1, 0, 0, nCount / 2,
			pResultM3, 0, 0, nCount / 2, nCount / 2, &pResultTemp2, false);
		MatrixAddOrSub(pResultTemp2, 0, 0, nCount / 2,
			pResultM7, 0, 0, nCount / 2, nCount / 2, &pResultC22, false);


		// 构造结果
		*pResult = new double[nCount * nCount];
		for (int i = 0; i < nCount / 2; ++i) {
			for (int j = 0; j < nCount / 2; ++j) {
				GetArrayVal(*pResult, nCount, i, j) = GetArrayVal(pResultC11, nCount / 2, i, j);
				GetArrayVal(*pResult, nCount, i, j + nCount / 2) = GetArrayVal(pResultC12, nCount / 2, i, j);
				GetArrayVal(*pResult, nCount, i + nCount / 2, j) = GetArrayVal(pResultC21, nCount / 2, i, j);
				GetArrayVal(*pResult, nCount, i + nCount / 2, j + nCount / 2) = GetArrayVal(pResultC22, nCount / 2, i, j);

				}
			}

		//释放内存
		DeleteMatrix(&pResultM1);
		DeleteMatrix(&pResultM2);
		DeleteMatrix(&pResultM3);
		DeleteMatrix(&pResultM4);
		DeleteMatrix(&pResultM5);
		DeleteMatrix(&pResultM6);
		DeleteMatrix(&pResultM7);
		DeleteMatrix(&pA11_A12);
		DeleteMatrix(&pA21_A22);
		DeleteMatrix(&pB12_B22);
		DeleteMatrix(&pB21_B11);
		DeleteMatrix(&pA11_A22);
		DeleteMatrix(&pB11_B22);
		DeleteMatrix(&pA12_A22);
		DeleteMatrix(&pB21_B22);
		DeleteMatrix(&pA11_A21);
		DeleteMatrix(&pB11_B12);
		DeleteMatrix(&pResultTemp1);
		DeleteMatrix(&pResultTemp2);
		DeleteMatrix(&pResultC11);
		DeleteMatrix(&pResultC12);
		DeleteMatrix(&pResultC21);
		DeleteMatrix(&pResultC22);

		}
	}

/*****************************************************************
* 函数描述： 打印矩阵
* 函数参数： pM——矩阵指针
			nRow——矩阵行规模
			nCol——矩阵列规模
* 函数返回：void
*****************************************************************/
void PrintMatrix(double *pM, int nRow, int nCol) {
	for (int i = 0; i < nRow; ++i) {
		for (int j = 0; j < nCol; ++j) {
			cout << GetArrayVal(pM, nCol, i, j) << " ";
			}
		cout << endl;
		}
	}

int TestStrassen(int nRow1,int nCol2) {
	srand(0);	// 随机数种子

	double *pM1 = NULL;
	double *pM2 = NULL;
	double *pMResult1 = NULL;

	// 测试4阶方阵乘法
	int nCol1 = nRow1;
	int nRow2 = nCol2;

	// 测试8阶方阵乘法
	//nRow1 = 8;
	//nCol1 = 8;
	//nRow2 = 8;
	//nCol2 = 8;

	// 随机构建两个矩阵，并打印
	CreateMatrix(&pM1, nRow1, nCol1);
	CreateMatrix(&pM2, nRow2, nCol2);
	
	//MatrixMulti(pM1, 0, 0, nRow1, pM2, 0, 0, nRow1, nRow1, &pMResult1);
	//PrintMatrix(pMResult1, nRow1, nCol2);

	StrassenMatrix(pM1, 0, 0, nRow1, pM2, 0, 0, nRow1, nRow1, &pMResult1);
	//PrintMatrix(pMResult1, nRow1, nCol2);

	//内存释放
	DeleteMatrix(&pM1);
	pM1 = nullptr;
	DeleteMatrix(&pM2);
	pM2 = nullptr;
	DeleteMatrix(&pMResult1);
	pMResult1 = nullptr;

	return 0;
	}
int TestNormal(int nRow1, int nCol2) {
    srand(0);	// 随机数种子

    double *pM1 = NULL;
    double *pM2 = NULL;
    double *pMResult1 = NULL;

    // 测试4阶方阵乘法
    int nCol1 = nRow1;
    int nRow2 = nCol2;

    // 测试8阶方阵乘法
    //nRow1 = 8;
    //nCol1 = 8;
    //nRow2 = 8;
    //nCol2 = 8;

    // 随机构建两个矩阵，并打印
    CreateMatrix(&pM1, nRow1, nCol1);
    CreateMatrix(&pM2, nRow2, nCol2);

    MatrixMulti(pM1, 0, 0, nRow1, pM2, 0, 0, nRow1, nRow1, &pMResult1);
    //PrintMatrix(pMResult1, nRow1, nCol2);

    //StrassenMatrix(pM1, 0, 0, nRow1, pM2, 0, 0, nRow1, nRow1, &pMResult1);
    //PrintMatrix(pMResult1, nRow1, nCol2);

    //内存释放
    DeleteMatrix(&pM1);
    pM1 = nullptr;
    DeleteMatrix(&pM2);
    pM2 = nullptr;
    DeleteMatrix(&pMResult1);
    pMResult1 = nullptr;

    return 0;
    }