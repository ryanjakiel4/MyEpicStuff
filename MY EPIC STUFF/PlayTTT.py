#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      wicke_000
#
# Created:     19/06/2014
# Copyright:   (c) wicke_000 2014
# Licence:     <your licence>
#-------------------------------------------------------------------------------

def main():
    file = open('TTT.txt', 'r')
    boardCollection = dict()
    for n in range(4520):
        newList = list()
        board = file.readline()
        board = board[:len(board)-1]
        for i in range(9):
            newElement = file.readline()
            newElement = newElement[:len(newElement)-1]
            newList.append(int(newElement))
        boardCollection[board] = newList

    mainBoard = '---------'
    while not isFinished(mainBoard):
        printTTTBoard(mainBoard)
        humanMove = input("Human, it's your move. Choose the position to put your 'X'.")
        humansTurn = True
        while humansTurn:
            if mainBoard[int(humanMove)] == '-':
                mainBoard = insertX(mainBoard, int(humanMove))
                humansTurn = False
            else:
                humanMove = input("That spot is already taken, enter a different position to put your 'X'")
        if not isFinished(mainBoard):
            cpuMove = getBestPosition(mainBoard, boardCollection)
            mainBoard = insertO(mainBoard, cpuMove)
    printTTTBoard(mainBoard)
    if isFinished(mainBoard) == 1:
        print('You Win!')
    if isFinished(mainBoard) == 2:
        print('You Lost :(')
    if isFinished(mainBoard) == 3:
        print('You tied.')

def printTTTBoard(board):
    print('Game Board:    Position Board:')
    print(' ' + board[0],'|', board[1], '|', board[2], '     ', ' ' + '0','|', '1', '|', '2')
    print('---+---+---','    ', '---+---+---')
    print(' ' + board[3],'|', board[4], '|', board[5], '     ', ' ' + '3','|', '4', '|', '5')
    print('---+---+---','    ', '---+---+---')
    print(' ' + board[6],'|', board[7], '|', board[8], '     ', ' ' + '6','|', '7', '|', '8')

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

def getIndexesWithoutZeros(lst):
	indexList = []
	for n in range(len(lst)):
		if lst[n] != 0:
			indexList.append(n)
	return indexList

if __name__ == '__main__':
    main()
