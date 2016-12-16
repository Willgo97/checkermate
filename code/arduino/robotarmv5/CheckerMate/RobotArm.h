/*
    RobotArm.h -library for checking the positions of the stones
    Created by William Goudswaard
*/
#ifndef RobotArm_h
#define RobotArm_h

#include "Arduino.h"

class RobotArm{
    public:
        RobotArm(int init);
        void moveRobot(int x, int y);
        void resetRobot();       
        
    private:
        int init;
        int x;
        int y;
};

#endif
