import differential
import math
import numpy as np
import matplotlib.pyplot as plt
from typing import Callable


def f1(y0, x0) -> (Callable[[float, float], float], Callable[[float], float]):
    f = lambda x, y: math.sin(x) * y
    F = lambda x: y0 * math.exp(math.cos(x0)) * math.exp(-math.cos(x))
    return f, F


def f2(y0, x0) -> (Callable[[float, float], float], Callable[[float], float]):
    f = lambda x, y: y * math.log(x)
    F = lambda x: y0 * math.exp(x0) / math.pow(x0, x0) * math.exp(-x) * math.pow(x, x)
    return f, F


def f3(y0, x0) -> (Callable[[float, float], float], Callable[[float], float]):
    f = lambda x, y: y - x**2
    F = lambda x: (y0 - x0**2 - 2 * x0 - 2) * math.exp(-x0) * math.exp(x) + x**2 + 2 * x + 2
    return f, F


def incorrect():
    print("Некорректный ввод")
    exit(0)


print("Введите y0, x0, xn, h, e через пробел")
y0, x0, xn, h, e = map(float, input().split())
print("""Выберите функцию
1. y' = sin(x) * y
2. y' = y * ln(x)
3. y' = y - x^2""")

choice = input()
if choice == "1":
    f, F = f1(y0, x0)
elif choice == "2":
    f, F = f2(y0, x0)
elif choice == "3":
    f, F = f3(y0, x0)
else:
    incorrect()

x = np.linspace(x0, xn, int((xn - x0) / h + 1))
Y = np.vectorize(F)(x) / F(x0) * y0

Euler_x, Euler_y = differential.Euler(y0, x0, xn, h, f, e)
RK_x, RK_y = differential.RK(y0, x0, xn, h, f, e)
Adams_x, Adams_y = differential.Adams(y0, x, h, f, e)

adams_max = 0
for i in range(len(Adams_x)):
    print('X =', Adams_x[i])
    print('Точное значение:', F(Adams_x[i]))
    print('Значение методом Адамса:', Adams_y[i])
    adams_max = max(adams_max, abs(Adams_y[i] - F(Adams_x[i])))
print('Погрешность метода Адамса:', adams_max)

print('X =', Euler_x[-1])
print('Точное значение:', F(Euler_x[-1]))
print('Значение методом Эйлера:', Euler_y[-1])

print('X =', RK_x[-1])
print('Точное значение:', F(RK_x[-1]))
print('Значение методом Рунге-Кутта:', RK_y[-1])

fig, ax = plt.subplots(3)

ax[0].plot(Euler_x, Euler_y, 'b')
ax[0].plot(x, Y, 'r')
ax[0].legend(['Приближенное решение', 'Точное решение'])
ax[0].title.set_text('Метод Эйлера')

ax[1].plot(RK_x, RK_y, 'b')
ax[1].plot(x, Y, 'r')
ax[1].legend(['Приближенное решение', 'Точное решение'])
ax[1].title.set_text('Метод Рунге-Кутта')

ax[2].plot(Adams_x, Adams_y, 'b')
ax[2].plot(x, Y, 'r')
ax[2].legend(['Приближенное решение', 'Точное решение'])
ax[2].title.set_text('Метод Адамса')

plt.show()
