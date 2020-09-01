package com.gos.shuffleboard_codelab.sd_widgets.ss.data;

import com.gos.shuffleboard_codelab.sd_widgets.SmartDashboardNames;
import edu.wpi.first.shuffleboard.api.data.ComplexDataType;

import java.util.Map;
import java.util.function.Function;

public class SpinnyWheelDataType extends ComplexDataType<SpinnyWheelData> {

    public SpinnyWheelDataType() {
        super(SmartDashboardNames.SPINNY_WHEEL_TABLE_NAME, SpinnyWheelData.class);
    }

    @Override
    public Function<Map<String, Object>, SpinnyWheelData> fromMap() {
        return SpinnyWheelData::new;
    }

    @Override
    public SpinnyWheelData getDefaultValue() {
        return new SpinnyWheelData();
    }
}
