// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Mechanism;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Sets the mechanism's motor based on joystick input
 */
public class SetMechanism extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Mechanism m_mechanism;
  private final DoubleSupplier m_inputSupplier;

  /**
   * Sets the mechanism's motor based on joystick input
   *
   * @param mechanism the mechanism to set
   * @param inputSupplier the joystick supplying motor percent inputs
   */
  public SetMechanism(Mechanism mechanism, DoubleSupplier inputSupplier) {
    m_mechanism = mechanism;
    m_inputSupplier = inputSupplier;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mechanism);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double input = m_inputSupplier.getAsDouble();
    if (Math.abs(input) > 0.05) {
      m_mechanism.setMotorPercentOutput(input);
    } else {
      m_mechanism.setMotorPercentOutput(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_mechanism.setMotorPercentOutput(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
