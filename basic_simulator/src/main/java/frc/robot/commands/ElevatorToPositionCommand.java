package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorToPositionCommand extends CommandBase {

    private final ElevatorSubsystem m_lift;
    private final double m_position;
    private final boolean m_holdAtPosition;
    private boolean m_finished;

    public ElevatorToPositionCommand(ElevatorSubsystem lift, double position) {
        this(lift, position, true);
    }

    public ElevatorToPositionCommand(ElevatorSubsystem lift, double position, boolean holdAtPosition) {
        m_lift = lift;
        m_position = position;
        m_holdAtPosition = holdAtPosition;

        addRequirements(lift);
    }

    @Override
    public void execute() {
        // TODO implement
    }

    @Override
    public boolean isFinished() {
        // TODO implement
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        // TODO implement
    }
}
