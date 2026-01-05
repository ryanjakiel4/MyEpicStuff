#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      wicke_000
#
# Created:     22/10/2013
# Copyright:   (c) wicke_000 2013
# Licence:     <your licence>
#-------------------------------------------------------------------------------
from time import clock
graphX = {'A':[366, 3, ('Z', 75), ('T', 118), ('S', 140)],
	 'Z':[374, 2, ('A', 75), ('O', 71)],
	 'T':[329, 2, ('A', 118), ('L', 111)],
	 'L':[244, 2, ('T', 111), ('M', 70)],
	 'M':[241, 2, ('L', 70), ('D', 75)],
	 'D':[242, 2, ('M', 75), ('C', 120)],
	 'C':[160, 3, ('D', 120), ('R', 146), ('P', 138)],
	 'R':[193, 3, ('C', 146), ('P', 97), ('S', 80)],
	 'S':[253, 4, ('R', 80), ('F', 99), ('O', 151), ('A', 140)],
	 'O':[380, 2, ('S', 151), ('Z', 71)],
	 'P':[100, 3, ('C', 138), ('R', 97), ('B', 101)],
	 'F':[176, 2, ('S', 99), ('B', 211)],
	 'B':[0, 4, ('P', 101), ('F', 211), ('G', 90), ('U', 85)],
	 'G':[77, 1, ('B', 90)],
	 'U':[80, 3, ('B', 85), ('H', 98), ('V', 142)],
	 'H':[151, 2, ('U', 98), ('E', 86)],
	 'E':[161, 1, ('H', 86)],
	 'V':[199, 2, ('U', 142), ('I', 92)],
	 'I':[226, 2, ('V', 92), ('N', 87)],
	 'N':[234, 1, ('I', 87)],}
def main():
    print(AStar('A','B'))
    print('Search Time:', round(clock() - startTime,2), 'seconds')
def AStar(rootNode, goalNode):
    Q = [(0+366, rootNode, ['A'], 0)]
    CLOSED = {}
    while Q:
        #print('Q List:')
        #for x in Q:
        #    print('---', x)
        #print ('CLOSED =', CLOSED)
        (fValue, node, path, gValue)= Q.pop(0)
        print (node, goalNode)
        if node == goalNode:
            return path
        CLOSED[node] = gValue
        for (child, localDist) in graphX[node][2:]:
            newChild = (fValue, child, path + [child], gValue + localDist)
            if child in CLOSED:
                if gValue + localDist > CLOSED[child]:
                    continue
                if gValue + localDist < CLOSED[child]:
                    del CLOSED[node]
                    Q.append(newChild)
                    continue
            for miniQ in Q:
                (fV, n, p, gV) = miniQ
                if child == n:
                    if gValue + localDist < fV:
                        Q.remove(miniQ)
                        Q.append(newChild)
                    break
            else:
                Q.append(newChild)
        Q.sort()
if __name__ == '__main__': startTime = clock(); main()
