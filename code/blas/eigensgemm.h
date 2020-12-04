#pragma once
#ifndef __EIGENSGEMM_H
#define EIGEN_USE_MKL_ALL
//#define EIGEN_VECTORIZE_AVX_2

#include <iostream>
#include <Eigen/Core>
#include <Eigen/Dense>
#include <time.h>
void eigensgemm(int rowa, int colb, int cola);
#endif