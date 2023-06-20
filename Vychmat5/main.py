import array

import interpolation
import numpy as np
import matplotlib.pyplot as plt
import math


def f1(x):
    return math.sin(x)

def f2(x):
    return x**3 - 2 * x**2 + 4 * x - 3

def f3(x):
    return math.log(x) + 1

print("Доступные функции:")
print("1: sin(x)")
print("2: x^3 - 2x^2 + 4x - 3")
print("3: ln(x) + 1")

n = ""
while n != "1" and n != "2" and n != "3" and n != "0":
    print("Введите номер функции или 0, если хотите читать данные из файла")
    n = input()

if n == "0":
    data_reader = open('input1')
    xy = np.array([list(map(float, data_reader.readline().split())), list(map(float, data_reader.readline().split()))])
    xy.sort()

else:
    if n == "1":
        f = f1
    elif n == "2":
        f = f2
    else:
        f = f3

    print("Введите левую границу интервала:")
    l = float(input())
    print("Введите правую границу интервала:")
    r = float(input())
    print("Введите количество точек на интервале:")
    k = int(input())

    i = l
    arr_x = []
    arr_y = []
    while i <= r:
        i += (r - l) / k
        arr_x.append(i)
        arr_y.append(f(i))
    xy = np.array([arr_x, arr_y])

print("Таблица конечных разностей:")
y = interpolation.dy(xy)
for yi in y:
    y_s = ""
    for yj in yi:
        y_s = f"{y_s} {yj:.3f}"
    print(y_s)
print()

print("Введите точку интерполяции")
x_i = float(input())
newton, is_left = interpolation.newton(xy, x_i)
lagrange = interpolation.lagrange(xy, x_i)
if is_left:
    print("Применена формула Ньютона для интерполирования вперед")
else:
    print("Применена формула Ньютона для интерполирования назад")
print(f"""Значение по Лагранжу: {lagrange:.3f}
Значение по Ньютону: {newton:.3f}
Разница между ними: {math.fabs(lagrange - newton)}
""")

x = np.linspace(xy[0][0] - 1, xy[0][-1] + 1)
y_L = [interpolation.lagrange(xy, x_i) for x_i in x]
y_N = [interpolation.newton(xy, x_i)[0] for x_i in x]
plt.plot(x, y_L, 'r')
plt.plot(x, y_N, 'b')
plt.scatter(xy[0], xy[1])
plt.show()
