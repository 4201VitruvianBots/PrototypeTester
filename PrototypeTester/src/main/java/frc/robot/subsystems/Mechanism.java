// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Mechanism extends SubsystemBase {
  // private final TalonFX talonFX = new TalonFX(Constants.talonFXPort);
  // private final TalonSRX talonSRX = new TalonSRX(Constants.talonSRXPort);
  private final CANSparkMax sparkMax = new CANSparkMax(Constants.sparkMaxPort, MotorType.kBrushless);

  SendableChooser<Integer> motorTypeChooser = new SendableChooser();
  private int motorType = 0; // 0  = TalonFX, 1 = TalonSRX, 2 = Spark

  private final DoubleSolenoid piston = new DoubleSolenoid(Constants.pcmOne, Constants.pistonForward, Constants.pistonReverse);

  /** Creates a new ExampleSubsystem. */
  public Mechanism() {
    motorTypeChooser.addDefault("TalonFX", 0);
    motorTypeChooser.addOption("TalonSRX", 1);
    motorTypeChooser.addOption("Spark", 2);
    SmartDashboard.putData(motorTypeChooser);

    motorType = motorTypeChooser.getSelected();

    // if (motorType == 0) {
    //   talonFX.configFactoryDefault();
    //   talonFX.configOpenloopRamp(0.1);
    //   talonFX.configClosedloopRamp(0.1);
    //   talonFX.setNeutralMode(NeutralMode.Coast);
    //   talonFX.configForwardSoftLimitEnable(false);
    //   talonFX.configReverseSoftLimitEnable(false);
    //   talonFX.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 30, 0, 0));
    // } else if (motorType == 1) {
    //   talonSRX.configFactoryDefault();
    //   talonSRX.configOpenloopRamp(0.1);
    //   talonSRX.configClosedloopRamp(0.1);
    //   talonSRX.setNeutralMode(NeutralMode.Coast);
    //   talonSRX.configForwardSoftLimitEnable(false);
    //   talonSRX.configReverseSoftLimitEnable(false);
    //   talonSRX.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 30, 0, 0));
    // } else {
      sparkMax.restoreFactoryDefaults();
      sparkMax.setIdleMode(CANSparkMax.IdleMode.kCoast);
      sparkMax.setInverted(false);
    // }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMotorPercentOutput(double input) {
    // if (motorType == 0) {
    //   talonFX.set(ControlMode.PercentOutput, input);
    // } else if (motorType == 1) {
    //   talonSRX.set(ControlMode.PercentOutput, input);
    // } else {
      sparkMax.set(input);
    // }
  }

  public void setPiston(boolean extend) {
    piston.set(extend ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
  }

  public boolean getPistonExtendStatus() {
    return piston.get() == DoubleSolenoid.Value.kForward;
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
