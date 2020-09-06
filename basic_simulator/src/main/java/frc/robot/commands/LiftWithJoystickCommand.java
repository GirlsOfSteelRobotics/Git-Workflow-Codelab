package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.LiftingSubsystem;

public class LiftWithJoystickCommand extends CommandBase {
    private final OI m_oi;
    private final LiftingSubsystem m_lift;

    public LiftWithJoystickCommand(LiftingSubsystem lift, OI oi) {
        m_lift = lift;
        m_oi = oi;

        addRequirements(lift);
    }

    @Override
    public void execute() {
        m_lift.setSpeed(m_oi.getElevatorJoystick());
    }
}
