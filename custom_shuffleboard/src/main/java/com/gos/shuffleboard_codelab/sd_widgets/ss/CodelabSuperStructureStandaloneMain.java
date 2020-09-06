package com.gos.shuffleboard_codelab.sd_widgets.ss;

import com.gos.shuffleboard_codelab.sd_widgets.SmartDashboardNames;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.gos.shuffleboard_codelab.sd_widgets.ss.data.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"PMD.ExcessiveMethodLength", "PMD.NPathComplexity"})
public class CodelabSuperStructureStandaloneMain {
    private final CodelabSuperStructureWidget m_controller;

    private double m_liftingSpeed;
    private double m_liftingHeight;
    private boolean m_liftingUpperLimitSwitch;
    private boolean m_liftingLowerLimitSwitch;
    private boolean m_punchIsPunchRetracted;
    private double m_spinnyWheelMotorSpeed;

    public CodelabSuperStructureStandaloneMain(Scene scene, CodelabSuperStructureWidget robotController) {
        m_controller = robotController;

        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            switch (code) {

            // Lifting
            case Q:
                m_liftingSpeed = 0.75;
                break;
            case A:
                m_liftingSpeed = -0.75;
                break;
            case S:
                m_liftingHeight -= 1;
                break;
            case W:
                m_liftingHeight += 1;
                break;
            case X:
                m_liftingUpperLimitSwitch = true;
                break;
            case Y:
                m_liftingLowerLimitSwitch = true;
                break;

            // Punch
            case D:
                m_punchIsPunchRetracted = true;
                break;

            // SpinnyWheel
            case O:
                m_spinnyWheelMotorSpeed = 0.5;
                break;
            case L:
                m_spinnyWheelMotorSpeed = -0.5;
                break;


            default:
                // ignored
            }
            handleUpdate();
        });


        scene.setOnKeyReleased(event -> {
            KeyCode code = event.getCode();
            switch (code) {

            // Lifting
            case Q:
            case A:
                m_liftingSpeed = 0;
                break;
            case X:
                m_liftingUpperLimitSwitch = false;
                break;
            case Y:
                m_liftingLowerLimitSwitch = false;
                break;

            // Punch
            case D:
                m_punchIsPunchRetracted = false;
                break;

            // SpinnyWheel
            case O:
            case L:
                m_spinnyWheelMotorSpeed = 0;
                break;

            default:
                break;
            }
            handleUpdate();
        });
    }

    private void handleUpdate() {

        try {
            Map<String, Object> map = new HashMap<>();

            map.putAll(new LiftingData(
                m_liftingSpeed,
                m_liftingHeight,
                m_liftingUpperLimitSwitch,
                m_liftingLowerLimitSwitch
                ).asMap(SmartDashboardNames.LIFTING_TABLE_NAME + "/"));

            map.putAll(new PunchData(
                m_punchIsPunchRetracted
                ).asMap(SmartDashboardNames.PUNCH_TABLE_NAME + "/"));

            map.putAll(new SpinnyWheelData(
                m_spinnyWheelMotorSpeed
                ).asMap(SmartDashboardNames.SPINNY_WHEEL_TABLE_NAME + "/"));


            m_controller.dataProperty().setValue(new CodelabSuperStructureData(map));
        } catch (ClassCastException ignored) {
            // don't worry about it
        }
    }

    public static class PseudoMain extends Application {

        @Override
        public void start(Stage primaryStage) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("codelab_super_structure_widget.fxml"));
            Pane root = loader.load();
            CodelabSuperStructureWidget controller = loader.getController();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            new CodelabSuperStructureStandaloneMain(scene, controller);

            primaryStage.show();
        }
    }

    @SuppressWarnings("JavadocMethod")
    public static void main(String[] args) {
        // JavaFX 11+ uses GTK3 by default, and has problems on some display
        // servers
        // This flag forces JavaFX to use GTK2
        // System.setProperty("jdk.gtk.version", "2");
        Application.launch(PseudoMain.class, args);
    }
}
