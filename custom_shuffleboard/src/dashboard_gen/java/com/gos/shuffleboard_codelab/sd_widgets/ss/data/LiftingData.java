package com.gos.shuffleboard_codelab.sd_widgets.ss.data;

import com.gos.shuffleboard_codelab.sd_widgets.SmartDashboardNames;
import edu.wpi.first.shuffleboard.api.data.ComplexData;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("PMD.DataClass")
public class LiftingData extends ComplexData<LiftingData> {

    private final double m_speed;
    private final double m_height;
    private final boolean m_upperLimitSwitch;
    private final boolean m_lowerLimitSwitch;


    public LiftingData() {
        this(0.0, 0.0, false, false);
    }

    public LiftingData(Map<String, Object> map) {
        this("", map);
    }

    public LiftingData(String prefix, Map<String, Object> map) {
        this(
            (Double) map.getOrDefault(prefix + "/" + SmartDashboardNames.LIFTING_MOTOR_SPEED, 0.0),
            (Double) map.getOrDefault(prefix + "/" + SmartDashboardNames.LIFTING_HEIGHT, 0.0),
            (Boolean) map.getOrDefault(prefix + "/" + SmartDashboardNames.LIFTING_UPPER_LIMIT_SWITCH, false),
            (Boolean) map.getOrDefault(prefix + "/" + SmartDashboardNames.LIFTING_LOWER_LIMIT_SWITCH, false));
    }

    public LiftingData(double speed, double height, boolean upperLimitSwitch, boolean lowerLimitSwitch) {
        m_speed = speed;
        m_height = height;
        m_upperLimitSwitch = upperLimitSwitch;
        m_lowerLimitSwitch = lowerLimitSwitch;
    }

    @Override
    public Map<String, Object> asMap() {
        return asMap("");
    }

    public Map<String, Object> asMap(String prefix) {
        Map<String, Object> map = new HashMap<>();
        map.put(prefix + SmartDashboardNames.LIFTING_MOTOR_SPEED, m_speed);
        map.put(prefix + SmartDashboardNames.LIFTING_HEIGHT, m_height);
        map.put(prefix + SmartDashboardNames.LIFTING_UPPER_LIMIT_SWITCH, m_upperLimitSwitch);
        map.put(prefix + SmartDashboardNames.LIFTING_LOWER_LIMIT_SWITCH, m_lowerLimitSwitch);
        return map;
    }

    public static boolean hasChanged(Map<String, Object> changes) {
        return hasChanged(SmartDashboardNames.LIFTING_TABLE_NAME + "/", changes);
    }

    public static boolean hasChanged(String prefix, Map<String, Object> changes) {
        boolean changed = false;
        changed |= changes.containsKey(prefix + SmartDashboardNames.LIFTING_MOTOR_SPEED);
        changed |= changes.containsKey(prefix + SmartDashboardNames.LIFTING_HEIGHT);
        changed |= changes.containsKey(prefix + SmartDashboardNames.LIFTING_UPPER_LIMIT_SWITCH);
        changed |= changes.containsKey(prefix + SmartDashboardNames.LIFTING_LOWER_LIMIT_SWITCH);

        return changed;
    }

    public double getSpeed() {
        return m_speed;
    }

    public double getHeight() {
        return m_height;
    }

    public boolean isAtUpperLimit() {
        return m_upperLimitSwitch;
    }

    public boolean isAtLowerLimit() {
        return m_lowerLimitSwitch;
    }
}
