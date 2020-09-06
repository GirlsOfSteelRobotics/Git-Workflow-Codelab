package com.gos.shuffleboard_codelab.sd_widgets.ss;

import com.gos.shuffleboard_codelab.sd_widgets.Utils;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import com.gos.shuffleboard_codelab.sd_widgets.ss.data.LiftingData;
import com.gos.shuffleboard_codelab.sd_widgets.ss.data.PunchData;
import com.gos.shuffleboard_codelab.sd_widgets.ss.data.SpinnyWheelData;

public class CodelabSuperStructureController {

    private static final double BASE_WIDTH = 32;
    private static final double BASE_HEIGHT = 5;
    private static final double BASE_TOP = 50;

    private static final double PUNCH_X = 25;
    private static final double PUNCH_END_Y = BASE_TOP;
    private static final double PUNCH_WIDTH = 1.5;
    private static final double PUNCH_EXTENDED_HEIGHT = 20;
    private static final double PUNCH_EXTENDED_START_Y = PUNCH_END_Y - PUNCH_EXTENDED_HEIGHT;

    private static final double PUNCH_RETRACTED_HEIGHT = 10;
    private static final double PUNCH_RETRACTED_START_Y = PUNCH_END_Y - PUNCH_RETRACTED_HEIGHT;


    private static final double SPINNY_WHEEL_RADIUS = 3;
    private static final double SPINNY_WHEEL_CX = 32;
    private static final double SPINNY_WHEEL_CY = 50;

    private static final double LIFT_WIDTH = 2;
    private static final double LIFT_X = 2;
    private static final double LIFT_END_Y = BASE_TOP;
    private static final double LIFT_MIN_HEIGHT = 7;

    private static final double LIFT_LIMIT_SWITCH_X = 3;
    private static final double LIFT_LIMIT_SWITCH_HEIGHT = .5;
    private static final double LIFT_LIMIT_SWITCH_WIDTH = .5;
    private static final double LIFT_LOWER_LIMIT_SWITCH_Y = 40;
    private static final double LIFT_UPPER_LIMIT_SWITCH_Y = 50;

    private static final double MAX_WIDTH = 35; // TODO figure out real value
    private static final double MAX_HEIGHT = 60; // TODO figure out real value


    @FXML
    public Rectangle m_base;

    @FXML
    public Circle m_spinnyWheel;

    @FXML
    public Rectangle m_punch;

    @FXML
    public Rectangle m_liftBox;

    @FXML
    public Rectangle m_liftUpperLimitSwitch;

    @FXML
    public Rectangle m_liftLowerLimitSwitch;

    @FXML
    private Group m_group;

    @FXML
    private Pane m_pane;

    @FXML
    public void initialize() {

        DoubleBinding scaleBinding = Bindings.createDoubleBinding(() -> {
            double output = Math.min(m_pane.getWidth() / MAX_WIDTH, m_pane.getHeight() / MAX_HEIGHT);
            return output;
        }, m_pane.widthProperty(), m_pane.heightProperty());

        Scale scale = new Scale();
        scale.xProperty().bind(scaleBinding);
        scale.yProperty().bind(scaleBinding);

        m_group.getTransforms().add(scale);

        m_base.setX(1);
        m_base.setY(50);
        m_base.setWidth(BASE_WIDTH);
        m_base.setHeight(BASE_HEIGHT);

        m_spinnyWheel.setRadius(SPINNY_WHEEL_RADIUS);
        m_spinnyWheel.setCenterX(SPINNY_WHEEL_CX);
        m_spinnyWheel.setCenterY(SPINNY_WHEEL_CY);

        m_punch.setWidth(PUNCH_WIDTH);
        m_punch.setHeight(PUNCH_RETRACTED_HEIGHT);
        m_punch.setX(PUNCH_X);
        m_punch.setY(PUNCH_RETRACTED_START_Y);

        m_liftBox.setWidth(LIFT_WIDTH);
        m_liftBox.setHeight(LIFT_MIN_HEIGHT);
        m_liftBox.setX(LIFT_X);
        m_liftBox.setY(LIFT_END_Y - LIFT_MIN_HEIGHT);

        m_liftUpperLimitSwitch.setX(LIFT_LIMIT_SWITCH_X);
        m_liftUpperLimitSwitch.setY(LIFT_UPPER_LIMIT_SWITCH_Y);
        m_liftUpperLimitSwitch.setHeight(LIFT_LIMIT_SWITCH_HEIGHT);
        m_liftUpperLimitSwitch.setWidth(LIFT_LIMIT_SWITCH_WIDTH);

        m_liftLowerLimitSwitch.setX(LIFT_LIMIT_SWITCH_X);
        m_liftLowerLimitSwitch.setY(LIFT_LOWER_LIMIT_SWITCH_Y);
        m_liftLowerLimitSwitch.setHeight(LIFT_LIMIT_SWITCH_HEIGHT);
        m_liftLowerLimitSwitch.setWidth(LIFT_LIMIT_SWITCH_WIDTH);
    }


    public void updateLifting(LiftingData liftingData) {
        m_liftBox.setFill(Utils.getMotorColor(liftingData.getSpeed()));

        m_liftBox.setHeight(LIFT_MIN_HEIGHT + liftingData.getHeight());
        m_liftBox.setY(LIFT_END_Y - LIFT_MIN_HEIGHT - liftingData.getHeight());

    }

    public void updatePunch(PunchData punchData) {
        if (punchData.isPunchExtended()) {
            m_punch.setHeight(PUNCH_RETRACTED_HEIGHT);
            m_punch.setY(PUNCH_RETRACTED_START_Y);
        }
        else {
            m_punch.setHeight(PUNCH_EXTENDED_HEIGHT);
            m_punch.setY(PUNCH_EXTENDED_START_Y);
        }
    }

    public void updateSpinnyWheel(SpinnyWheelData spinnyWheelData) {
        m_spinnyWheel.setFill(Utils.getMotorColor(spinnyWheelData.getMotorSpeed()));
    }


}
