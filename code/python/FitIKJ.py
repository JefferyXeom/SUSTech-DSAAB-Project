import matplotlib.pyplot as plt
import numpy as np
import math
from scipy.optimize import curve_fit

# 用指数形式来拟合

x = np.array([
    2, 53, 104, 155, 206, 257, 308, 359, 410, 461, 512, 562, 612, 662, 712,
    762, 812, 862, 912, 962, 1012, 1024, 1124, 1224, 1324, 1424, 1524, 1624,
    1724, 1824, 1924, 2024, 2048, 2248, 2448, 2648, 2848, 3048, 3248, 3448,
    3648
])

ynor = np.array([
    0, 0.002, 0.003, 0.007, 0.019, 0.032, 0.047, 0.078, 0.118, 0.182, 0.239,
    0.3, 0.389, 0.489, 0.619, 0.782, 0.933, 1.13, 1.369, 1.705, 2.146, 2.421,
    3.105, 3.979, 5.715, 6.462, 9.362, 13.075, 18.372, 23.712, 30.431, 34.335,
    61.804, 49.062, 66.495, 85.081, 106.63, 131.37, 169.45, 210.52, 239.68
])

yikj = np.array([
    0, 0.002, 0.002, 0.003, 0.007, 0.011, 0.017, 0.028, 0.04, 0.064, 0.079,
    0.114, 0.134, 0.165, 0.208, 0.259, 0.311, 0.39, 0.46, 0.557, 0.688, 0.723,
    0.961, 1.277, 1.663, 2.121, 2.716, 3.096, 3.845, 4.536, 5.397, 6.078,
    6.634, 8.476, 10.727, 13.866, 17.352, 21.279, 25.808, 31.35, 37.521
])
ystr = np.array([
    0, 0.013, 0.005, 0.014, 0.067, 0.846, 0.055, 1.837, 0.106, 4.06, 0.181,
    1.136, 0.307, 1.979, 0.599, 0.814, 0.659, 5.459, 0.97, 1.139, 1.389, 1.322,
    4.275, 2.292, 7.53, 4.206, 5.365, 5.326, 17.08, 7.461, 8.622, 10.134,
    10.896, 20.644, 19.204, 34.519, 28.802, 37.193, 39.458, 76.369, 57.278
])


def expdeg(x, a, b, c):
    return a * x**b + c


def polydeg(x, a, b, c):
    return a * (x**(2 + b)) + c


def polysindeg(x, a, b, c, d):
    return a * (x**(2 + b)) + c * x * np.sin(x) + d


directory = "D:\\CS203B\\Project\\ppt\\slide-UIBK\\ikj_naive_strassen_comp.png"


def fit3(x, arr1, arr2, arr3, directory):
    popt1, pcov1 = curve_fit(expdeg, x, arr1, maxfev=5000)
    popt2, pcov2 = curve_fit(polydeg, x, arr2, maxfev=5000)
    popt3, pcov3 = curve_fit(polydeg, x, arr3, maxfev=5000)
    print(popt1)
    print(popt2)
    print(popt3)
    xvals = np.linspace(2, 3650, 256)
    yvals1 = expdeg(xvals, popt1[0], popt1[1], popt1[2])
    yvals2 = polydeg(xvals, popt2[0], popt2[1], popt2[2])
    yvals3 = polydeg(xvals, popt3[0], popt3[1], popt3[2])  #, popt3[3])
    plot1 = plt.plot(x, arr1, "*", label="Normal MM")
    plot2 = plt.plot(xvals, yvals1, "r", label="Fit result of Normal MM")
    plot3 = plt.plot(x, arr2, "+", label="IKJ")
    plot4 = plt.plot(xvals,
                     yvals2,
                     "green",
                     label="Fit result of Inverted-Order MM")
    plot3 = plt.plot(x, arr3, "v", label="Strassen")
    plot4 = plt.plot(xvals,
                     yvals3,
                     "magenta",
                     label="Fit result of Inverted-Order MM")

    plt.xlabel("Matrix Size")
    plt.ylabel("Time/s")
    plt.legend(loc=2)  # 指定legend的位置,读者可以自己help它的用法
    plt.title("IKJ,SubNaive and Optimized Strassen")
    plt.savefig(directory)
    plt.show()
    return 0


def fit2(x, arr1, arr2, directory):
    popt1, pcov1 = curve_fit(expdeg, x, arr1, maxfev=5000)
    popt2, pcov2 = curve_fit(polydeg, x, arr2, maxfev=5000)
    print(popt1)
    print(popt2)
    xvals = np.linspace(2, 3650, 256)
    yvals1 = expdeg(xvals, popt1[0], popt1[1], popt1[2])
    yvals2 = polydeg(xvals, popt2[0], popt2[1], popt2[2])
    plot1 = plt.plot(x, arr1, "*", label="Inverted IKJ MM")
    plot2 = plt.plot(xvals, yvals1, "r", label="Fit result of Inverted IKJ MM")
    plot3 = plt.plot(x, arr2, "+", label="IKJ")
    plot4 = plt.plot(xvals,
                     yvals2,
                     "green",
                     label="Fit result of Inverted-Order MM")
    plt.xlabel("Matrix Size")
    plt.ylabel("Time/s")
    plt.legend(loc=2)  # 指定legend的位置,读者可以自己help它的用法
    plt.title("IKJ,SubNaive and Optimized Strassen")
    plt.savefig(directory)
    plt.show()
    return 0


# fit3(x,ynor,yikj,ystr,directory)
fit2(x, ynor, ystr,
     "D:\\CS203B\\Project\\ppt\\slide-UIBK\\naive_strassen_comp.png")
