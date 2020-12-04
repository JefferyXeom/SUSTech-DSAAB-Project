#include "eigensgemm.h"

using namespace std;
using namespace Eigen;

// 使用Eigen+Intel MKL
void eigensgemm(int rowa,int colb,int cola){
    int rowb = cola;
    MatrixXd a = MatrixXd::Random(rowa, cola);  // 随机初始化矩阵
    MatrixXd b = MatrixXd::Random(rowb, colb);
    MatrixXd c = a * b;    // 乘法好简洁
    a.resize(0, 0);
    b.resize(0, 0);
    c.resize(0, 0);
    }