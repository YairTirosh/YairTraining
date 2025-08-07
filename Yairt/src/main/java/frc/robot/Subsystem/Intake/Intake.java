
package frc.robot.Subsystem.Intake;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private static Intake intake;

  public Intake() {}

  public void setPower(double power) {

  }

  public boolean getSensor() {
    return false;
  }

  public static Intake getInstance() {
    if (intake == null) {
      intake = new Intake();
    }
    return intake;
  }

  @Override
  public void periodic() {
    
  }
}
