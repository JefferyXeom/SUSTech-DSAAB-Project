#pragma once
#ifndef __MKLDGEMM_H
#define __MKLDGEMM_H
#include <mkl.h>
#include <complex>
#include "randgen.h"
int mkldgemm(int rowa, int cola, int colb);
#endif