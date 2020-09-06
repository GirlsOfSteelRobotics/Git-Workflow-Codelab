package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ChassisSubsystem;

public class AutoDriveStraightTimedCommand extends CommandBase {

    private final ChassisSubsystem m_chassis;
    private final Timer m_timer;
    private final double m_speed;
    private final double m_time;

    public AutoDriveStraightTimedCommand(ChassisSubsystem chassis, double speed, double time) {
        m_chassis = chassis;
        m_speed = speed;
        m_time = time;
        m_timer = new Timer();

        addRequirements(chassis);
    }

    @Override
    public void initialize() {
        m_timer.start();
    }

    @Override
    public void execute() {
        m_chassis.setSpeedAndSteer(m_speed, 0);
    }

    @Override
    public boolean isFinished() {
        return m_timer.get() > m_time;
    }
}
