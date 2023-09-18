package com.affinitylabs.ussdsim;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public abstract class Base implements Initializable {

    protected Preferences preferences;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        preferences = Preferences.userRoot().node(this.getClass().getName());
    }
}
