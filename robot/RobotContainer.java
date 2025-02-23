// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Commands.ToggleStateCommand;
import frc.robot.Subsystems.CycleStates;

public class RobotContainer {

  public static final XboxController DRIVE_JOYSTICK = new XboxController(0);

  public static final JoystickButton A = new JoystickButton(DRIVE_JOYSTICK, XboxController.Button.kA.value);
  public static final JoystickButton B = new JoystickButton(DRIVE_JOYSTICK, XboxController.Button.kB.value);
  public static final JoystickButton X = new JoystickButton(DRIVE_JOYSTICK, XboxController.Button.kX.value);
  public static final JoystickButton Y = new JoystickButton(DRIVE_JOYSTICK, XboxController.Button.kY.value);

  private final CycleStates cycleStates = new CycleStates();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {

    /**
     * Increments infeed state then runs a command with corresponding select command.  
     * The incrementState method must be run before the select command to properly increment on start up.
     */
    A.onTrue(new InstantCommand(() -> cycleStates.incrementState()));
    A.onTrue(new ToggleStateCommand(cycleStates, X));

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
