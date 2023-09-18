package com.affinitylabs.ussdsim;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class MainWindow extends Base {

    private Parent optionsWindow;
    private String sessionId;
    private String startUrl;
    private String responseUrl;
    private String endUrl;
    private boolean isOptionsOpen = false;
    private UssdStartService startService;


    @FXML
    private Menu menuOptions;

    @FXML
    private MenuItem menuSettings;

    @FXML
    private TextArea txtScreen;

    @FXML
    private TextField txtInput;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSend;

    @FXML
    private StackPane stackPane;
    @FXML
    private ProgressIndicator progressIndicator;


    @FXML
    void clearClickAction(ActionEvent event) {
        txtScreen.setText("");
        btnClear.disableProperty().setValue(true);
    }

    @FXML
    void sendClickAction(ActionEvent event) {
        String input = txtInput.getText();
        if(input.matches("\\s+")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Enter Value!");
            alert.setTitle("Invalid Input");
            alert.show();
            txtInput.setText("");
        }
    }

    @FXML
    void startClickAction(ActionEvent event) {
        String serverUrl = preferences.get("url","http://localhost:7000/ussd");
        int timeout = preferences.getInt("timeout",10);
        UUID uuid = UUID.randomUUID();
        serverUrl =  serverUrl.concat("/session/").concat(uuid.toString());
        startUrl = serverUrl.concat("/start");
        responseUrl = serverUrl.concat("/response");
        endUrl = serverUrl.concat("/end");

        System.out.println(startUrl);
        System.out.println(responseUrl);
        System.out.println(endUrl);

        startService = new UssdStartService(startUrl);
        progressIndicator.visibleProperty().bind(startService.runningProperty());

        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(timeout), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startService.cancel();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Timeout");
                alert.setContentText("Session Timeout!");
                alert.show();
            }
        }));

        startService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                System.out.println("Succeeded");
                txtScreen.setText(startService.getValue());
                timeline.play();
            }
        });

        startService.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {

            }
        });
        startService.start();
    }


    @FXML
    void settingsAction(ActionEvent event) {
        System.out.println("In Settings Action");
        try{
            if(!isOptionsOpen){
                isOptionsOpen = true;
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/affinitylabs/ussdsim/Settings.fxml"));
                optionsWindow = (Parent) fxmlLoader.load();
                ((Settings) fxmlLoader.getController()).setStage(stage);
                Scene scene = new Scene(optionsWindow);
                stage.setScene(scene);
                stage.setTitle("Settings");
                stage.setResizable(false);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(btnSend.getScene().getWindow());
                stage.show();

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent windowEvent) {
                        isOptionsOpen = false;
                    }
                });
            }

        }catch(Exception ex){
            ex.printStackTrace(System.out);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);
        progressIndicator.visibleProperty().setValue(false);
        btnClear.disableProperty().setValue(true);
        btnSend.disableProperty().setValue(true);
        txtScreen.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                btnClear.disableProperty().setValue(false);
            }
        });
        txtInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s1, String s2) {
                if(s2.isEmpty()){
                    btnSend.disableProperty().setValue(true);
                }else{
                    btnSend.disableProperty().setValue(false);
                }

            }
        });
    }
}
