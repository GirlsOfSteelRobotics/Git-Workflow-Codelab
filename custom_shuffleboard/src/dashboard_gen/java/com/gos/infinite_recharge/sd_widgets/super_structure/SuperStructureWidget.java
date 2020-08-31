package com.gos.infinite_recharge.sd_widgets.super_structure;

import com.gos.infinite_recharge.sd_widgets.super_structure.data.ControlPanelData;
import com.gos.infinite_recharge.sd_widgets.super_structure.data.LiftData;
import com.gos.infinite_recharge.sd_widgets.super_structure.data.ShooterConveyorData;
import com.gos.infinite_recharge.sd_widgets.super_structure.data.ShooterIntakeData;
import com.gos.infinite_recharge.sd_widgets.super_structure.data.ShooterWheelsData;
import com.gos.infinite_recharge.sd_widgets.super_structure.data.SuperStructureData;
import com.gos.infinite_recharge.sd_widgets.super_structure.data.WinchData;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.Map;

@Description(name = "Infinite Recharge Super Structure Widget", dataTypes = {SuperStructureData.class})
@ParametrizedController("super_structure_widget.fxml")
public class SuperStructureWidget extends SimpleAnnotatedWidget<SuperStructureData> {
    @FXML
    private Pane m_root;

    @FXML
    protected SuperStructureController m_controller;

    @Override
    public Pane getView() {
        return m_root;
    }

    @FXML
    public void initialize() {

        dataOrDefault.addListener((ignored, oldData, newData) -> {
            final Map<String, Object> changes = newData.changesFrom(oldData);
            if (!changes.isEmpty()) {
                System.out.println("changes : " + changes); // NOPMD
            }

            if (ControlPanelData.hasChanged(changes)) {
                m_controller.updateControlPanel(newData.getControlPanel());
            }

            if (LiftData.hasChanged(changes)) {
                m_controller.updateLift(newData.getLiftData());
            }

            if (ShooterConveyorData.hasChanged(changes)) {
                m_controller.updateShooterConveyor(newData.getShooterConveyor());
            }

            if (ShooterIntakeData.hasChanged(changes)) {
                m_controller.updateShooterIntake(newData.getShooterIntake());
            }

            if (ShooterWheelsData.hasChanged(changes)) {
                m_controller.updateShooterWheels(newData.getShooterWheels());
            }

            if (WinchData.hasChanged(changes)) {
                m_controller.updateWinch(newData.getWinchData());
            }
        });
    }

}
