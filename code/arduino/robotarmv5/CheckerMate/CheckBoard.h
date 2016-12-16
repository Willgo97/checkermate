/*
    CheckBoard.h -library for checking the positions of the stones
    Created by William Goudswaard
*/
#ifndef CheckBoard_h
#define CheckBoard_h

#include "Arduino.h"

class CheckBoard{
    public:
        CheckBoard(int init);
        void checkB();
        void printB();
    private:
        int init;
};

#endif
