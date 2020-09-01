package com.gos.shuffleboard_codelab.sd_widgets.ss.data;

import com.gos.shuffleboard_codelab.sd_widgets.SmartDashboardNames;
import edu.wpi.first.shuffleboard.api.data.ComplexData;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("PMD.DataClass")
public class SpinnyWheelData extends ComplexData<SpinnyWheelData> {

    private final double m_motorSpeed;


    public SpinnyWheelData() {
        this(0.0);
    }

    public SpinnyWheelData(Map<String, Object> map) {
        this("", map);
    }

    public SpinnyWheelData(String prefix, Map<String, Object> map) {
        this((Double) map.getOrDefault(prefix + "/" + SmartDashboardNames.SPINNY_WHEEL_MOTOR_SPEED, 0.0));
    }

    public SpinnyWheelData(double motorSpeed) {
        m_motorSpeed = motorSpeed;
    }

    @Override
    public Map<String, Object> asMap() {
        return asMap("");
    }

    public Map<String, Object> asMap(String prefix) {
        Map<String, Object> map = new HashMap<>();
        map.put(prefix + SmartDashboardNames.SPINNY_WHEEL_MOTOR_SPEED, m_motorSpeed);
        return map;
    }

    public static boolean hasChanged(Map<String, Object> changes) {
        return hasChanged(SmartDashboardNames.SPINNY_WHEEL_TABLE_NAME + "/", changes);
    }

    public static boolean hasChanged(String prefix, Map<String, Object> changes) {
        boolean changed = false;
        changed |= changes.containsKey(prefix + SmartDashboardNames.SPINNY_WHEEL_MOTOR_SPEED);

        return changed;
    }

    public double getMotorSpeed() {
        return m_motorSpeed;
    }
}
