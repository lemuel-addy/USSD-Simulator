package com.affinitylabs.ussdsim;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class Settings extends Base {

    private Stage stage;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    private VBox rootNode;

    @FXML
    private TextArea txtServerUrl;

    @FXML
    private Button btnSave;

    @FXML
    private Slider timeoutSlider;

    @FXML
    private TextField txtPhone;

    @FXML
    private Label timeoutLabel;

    @FXML
    void btnSaveAction(ActionEvent event) {
        String serverUrl = txtServerUrl.getText();
        Double timeout = timeoutSlider.getValue();
        String phoneNumber = txtPhone.getText();
        if(serverUrl != null && !serverUrl.isEmpty()){
            preferences.put("url", serverUrl);
            preferences.putInt("timeout",timeout.intValue());
            preferences.put("phoneNumber", phoneNumber);
            btnSave.disableProperty().set(true);
        }
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);
        btnSave.disableProperty().set(true);

        String serverUrl = preferences.get("url","http://localhost:7000/ussd");
        int timeout = preferences.getInt("timeout", 10);
        String phoneNumber = preferences.get("phoneNumber","0243922922");

        timeoutLabel.setText(String.valueOf(timeout));
        timeoutSlider.setValue(timeout);
        txtServerUrl.setText(serverUrl);
        txtServerUrl.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s1, String s2) {
                if(s1.equalsIgnoreCase(s2)){
                   btnSave.disableProperty().set(true);
                }else{
                    btnSave.disableProperty().set(false);
                }
            }
        });
        timeoutSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number val1, Number val2) {
                btnSave.disableProperty().set(false);
                timeoutLabel.textProperty().set(String.valueOf(val2.intValue()));
            }
        });
    }

}
