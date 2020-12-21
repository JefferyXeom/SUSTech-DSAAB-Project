import matplotlib.pyplot as plt
import numpy as np
from scipy.optimize import curve_fit

# 用指数形式来拟合

x = np.array([512, 562, 612, 662, 712, 762, 812, 862, 912, 962, 1012])

ynor = np.array([
    0.255, 0.332, 0.369, 0.467, 0.589, 0.714, 0.862, 1.111, 1.317, 1.483, 1.969
])

ystr = np.array([
    0.1, 0.111, 0.132, 0.161, 0.213, 0.244, 0.291, 0.368, 0.446, 0.522, 0.623
])


def func(x, a, b, c):

    return a * x**b + c


popt1, pcov1 = curve_fit(func, x, ynor, maxfev=5000)
popt2, pcov2 = curve_fit(func, x, ystr, maxfev=5000)
print(popt1)
print(popt2)
xvals = np.linspace(512, 1012, 128)
yvals1 = func(xvals, popt1[0], popt1[1], popt1[2])
yvals2 = func(xvals, popt2[0], popt2[1], popt2[2])

plot1 = plt.plot(x, ynor, "*", label="Normal MM")
plot2 = plt.plot(xvals, yvals1, "r", label="Fit result of Normal MM")
plot3 = plt.plot(x, ystr, "+", label="Strassen")
plot4 = plt.plot(xvals, yvals2, "green", label="Fit result of Sectioned MM")

plt.xlabel("Matrix Size")
plt.ylabel("Time/s")
plt.legend(loc=4)  # 指定legend的位置,读者可以自己help它的用法
plt.title("Strassen with threshould=2")
plt.savefig("D:\\CS203B\\Project\\ppt\\slide-UIBK\\sub_naive.png")
plt.show()
