package frc.robot.Commands;

import java.util.Map;
import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Subsystems.CycleStates;

public class ToggleStateCommand extends SequentialCommandGroup{

    private boolean endCommand;

    /**
     * Command to cycle through infeed commands with one button and reset to a known starting state/infeed command. 
     * Each infeed can have a unique compliance routine.
     * This can be tiled for any number of unique commands/states.
     * @param cycleStates Subsystem with cycleable enum state chooser. See CycleStates.java for subsystem requirements.
     * @param sensor Sensor input, should be from a sensor subsystem not a boolean supplier.
     */
    public ToggleStateCommand(CycleStates cycleStates, BooleanSupplier sensor){

        addCommands(
            new RepeatCommand(
                new ConditionalCommand(
                    //Compliance selectable commands.
                    new ParallelCommandGroup(
                        new InstantCommand(() -> endCommand = true),
                        new SelectCommand<>(
                            Map.ofEntries(
                                Map.entry(CycleStates.State.Floor, new PrintCommand("Floor Comp")),
                                Map.entry(CycleStates.State.L2, new PrintCommand("L2 Comp")),
                                Map.entry(CycleStates.State.L3, new PrintCommand("L3 Comp"))
                            ),
                            cycleStates::getState)
                    ),

                    //Infeed selectable commands.
                    new ParallelCommandGroup(
                        new InstantCommand(() -> endCommand = false),
                        new SelectCommand<>(
                            Map.ofEntries(
                                Map.entry(CycleStates.State.Floor, new PrintCommand("Floor Infeed")),
                                Map.entry(CycleStates.State.L2, new PrintCommand("L2 Infeed")),
                                Map.entry(CycleStates.State.L3, new PrintCommand("L3 Infeed"))
                            ),
                            cycleStates::getState)
                    ),

                    //Sensor input.
                    sensor
                )
            ).until(() -> endCommand).finallyDo(cycleStates::resetState)
        );
    } 
}
