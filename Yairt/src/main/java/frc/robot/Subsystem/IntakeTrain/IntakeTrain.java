package frc.robot.Subsystem.IntakeTrain;

import static edu.wpi.first.units.Units.Newton;

import com.MAutils.DashBoard.DashBoardTab;
import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeTrain extends SubsystemBase {
    private final TalonFX motor1;
    private final TalonFX motor2;

    private TalonFXConfiguration motor1Configuration;
    private TalonFXConfiguration motor2Configuration;

    private final StatusSignal<AngularVelocity> velocitySignalMotor1;
    private final StatusSignal<Voltage> voltageSignalMotor1;
    private final StatusSignal<Current> currentSignalMotor1;

    private final StatusSignal<AngularVelocity> velocitySignalMotor2;
    private final StatusSignal<Voltage> voltageSignalMotor2;
    private final StatusSignal<Current> currentSignalMotor2;

    private DashBoardTab intakeTab = new DashBoardTab("intake");


    public IntakeTrain () {
        motor1 = new TalonFX(16);
        motor2 = new TalonFX(18);

        motor1Configuration = new TalonFXConfiguration();
        motor2Configuration = new TalonFXConfiguration();

        velocitySignalMotor1 = motor1.getVelocity();
        voltageSignalMotor1 = motor1.getMotorVoltage();
        currentSignalMotor1 = motor1.getStatorCurrent();

        velocitySignalMotor2 = motor2.getVelocity();
        voltageSignalMotor2 = motor2.getMotorVoltage();
        currentSignalMotor2 = motor2.getStatorCurrent();


        motor1Configuration.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        motor1Configuration.Feedback.SensorToMechanismRatio = IntakeTrainConstants.gear;
        motor1Configuration.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        motor1.getConfigurator().apply(motor1Configuration);

        motor2Configuration.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        motor2Configuration.Feedback.SensorToMechanismRatio = IntakeTrainConstants.gear;
        motor2Configuration.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        motor2.getConfigurator().apply(motor2Configuration);
    }
    public void setMotor1Voltage(double voltage) {
        motor1.setVoltage(voltage);
    }
    public void setMotor2Voltage(double voltage) {
        motor1.setVoltage(voltage);
    }
    public void stopMotors() {
        motor1.setVoltage(0);
        motor2.setVoltage(0);
    }
    public double getVelocityMotor1() {
        return velocitySignalMotor1.getValueAsDouble() * 60;
    }
    public double getVelocityMotor2() {
        return velocitySignalMotor2.getValueAsDouble() * 60;
    }
    public double getVoltageMotor1() {
        return voltageSignalMotor1.getValueAsDouble();
    }
    public double getVoltageMotor2() {
        return voltageSignalMotor2.getValueAsDouble();
    }
    public double getCurrentMotor1() {
        return currentSignalMotor1.getValueAsDouble();
    }
    public double getCurrentMotor2() {
        return currentSignalMotor2.getValueAsDouble();
    }

    @Override
    public void periodic() {
        BaseStatusSignal.refreshAll(velocitySignalMotor1, velocitySignalMotor2, voltageSignalMotor1, voltageSignalMotor2, currentSignalMotor1, currentSignalMotor2);        
    
    MALog.log("/Subsystems/IntakeTrain/VoltageMotor1", getVoltageMotor1());
    MALog.log("/Subsystems/IntakeTrain/CurrentMotor1", getCurrentMotor1());
    MALog.log("/Subsystems/IntakeTrain/VelocityMotor1", getVelocityMotor1());
    MALog.log("/Subsystems/IntakeTrain/VoltageMotor1", getVoltageMotor2());
    MALog.log("/Subsystems/IntakeTrain/CurrentMotor2", getCurrentMotor2());
    MALog.log("/Subsystems/IntakeTrain/VoltageMotor1", getVelocityMotor2());

    intakeTab.addNum("test1", 2);
    }
    

}