
package com.company.javafx;

import com.company.Artist;
import com.company.controllers.ArtistsController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public ListView<Artist> artistListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
        artistListView.setItems(FXCollections.observableList(ArtistsController.index()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
