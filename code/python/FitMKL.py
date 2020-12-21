import matplotlib.pyplot as plt
import numpy as np
from scipy.optimize import curve_fit

# 用指数形式来拟合

x = np.array([
    2, 53, 104, 155, 206, 257, 308, 359, 410, 461, 512, 562, 612, 662, 712,
    762, 812, 862, 912, 962, 1012, 1024, 1124, 1224, 1324, 1424, 1524, 1624,
    1724, 1824, 1924, 2024, 2048, 2248, 2448, 2648, 2848, 3048, 3248, 3448,
    3648, 3848, 4048, 4096, 5096, 6096
])

ynor = np.array([
    0, 0.002, 0.002, 0.009, 0.024, 0.04, 0.081, 0.096, 0.158, 0.154, 0.303,
    0.251, 0.3, 0.401, 0.49, 0.691, 0.769, 1.037, 1.184, 1.648, 1.96, 4.783,
    3.029, 4.356, 6.196, 6.53, 10.692, 13.511, 18.631, 23.97, 30.597, 35.472,
    68.084, 48.719, 64.008, 84.814, 113.954, 134.673, 181.081, 227.03, 250.604,
    309.702, 373.716, 681.522, 743.003, 1424.562
])

ystr = np.array([
    0.017, 0.001, 0, 0.002, 0.001, 0.002, 0.003, 0.004, 0.004, 0.004, 0.006,
    0.008, 0.011, 0.01, 0.013, 0.015, 0.017, 0.022, 0.024, 0.025, 0.03, 0.029,
    0.041, 0.056, 0.062, 0.069, 0.079, 0.098, 0.105, 0.123, 0.136, 0.168,
    0.177, 0.212, 0.259, 0.428, 0.376, 0.455, 0.588, 0.687, 0.778, 0.875,
    1.139, 1.035, 1.884, 3.606
])


def func(x, a, b, c):

    return a * x**b + c


popt1, pcov1 = curve_fit(func, x, ynor)
popt2, pcov2 = curve_fit(func, x, ystr)
print(popt1)
print(popt2)
xvals = np.linspace(2, 6100, 1024)
yvals1 = func(xvals, popt1[0], popt1[1], popt1[2])
yvals2 = func(xvals, popt2[0], popt2[1], popt2[2])

plot1 = plt.plot(x, ynor, "*", label="Normal MM")
plot2 = plt.plot(xvals, yvals1, "r", label="Fit result of Normal MM")
plot3 = plt.plot(x, ystr, "+", label="Strassen")
plot4 = plt.plot(xvals, yvals2, "green", label="Fit result of MKL")

plt.xlabel("Matrix Size")
plt.ylabel("Time/s")
plt.legend(loc=4)  # 指定legend的位置,读者可以自己help它的用法
plt.title("Strassen with threshould=2")
plt.savefig("D:\\CS203B\\Project\\ppt\\slide-UIBK\\mkl.png")
plt.show()
