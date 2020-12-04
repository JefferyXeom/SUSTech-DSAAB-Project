#pragma once
#ifndef __ZGEMV_H
#define __ZGEMV_H
#include <iostream>
#include <complex>
#include <mkl_cblas.h>
#include "randgen.h"
int zgemv(int rows, int cols);
#endif
