// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Commands.IntakeUntilSensor;

public class RobotContainer {
  public static final PS5Controller controller = new PS5Controller(0);


  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    new Trigger(() -> controller.getTriangleButton())
    .onTrue(new IntakeUntilSensor());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
