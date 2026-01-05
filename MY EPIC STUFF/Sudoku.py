class Cell(object):
    matrix = None
    def __init__(self, val, r, c, matrix):
        if val != 0:
            self.value = {val,}
        else:
            self.value = set(range(1, MAX +1))
        self.row = r
        self.col = c
        self.block = self.blockNumber(r, c)
        Cell.matrix = matrix
    def __repr__(self):
        if len(self.value) == 1:
            return (list(self.value)[0])
        return ' '
    def print(matrix, details = False):
        print('+---' * MAX + '+')
        for r in range(MAX):
            for c in range(MAX):
                if len(Cell.matrix[r][c].value) == 1:
                    elt = list(Cell.matrix[r][c].value)[0]
                    print('| ', elt, ' ', end = '', sep = '')
                else: print('| ', end ='', sep ='')
            print('|')
            print('+---' * MAX + '+')
        print()
        if details == True:
            for r in range(MAX):
                for c in range(MAX):
                    print('matrix[', r, '][', c, '].value =', matrix[r][c].value, sep = '')
                print()
    def blockNumber(self, row, col):
        if (self.row < 3) and (self.col  < 3): return 0
        if (self.row < 3) and (2 < self.col  < 6): return 1
        if (self.row < 3) and (5 < self.col): return 2
        if (2 < self.row < 6) and (self.col  < 3): return 3
        if (2 < self.row < 6) and (2 < self.col  < 6): return 4
        if (2 < self.row < 6) and (5 < self.col): return 5
        if (5 < self.row) and (self.col  < 3): return 6
        if (5 < self.row) and (2 < self.col  < 6): return 7
        if (5 < self.row) and (5 < self.col): return 8

    def reduceCellCandidatesByRow(self):
        for r in range(MAX):
            if r != self.row and len(Cell.matrix[r][self.col].value)==1:
                self.value -= Cell.matrix[r][self.col].value
    def reduceCellCandidatesByCol(self):
        for c in range(MAX):
            if c != self.col and len(Cell.matrix[self.row][c].value)==1:
                self.value -= Cell.matrix[self.row][c].value
    def reduceCellCandidatesByBlock(self):
        for r in range(MAX):
            for c in range(MAX):
                if (Cell.matrix[r][c].value == self.block) \
                    and (r != self.row or c != self.col) and len(Cell.matrix[r][c].value) == 1:
                        self.value -= Cell.matrix[r][c].value
    def updateCellBasedOnCandidateValuesInItsRowColAndBlock(self):
        rowCandidates = []
        colCandidates = []
        blkCandidates = []
        for c in range(MAX):
            if c != self.col:
                rowCandidates += Cell.matrix[self.row][c].value
        for r in range(MAX):
            if r != self.col:
                colCandidates += Cell.matrix[r][self.col].value
        for r in range(MAX):
            for c in range(MAX):
                if r != self.row or c!= self.col:
                    if Cell.matrix[r][c].block == self.block:
                        blkCandidates += Cell.matrix[r][c].value
        for num in self.value:
            if (num not in rowCandidates) or(num not in colCandidates) or (num not in blkCandidates):
                self.value = {num,}
    def TryToReduceCandidateValuesInCell(self):
        oldValue = deepcopy(self.value)
        if len(self.value) == 1:
            return false
        self.reduceCellCandidatesByRow()
        self.reduceCellCandidatesByCol()
        self.reduceCellCandidatesByBlock()
        self.updateCellBasedOnCandidateValuesInItsRowColAndBlock()
        return self.value != oldValue

def setUpCanvas(root):
    root.title("SUDOKU: A Tk/Python Graphics Program by Ryan Jakiel")
    canvas = Canvas(root, width = 1920, height = 1080, bg = 'black')
    canvas.pack(expand = YES, fill = BOTH)
    return canvas
def displayTheSudokuBoard(matrix):
    canvas.delete('all')
    canvas.create_rectangle(0,0,1920,1080, fill = 'BLACK')
    canvas.create_rectangle(375, 170, 735, 560, width = 4, outline = 'RED', fill = 'BLUE')
    kolor = 'RED'
    for l in range(10):
        canvas.create_rectangle(375+l*10, 170, 735+l*10, 560, width = 2, fill = kolor)
    for l in range(10):
        canvas.create_rectangle(375, 170+l*10, 735, 560+l*10,fill = kolor)
    for r in range(MAX):
        for c in range(MAX):
            if len(matrix[r][c].value) != 1:
                ch = ' '
            else:
                ch = list(matrix[r][c].value)[0]
            canvas.create_text(c*40 + 395, r*42+200, text = ch, fill= 'YELLOW', font = ('Helvetica', 20, 'bold'))
def createTheSudokuBoard():
    V = [[0,0,1,0,0,9,0,0,0,],
        [0,0,7,8,0,6,0,4,0,],
        [0,0,0,0,4,3,0,0,0,],
        [0,4,0,1,0,0,0,6,0,],
        [0,7,6,0,0,0,2,5,0,],
        [0,1,0,0,0,2,0,3,0,],
        [0,0,0,2,7,0,0,0,0,],
        [0,5,0,3,0,1,9,0,0,],
        [0,0,0,6,0,0,3,0,0,],]

    matrix = []
    for r in range(MAX):
        row = []
        for c in range(MAX):
            row.append(Cell(V[r][c], r, c, matrix))
        matrix.append(row)
    return matrix
def solutionIsCorrect(matrix):
    return None
def printVerification(matrix):
    if solutionIsCorrect(matrix):
        canvas.create_text(565, 600, text = "This Sudoku is correct.", fill = 'WHITE', font = ('Helvetica', 20, 'bold'))
    else:
        canvas.create_text(565, 620, text = "WRONG!", fill = 'RED', font = ('Helvetica', 70, 'bold'))
def makeAllPossibleSimpleChangesToMatrix(matrix):
    return matrix
def restoreValues(matrix, oldMatrix):
    for r in range(MAX):
        for c in range(MAX):
            matrix[r][c].value = deepcopy(oldMatrix[r][c].value)
    return matrix
def solveTheSudoku(matrix):
    #..
    oldMatrix = deepcopy(matrix)
    for r in range(MAX):
        for c in range(MAX):
            #..
            for number in candidates:
                #..
                matrix = solveTheSoduku(matrix)
                if solutionIsCorrect(matrix):
                    return matrix
                matrix = restoreValues(matrix, oldMatrix)
            return matrix

from tkinter import Tk, Canvas, YES, BOTH
from time import clock, sleep
from copy import deepcopy
root = Tk()
canvas = setUpCanvas(root)
MAX = 9

def main():
    matrix = createTheSudokuBoard()
    matrix = solveTheSoduku(matrix)
    displayTheSudokuBoard(matrix)
    printVerification(matrix)
    printElaspedTime()
    root.mainloop()
def printElaspedTime():
    print('\n---Total run time =', round(clock() - startTime, 2), 'seconds.')

if __name__ == '__main__': startTime = clock(); main()
