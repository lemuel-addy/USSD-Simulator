package com.affinitylabs.ussdsim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/com/affinitylabs/ussdsim/MainWindow.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("USSD Simulator");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
