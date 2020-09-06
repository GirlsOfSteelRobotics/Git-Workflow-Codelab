package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ChassisSubsystem;

public class AutoDriveStraightDistanceCommand extends CommandBase {

    public static final double ALLOWABLE_ERROR = .5;
    private static final double KP = .2;

    private final ChassisSubsystem m_chassis;
    private final double m_goalDistance;

    private double m_error;

    public AutoDriveStraightDistanceCommand(ChassisSubsystem chassis, double goalDistance) {
        m_chassis = chassis;
        m_goalDistance = goalDistance;

        addRequirements(chassis);
    }

    @Override
    public void execute() {

        m_error = m_goalDistance - m_chassis.getAverageDistance();
        double speed = KP * m_error;

        m_chassis.setSpeedAndSteer(speed, 0);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(m_error) < ALLOWABLE_ERROR;
    }

    @Override
    public void end(boolean interrupted) {
        m_chassis.setSpeedAndSteer(0, 0);
    }
}
