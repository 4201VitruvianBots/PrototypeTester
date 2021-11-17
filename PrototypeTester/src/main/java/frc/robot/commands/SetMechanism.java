// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Mechanism;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class SetMechanism extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Mechanism m_mechanism;
  private final DoubleSupplier m_inputSupplier;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
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
    if (Math.abs(input) > 0.15) {
      m_mechanism.setMotorPercentOutput(input);
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
