def ANN_Training(x, w, alpha = .00001):
    error = 999
    count = 0
    while count < 3000:
        for n in range(4):
            yy = fff(x[n][0] * w[0] + x[n][1] * w[1] + x[n][2] * w[2])
            t = x[n][3]
            error = t - yy
            w[0] = w[0] + error * alpha * x[n][0]
            w[1] = w[1] + error * alpha * x[n][1]
            w[2] = w[2] + error * alpha * x[n][2]
            count += 1
            print(count, 'iterations and error = ', round(error, 6))
    return w

def ANN(x,w):
    z = f(x,w)
    print(z)
    return z < w[0]

from math import exp
def f(x,w):
    dotProd = w[1]*x[1] + w[2]*x[2]
    print()
    return round(fff(dotProd))

def fff(x):
    return(1/(1+exp(-x)))
def main():
    x = [[-1,1,1,1],[-1,1,0,0],[-1,0,1,0],[-1,0,0,0]]
    w = [.7,-.1,-.8]
    w = ANN_Training(x, w)
    print(w)
    print(ANN(x[0],w))
    print(ANN(x[1],w))
    print(ANN(x[2],w))
    print(ANN(x[3],w))


if __name__ == '__main__':
    from time import clock; START_TIME = clock(); main();
    print('--->Run time =', round(clock() - START_TIME, 2),'seconds<---');


