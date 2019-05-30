
package com.company.javafx;

import com.company.Artist;
import com.company.controllers.ArtistsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TableView<Artist> artistTableView;
    @FXML
    public TableColumn<Artist, String> artistNameColumn;
    @FXML
    public TextField artistNameTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            artistTableView.setItems(FXCollections.observableList(ArtistsController.index()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        artistNameColumn.setCellValueFactory(new PropertyValueFactory<Artist, String>("name"));
        artistTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        artistTableView.setEditable(true);
        artistNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        artistTableView.setCellFactory(TextFieldListCell.forListView());
    }

    public void changeArtistNameCellEvent(TableColumn.CellEditEvent editted) throws SQLException {
        Artist selectedArtist = artistTableView.getSelectionModel().getSelectedItem();
        selectedArtist.setName(editted.getNewValue().toString());
        ArtistsController.update(selectedArtist);
    }

    public void deleteArtists() throws SQLException {
        ObservableList<Artist> selectedArtists;
        selectedArtists = artistTableView.getSelectionModel().getSelectedItems();
        for (Artist a : selectedArtists) {
            ArtistsController.delete(a.id);
        }
        try {
            artistTableView.setItems(FXCollections.observableList(ArtistsController.index()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createArtist() throws SQLException {
        if (!artistNameTextField.getText().toString().equals("")) {
            Artist artist = ArtistsController.create(artistNameTextField.getText().toString());
            artistTableView.getItems().add(artist);
        }
    }
}
