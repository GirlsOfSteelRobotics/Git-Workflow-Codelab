package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.SmartDashboardNames;

public class PunchSubsystem extends SubsystemBase {
    private final Solenoid m_punchSolenoid;

    public PunchSubsystem() {
        m_punchSolenoid = new Solenoid(Constants.SOLENOID_PUNCH);
    }

    @Override
    public void periodic() {
        // TODO fill out
        SmartDashboard.putNumber(SmartDashboardNames.PUNCH_RETRACTED, 0);
    }
}
