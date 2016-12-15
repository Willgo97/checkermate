/*
    CheckBoard.h -library for checking the positions of the stones
    Created by William Goudswaard
*/
#ifndef CheckBoard_h
#define CheckBoard_h

#include "Arduino.h"

class CheckBoard{
    public:
        CheckBoard(int rows[5], int columns[10]);
        void checkB();
        void printB();
    private:
        int _rows[5];
        int _columns[10];
        int array[10][10];
};

#endif
