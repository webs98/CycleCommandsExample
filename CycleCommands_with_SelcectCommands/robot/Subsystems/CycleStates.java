package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CycleStates extends SubsystemBase{
    
    private int stateInt = 3;

    public CycleStates(){
    }

    public enum State{
        Floor,
        L2,
        L3
    }

    State state = State.L3;

    public State getState(){
        return state;
    }

    /**
     * Method to cycle through 3 premade states.  Any number of states can be used but they need to be created
     */
    public void incrementState(){
        stateInt++;
        switch(stateInt){
            case 1: state = State.Floor; break;
            case 2: state = State.L2; break;
            case 3: state = State.L3; break;

            default: state = State.Floor; stateInt = 1; break;
        }
    }
    
    /**
     * Method to reset the state to a known starting point
     */
    public void resetState(){
        state = State.L3;
        stateInt = 3;
    }
}
