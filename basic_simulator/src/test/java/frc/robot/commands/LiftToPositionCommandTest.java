package frc.robot.commands;

import frc.robot.BaseTestFixture;
import frc.robot.subsystems.LiftingSubsystem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LiftToPositionCommandTest extends BaseTestFixture {

    @Test
    public void testExecution() {
        LiftingSubsystem lift = new LiftingSubsystem();
        LiftToPositionCommand command = new LiftToPositionCommand(lift, 20);
        command.schedule();

        runCycles(100);

        assertFalse(command.isScheduled());
        assertEquals(20, lift.getHeight(), LiftingSubsystem.ALLOWABLE_POSITION_ERROR);
    }
}
