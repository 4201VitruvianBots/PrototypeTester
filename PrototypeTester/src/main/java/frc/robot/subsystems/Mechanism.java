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

/**
 * Mechanism for testing prototypes, with one motor and one piston
 */
public class Mechanism extends SubsystemBase {
  private TalonFX talonFX;
  private TalonSRX talonSRX;
  private CANSparkMax sparkMax;

  SendableChooser<Integer> motorTypeChooser = new SendableChooser();
  private boolean usingTalonFX = true;
  private boolean usingTalonSRX = true;
  private boolean usingSparkMax = true;

  private int motorType = 0; // 0  = TalonFX, 1 = TalonSRX, 2 = Spark

  private final DoubleSolenoid piston = new DoubleSolenoid(Constants.pcmOne, Constants.pistonForward, Constants.pistonReverse);

  /** Creates a mechanism with one motor and one piston */
  public Mechanism() {
  }

  public void initializeMotors() {
    usingTalonFX = true;
    usingTalonSRX = true;
    usingSparkMax = true;

    try {
      talonFX = new TalonFX(Constants.talonFXPort);
      talonFX.configFactoryDefault();
      talonFX.configOpenloopRamp(0.1);
      talonFX.configClosedloopRamp(0.1);
      talonFX.setNeutralMode(NeutralMode.Coast);
      talonFX.configForwardSoftLimitEnable(false);
      talonFX.configReverseSoftLimitEnable(false);
      talonFX.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 30, 0, 0));
    } catch (Exception e) {
      usingTalonFX = false;
    }

    try {
      talonSRX = new TalonSRX(Constants.talonSRXPort);
      talonSRX.configFactoryDefault();
      talonSRX.configOpenloopRamp(0.1);
      talonSRX.configClosedloopRamp(0.1);
      talonSRX.setNeutralMode(NeutralMode.Coast);
      talonSRX.configForwardSoftLimitEnable(false);
      talonSRX.configReverseSoftLimitEnable(false);
      talonSRX.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 30, 0, 0));
    } catch (Exception e) {
      usingTalonSRX = false;
    }

    try {
      sparkMax = new CANSparkMax(Constants.sparkMaxPort, MotorType.kBrushless);
      sparkMax.restoreFactoryDefaults();
      sparkMax.setIdleMode(CANSparkMax.IdleMode.kCoast);
      sparkMax.setInverted(false);
    } catch (Exception e) {
      usingSparkMax = false;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    SmartDashboard.putBoolean("TalonFX Found", usingTalonFX);
    SmartDashboard.putBoolean("TalonSRX Found", usingTalonSRX);
    SmartDashboard.putBoolean("SparkMAX Found", usingSparkMax);
  }

  public void setMotorPercentOutput(double input) {
    if (usingTalonFX) {
      talonFX.set(ControlMode.PercentOutput, input);
    }
    if (usingTalonSRX) {
      talonSRX.set(ControlMode.PercentOutput, input);
    } 
    if (usingSparkMax) {
      sparkMax.set(input);
    }
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
