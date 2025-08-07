// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Intake.Intake;
import frc.robot.Subsystem.IntakeTrain.IntakeTrain;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class TestCommand3 extends Command {
  /** Creates a new IntakeUntilSensor. */
  public TestCommand3() {
    addRequirements(IntakeTrain.getInstanceIntakeTrain());
}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    IntakeTrain.getInstanceIntakeTrain().setMotor1Voltage(12);
    IntakeTrain.getInstanceIntakeTrain().setMotor2Voltage(12);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (IntakeTrain.getInstanceIntakeTrain().getVelocityMotor1() == 1200) {
        System.out.println("1200Right");
    }
    else if (IntakeTrain.getInstanceIntakeTrain().getVelocityMotor2() == 1200) {
        System.out.println("1200Left");
    }
    
}


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    wait(4000);

    IntakeTrain.getInstanceIntakeTrain().stopMotors();
    wait(500);

    while (IntakeTrain.getInstanceIntakeTrain().sensor1.get() == true) {
        IntakeTrain.getInstanceIntakeTrain().setMotor1Voltage(4);
        IntakeTrain.getInstanceIntakeTrain().setMotor2Voltage(4);
    }
    IntakeTrain.getInstanceIntakeTrain().stopMotors();


    

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return IntakeTrain.getInstanceIntakeTrain().getVelocityMotor1() == 1200 && IntakeTrain.getInstanceIntakeTrain().getVelocityMotor2() == 1200;
  }

  public static void wait(int ms) {
    try {
        Thread.sleep(ms);
    }
    catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
    }
}
}
