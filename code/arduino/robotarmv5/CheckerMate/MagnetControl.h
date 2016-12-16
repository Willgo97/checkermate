/*
    MagnetControl.h -library for checking the positions of the stones
    Created by William Goudswaard
*/
#ifndef MagnetControl_h
#define MagnetControl_h

#include "Arduino.h"

class MagnetControl{
    public:
        MagnetControl(int init);
        void goUp();
        void goDown();
        
        void enMagnet();
        void disMagnet();        
        
    private:
        int _init;
};

#endif
