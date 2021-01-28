package com.gos.shuffleboard_codelab.sd_widgets.ss;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

import com.gos.shuffleboard_codelab.sd_widgets.Utils;
import com.gos.shuffleboard_codelab.sd_widgets.ss.data.ElevatorData;
import com.gos.shuffleboard_codelab.sd_widgets.ss.data.PunchData;
import com.gos.shuffleboard_codelab.sd_widgets.ss.data.SpinningWheelData;

public class CodelabSuperStructureController {

    private static final double MAX_WIDTH = 35; // TODO figure out real value
    private static final double MAX_HEIGHT = 60; // TODO figure out real value


    @FXML
    public Rectangle m_base;

    @FXML
    public Circle m_spinningWheel;

    @FXML
    public Rectangle m_punch;

    @FXML
    public Rectangle m_elevatorBox;

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
        m_base.setWidth(32);
        m_base.setHeight(5);

        m_spinningWheel.setCenterX(32);
        m_spinningWheel.setCenterY(50);
        m_spinningWheel.setRadius(3);

        m_punch.setX(25);
        m_punch.setY(40);
        m_punch.setWidth(1.5);
        m_punch.setHeight(10);

        m_elevatorBox.setX(2);
        m_elevatorBox.setY(43);
        m_elevatorBox.setWidth(2);
        m_elevatorBox.setHeight(7);

        m_liftUpperLimitSwitch.setX(15);
        m_liftUpperLimitSwitch.setX(15);
        m_liftUpperLimitSwitch.setWidth(15);
        m_liftUpperLimitSwitch.setHeight(6);

        m_liftLowerLimitSwitch.setX(23);
        m_liftLowerLimitSwitch.setX(23);
        m_liftLowerLimitSwitch.setWidth(23);
        m_liftLowerLimitSwitch.setHeight(6);


    }


    public void updateElevator(ElevatorData elevatorData) {
        m_elevatorBox.setFill(Utils.getMotorColor(elevatorData.getSpeed()));
        m_elevatorBox.setHeight(elevatorData.getHeight());
        m_elevatorBox.setY(m_base.getY() - elevatorData.getHeight());
    }

    public void updatePunch(PunchData punchData) {
        if (punchData.isPunchExtended()) {
            m_punch.;

        }


    }

    public void updateSpinningWheel(SpinningWheelData spinningWheelData) {
        m_spinningWheel.setFill(Utils.getMotorColor(spinningWheelData.getMotorSpeed()));

    }


}
