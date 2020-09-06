package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.SmartDashboardNames;

public class PunchSubsystem extends SubsystemBase {
    private static final boolean EXTENDED_STATE = true;

    private final Solenoid m_punchSolenoid;

    public PunchSubsystem() {
        m_punchSolenoid = new Solenoid(Constants.SOLENOID_PUNCH);
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean(SmartDashboardNames.PUNCH_IS_RETRACTED, isExtended());
    }

    public boolean isExtended() {
        return m_punchSolenoid.get() == EXTENDED_STATE;
    }

    public void extend() {
        m_punchSolenoid.set(EXTENDED_STATE);
    }

    public void retract() {
        m_punchSolenoid.set(!EXTENDED_STATE);
    }
}
