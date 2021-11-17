// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;

public class Mechanism extends SubsystemBase {
  private final TalonFX talonFX = new TalonFX(Constants.motorPort);
  private final TalonSRX talonSRX = new TalonSRX(Constants.motorPort);
  private final CANSparkMax;

  /** Creates a new ExampleSubsystem. */
  public Mechanism() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMotorPercentOutput(double input) {
    talonFX.set(ControlMode.PercentOutput, input);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}