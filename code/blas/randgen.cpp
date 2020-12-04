#include "randgen.h"

double randgen(double init,double fin)
{
    return (((double) rand() / RAND_MAX) * (fin - init) + init);
}
void randinit()
{
    srand((unsigned) time(NULL));
}