package com.gos.shuffleboard_codelab.sd_widgets.ss.data;

import com.gos.shuffleboard_codelab.sd_widgets.SmartDashboardNames;
import edu.wpi.first.shuffleboard.api.data.ComplexData;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("PMD.DataClass")
public class CodelabSuperStructureData extends ComplexData<CodelabSuperStructureData> {

    private final LiftingData m_lifting;
    private final PunchData m_punch;
    private final SpinnyWheelData m_spinnyWheel;

    public CodelabSuperStructureData() {

        m_lifting = new LiftingData();
        m_punch = new PunchData();
        m_spinnyWheel = new SpinnyWheelData();
    }

    public CodelabSuperStructureData(Map<String, Object> map) {

        m_lifting = new LiftingData(SmartDashboardNames.LIFTING_TABLE_NAME, map);
        m_punch = new PunchData(SmartDashboardNames.PUNCH_TABLE_NAME, map);
        m_spinnyWheel = new SpinnyWheelData(SmartDashboardNames.SPINNY_WHEEL_TABLE_NAME, map);
    }

    @Override
    public Map<String, Object> asMap() {
        Map<String, Object> map = new HashMap<>();

        map.putAll(m_lifting.asMap(SmartDashboardNames.LIFTING_TABLE_NAME + "/"));
        map.putAll(m_punch.asMap(SmartDashboardNames.PUNCH_TABLE_NAME + "/"));
        map.putAll(m_spinnyWheel.asMap(SmartDashboardNames.SPINNY_WHEEL_TABLE_NAME + "/"));
        return map;
    }

    public LiftingData getLifting() {
        return m_lifting;
    }

    public PunchData getPunch() {
        return m_punch;
    }

    public SpinnyWheelData getSpinnyWheel() {
        return m_spinnyWheel;
    }

}
