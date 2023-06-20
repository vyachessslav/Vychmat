def Runge(y1, y2, p, e):
    return abs(y1 - y2) / (2 ** p - 1) <= e


def Euler(y0, x0, xn, h0, f, e):
    y = [y0]
    x = [x0]
    while x[-1] < xn:
        h = h0
        while h0 < h * 256:
            y_next = y[-1] + h * f(x[-1], y[-1])
            y_half_next = y[-1] + h/2 * f(x[-1], y[-1])
            if Runge(y_next, y_half_next, 1, e):
                break
            h = h/2
        y.append(y_next)
        x.append(x[-1] + h)
    return x, y

def RK(y0, x0, xn, h0, f, e):
    y = [y0]
    x = [x0]
    while x[-1] < xn:
        h = h0
        while h0 < h * 256:
            k1 = h * f(x[-1], y[-1])
            k2 = h * f(x[-1] + h / 2, y[-1] + k1 / 2)
            k3 = h * f(x[-1] + h / 2, y[-1] + k2 / 2)
            k4 = h * f(x[-1] + h, y[-1] + k3)
            y_next = y[-1] + (k1 + 2 * (k2 + k3) + k4) / 6
            k1 = h/2 * f(x[-1], y[-1])
            k2 = h/2 * f(x[-1] + h/4, y[-1] + k1 / 2)
            k3 = h/2 * f(x[-1] + h/4, y[-1] + k2 / 2)
            k4 = h/2 * f(x[-1] + h/2, y[-1] + k3)
            y_half_next = y[-1] + (k1 + 2 * (k2 + k3) + k4) / 6
            if Runge(y_next, y_half_next, 4, e):
                break
            h = h/2
        y.append(y_next)
        x.append(x[-1] + h)
    return x, y

def Adams(y0, x_old, h , f, e) -> list:
    x, y = RK(y0, x_old[0], x_old[3], h, f, e)
    for i in range(4, len(x_old)):
        fi = f(x[-1], y[-1])
        fim1 = f(x[-2], y[-2])
        fim2 = f(x[-3], y[-3])
        fim3 = f(x[-4], y[-4])
        df = fi - fim1
        d2f = fi - 2 * fim1 + fim2
        d3f = fi + 3 * (-fim1 + fim2) - fim3
        y.append(y[-1] + h * fi + h**2 / 2 * df + 5 * h**3 / 12 * d2f + 3 * h**4 / 8 * d3f)
        x.append(x[-1] + h)
    return x, y
