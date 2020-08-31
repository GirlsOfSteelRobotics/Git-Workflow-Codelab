/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SpinnyWheelSubsystem extends SubsystemBase {

    private final SpeedController m_wheelMotor;

    public SpinnyWheelSubsystem() {
        m_wheelMotor = new Talon(Constants.SC_SPINNY_MOTOR);

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}