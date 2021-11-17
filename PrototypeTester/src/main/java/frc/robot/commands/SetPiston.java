/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Mechanism;;

/**
 * Sets the piston that raises/lowers the intake.
 */
public class SetPiston extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Mechanism m_mechanism;
    private final boolean m_extend;

    public SetPiston(Mechanism mechansim, boolean extend) {
        m_mechanism = mechansim;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(mechansim);
        m_extend = extend;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        if(m_mechanism.getPistonExtendStatus() != m_extend)
            m_mechanism.setPiston(m_extend);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
