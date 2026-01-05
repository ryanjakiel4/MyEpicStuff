#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      wicke_000
#
# Created:     11/06/2014
# Copyright:   (c) wicke_000 2014
# Licence:     <your licence>
#-------------------------------------------------------------------------------

def main():
    boardCollection = {'---------': [9,9,9,9,9,9,9,9,9]}
    sequence = [0,1,2,3,4,5,6,7,8]
    from itertools import permutations
    filledBoards = list(permutations(sequence,9))
    for p in filledBoards:
        for n in range(8):
            tempBoard = stringBoard(p, n)
            if isFinished(tempBoard):
                break
            else:
                boardCollection[tempBoard] = newBoardList(tempBoard)
    for n in range(5000000):
        if n % 100000 == 0:
            print('Played', str(n), 'times')
        playGame(boardCollection)
    for b in boardCollection:
        print(boardCollection[b])
    file = open('TTT.txt', 'w')
    for b in boardCollection:
        file.write(b)
        file.write('\n')
        for n in range(9):
            file.write(str(boardCollection[b][n]))
            file.write('\n')
    file.close()

def stringBoard(perm, limit):
    newBoard = '---------'
    for n in range(limit + 1):
        if n % 2 == 0:
            newBoard = insertX(newBoard, perm.index(n))
        else:
            newBoard = insertO(newBoard, perm.index(n))
    return newBoard

def insertX(newBoard, pos):
    return newBoard[:pos] + 'X' + newBoard[pos+1:]

def insertO(newBoard, pos):
    return newBoard[:pos] + 'O' + newBoard[pos+1:]

def isFinished(board):
    if '-' not in board:
        return 3
    if board[0] == 'X' and board[1] == 'X' and board[2] == 'X':
        return 1
    if board[3] == 'X' and board[4] == 'X' and board[5] == 'X':
        return 1
    if board[6] == 'X' and board[7] == 'X' and board[8] == 'X':
        return 1
    if board[0] == 'X' and board[3] == 'X' and board[6] == 'X':
        return 1
    if board[1] == 'X' and board[4] == 'X' and board[7] == 'X':
        return 1
    if board[2] == 'X' and board[5] == 'X' and board[8] == 'X':
        return 1
    if board[0] == 'X' and board[4] == 'X' and board[8] == 'X':
        return 1
    if board[2] == 'X' and board[4] == 'X' and board[6] == 'X':
        return 1
    if board[0] == 'O' and board[1] == 'O' and board[2] == 'O':
        return 2
    if board[3] == 'O' and board[4] == 'O' and board[5] == 'O':
        return 2
    if board[6] == 'O' and board[7] == 'O' and board[8] == 'O':
        return 2
    if board[0] == 'O' and board[3] == 'O' and board[6] == 'O':
        return 2
    if board[1] == 'O' and board[4] == 'O' and board[7] == 'O':
        return 2
    if board[2] == 'O' and board[5] == 'O' and board[8] == 'O':
        return 2
    if board[0] == 'O' and board[4] == 'O' and board[8] == 'O':
        return 2
    if board[2] == 'O' and board[4] == 'O' and board[6] == 'O':
        return 2
    return 0

def newBoardList(board):
    newList = []
    for b in board:
        if b == '-':
            newList.append(9)
        else:
            newList.append(0)
    return newList

def playGame(allBoards):
    from random import random
    moves = 0
    gameBoardsO = dict()
    gameBoardsX = dict()
    currentBoard = '---------'
    while not isFinished(currentBoard):
        pos = getBestPosition(currentBoard, allBoards)

        #print("Position:", str(pos), "Move:", str(moves))
        if moves % 2 == 0:
            #gameBoardsX[currentBoard] = pos
            currentBoard = insertX(currentBoard, pos)
        else:
            gameBoardsO[currentBoard] = pos
            currentBoard = insertO(currentBoard, pos)
        moves += 1
    if isFinished(currentBoard) == 1:
        for b in gameBoardsO:
            if allBoards[b][gameBoardsO[b]] > 1:
                allBoards[b][gameBoardsO[b]] -= 1
        for b in gameBoardsX:
            if allBoards[b][gameBoardsX[b]] > 0:
                allBoards[b][gameBoardsX[b]] += 3
    if isFinished(currentBoard) == 2:
        for b in gameBoardsO:
            if allBoards[b][gameBoardsO[b]] > 0:
                allBoards[b][gameBoardsO[b]] += 3
        for b in gameBoardsX:
            if allBoards[b][gameBoardsX[b]] > 1:
                allBoards[b][gameBoardsX[b]] -= 1
    if isFinished(currentBoard) == 3:
        for b in gameBoardsO:
            if allBoards[b][gameBoardsO[b]] > 0:
                allBoards[b][gameBoardsO[b]] += 1
        for b in gameBoardsX:
            if allBoards[b][gameBoardsX[b]] > 0:
                allBoards[b][gameBoardsX[b]] += 1

def getBestPosition(board, allBoards):
    probabilityList = allBoards[board]
    total = sum(probabilityList)
    indexesWithoutZeros = getIndexesWithoutZeros(probabilityList)
    #print(board, probabilityList, indexesWithoutZeros)
    from random import random
    r = int(random()*total)
    sumSoFar = 0
    for index in indexesWithoutZeros:
        if r < (probabilityList[index] + sumSoFar):
            return index
        else:
            sumSoFar += probabilityList[index]
    print("Something's wrong")

def getIndexesWithoutZeros(lst):
	indexList = []
	for n in range(len(lst)):
		if lst[n] != 0:
			indexList.append(n)
	return indexList

if __name__ == '__main__':
    from time import clock
    START_TIME = clock()
    main()
    print('Run Time -->', round(clock() - START_TIME,2))
