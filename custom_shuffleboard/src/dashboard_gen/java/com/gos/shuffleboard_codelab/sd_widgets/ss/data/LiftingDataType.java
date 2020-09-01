package com.gos.shuffleboard_codelab.sd_widgets.ss.data;

import com.gos.shuffleboard_codelab.sd_widgets.SmartDashboardNames;
import edu.wpi.first.shuffleboard.api.data.ComplexDataType;

import java.util.Map;
import java.util.function.Function;

public class LiftingDataType extends ComplexDataType<LiftingData> {

    public LiftingDataType() {
        super(SmartDashboardNames.LIFTING_TABLE_NAME, LiftingData.class);
    }

    @Override
    public Function<Map<String, Object>, LiftingData> fromMap() {
        return LiftingData::new;
    }

    @Override
    public LiftingData getDefaultValue() {
        return new LiftingData();
    }
}
