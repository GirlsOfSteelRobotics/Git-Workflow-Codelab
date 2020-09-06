package frc.robot.subsystems;

import frc.robot.BaseTestFixture;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LiftingSubsystemTest extends BaseTestFixture {

    @Test
    public void testManuallyMoveUp() {

        LiftingSubsystem lift = new LiftingSubsystem();

        runCycles(50, () -> lift.setSpeed(1));

        assertTrue(lift.getHeight() > 0);
    }

    @Test
    public void testManuallyMoveDown() {

        LiftingSubsystem lift = new LiftingSubsystem();

        lift.setSpeed(-1);
        runCycles(50); // Run for one second

        assertTrue(lift.getHeight() < 0);
    }

    @Test
    public void testGoToPosition() {
        // TODO implement

        LiftingSubsystem lift = new LiftingSubsystem();

        runCycles(100, () -> lift.goToPosition(20));
        assertEquals(20, lift.getHeight(), LiftingSubsystem.ALLOWABLE_POSITION_ERROR);
    }
}
