import numpy as np
import matplotlib.pyplot as plt
import math


def lagrange(xy: np.ndarray, x: float) -> float:
    return sum([xy[1][i] * np.prod([(x - xy[0][j]) / (xy[0][i] - xy[0][j]) if i != j else 1 for j in range(0, xy.shape[1])]) for i in range(0, xy.shape[1])])


def dy(xy: np.ndarray) -> np.ndarray:
    y = np.zeros((xy.shape[1], xy.shape[1]))
    y[:, 0] = xy[1]
    for i in range(1, xy.shape[1]):
        for j in range(0, xy.shape[1] - i):
            y[j][i] = y[j + 1][i - 1] - y[j][i - 1]
    return y


def newton(xy: np.ndarray, x: float) -> (float, bool):
    y = dy(xy)
    start = 0
    while start + 2 != xy.shape[1] and x > xy[0][start + 1]:
        start += 1
    is_left = (xy[0][0] + xy[0][-1]) / 2 >= x
    h = xy[0][1] - xy[0][0]
    t = np.zeros(xy.shape[1])
    t[0] = 1
    t[1] = (x - xy[0][start]) / h if is_left else (x - xy[0][xy.shape[1]-1]) / h
    for i in range(2, xy.shape[1]):
        t[i] = t[i - 1] * (t[1] - i + 1) / i if is_left else t[i - 1] * (t[1] + i - 1) / i
    if is_left:
        print("Использованные конечные разности:")
        for k in range(0, xy.shape[1] - start):
            print(y[start][k], end=" ")
        print()
        return sum([y[start][k] * t[k] for k in range(0, xy.shape[1] - start)]), is_left
    else:
        print("Использованные конечные разности:")
        for k in range(0, xy.shape[1]):
            print(y[xy.shape[1] - 1 - k][k], end=" ")
        print()
        return sum([y[xy.shape[1] - 1 - k][k] * t[k] for k in range(0, xy.shape[1])]), is_left
