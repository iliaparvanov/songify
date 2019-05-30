
package com.company.javafx;

import com.company.Album;
import com.company.Artist;
import com.company.Song;
import com.company.controllers.ArtistsController;
import com.company.controllers.SongsController;
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
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TableView<Artist> artistTableView;
    @FXML
    public TableColumn<Artist, String> artistNameColumn;
    @FXML
    public TextField artistNameTextField;

    @FXML
    public TableView<Song> songTableView;
    @FXML
    public TableColumn<Song, String> songTitleColumn;
    @FXML
    public TableColumn<Song, String> songReleaseDateColumn;
    @FXML
    public TableColumn<Song, String> songLengthColumn;
    @FXML
    public TableColumn<Song, Album> songAlbumColumn;
    @FXML
    public TableColumn<Song, List<Artist>> songArtistsColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            artistTableView.setItems(FXCollections.observableList(ArtistsController.index()));
            songTableView.setItems(FXCollections.observableList(SongsController.index()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        artistNameColumn.setCellValueFactory(new PropertyValueFactory<Artist, String>("name"));
        artistTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        artistTableView.setEditable(true);
        artistNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        songTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        songTitleColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        songReleaseDateColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("releaseDate"));
        songLengthColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("length"));
        songAlbumColumn.setCellValueFactory(new PropertyValueFactory<Song, Album>("album"));
        songArtistsColumn.setCellValueFactory(new PropertyValueFactory<Song, List<Artist>>("artists"));
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

    public void deleteSongs() throws SQLException {
        ObservableList<Song> selectedSongs;
        selectedSongs = songTableView.getSelectionModel().getSelectedItems();
        for (Song s : selectedSongs) {
            SongsController.delete(s.id);
        }
        try {
            songTableView.setItems(FXCollections.observableList(SongsController.index()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
