package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LiftingSubsystem;

public class LiftToPositionCommand extends CommandBase {

    private final LiftingSubsystem m_lift;
    private final double m_position;
    private boolean m_finished;

    public LiftToPositionCommand(LiftingSubsystem lift, double position) {
        m_lift = lift;
        m_position = position;

        addRequirements(lift);
    }

    @Override
    public void execute() {
        m_finished = m_lift.goToPosition(m_position);
    }

    @Override
    public boolean isFinished() {
        return m_finished;
    }

    @Override
    public void end(boolean interrupted) {
        m_lift.setSpeed(0);
    }
}
