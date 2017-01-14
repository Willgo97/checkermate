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
        void goUpReset(int hitStonesCount);
        
        void enMagnet();
        void disMagnet();        
        
    private:
        int init;
        int hitStonesCount;
};

#endif
