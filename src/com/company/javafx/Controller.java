
package com.company.javafx;

import com.company.Album;
import com.company.Artist;
import com.company.Genre;
import com.company.Song;
import com.company.controllers.AlbumsController;
import com.company.controllers.ArtistsController;
import com.company.controllers.GenresController;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    @FXML
    public TableColumn<Song, Genre> songGenreColumn;

    @FXML
    public TableView<Album> albumTableView;
    @FXML
    public TableColumn<Album, String> albumTitleColumn;
    @FXML
    public TableColumn<Album, Artist> albumArtistColumn;
    @FXML
    public TextField albumTitleTextField;

    @FXML
    public TableView<Genre> genreTableView;
    @FXML
    public TableColumn<Genre, String> genreNameColumn;
    @FXML
    public TextField genreNameTextField;

    //Song creation
    @FXML
    public TextField songTitleTextField;
    @FXML
    public DatePicker songReleaseDateDatePicker;
    @FXML
    public ChoiceBox<Integer> songLengthMinutesChoiceBox;
    @FXML
    public ChoiceBox<Integer> songLengthTensOfSecondsChoiceBox;
    @FXML
    public ChoiceBox<Integer> songLengthSecondsChoiceBox;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            fetchAllFromDB();
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
        songGenreColumn.setCellValueFactory(new PropertyValueFactory<Song, Genre>("genre"));

        albumTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        albumTitleColumn.setCellValueFactory(new PropertyValueFactory<Album, String>("title"));
        albumArtistColumn.setCellValueFactory(new PropertyValueFactory<Album, Artist>("artist"));
        albumTitleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        genreNameColumn.setCellValueFactory(new PropertyValueFactory<Genre, String>("name"));
        genreNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        songLengthMinutesChoiceBox.setItems(FXCollections.observableList(IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toList())));
        songLengthTensOfSecondsChoiceBox.setItems(FXCollections.observableList(IntStream.rangeClosed(0, 9).boxed().collect(Collectors.toList())));
        songLengthSecondsChoiceBox.setItems(FXCollections.observableList(IntStream.rangeClosed(0, 9).boxed().collect(Collectors.toList())));


    }


    public void changeArtistNameCellEvent(TableColumn.CellEditEvent editted) throws SQLException {
        Artist selectedArtist = artistTableView.getSelectionModel().getSelectedItem();
        selectedArtist.setName(editted.getNewValue().toString());
        ArtistsController.update(selectedArtist);
        fetchAllFromDB();
    }

    public void changeGenreNameCellEvent(TableColumn.CellEditEvent editted) throws SQLException {
        Genre selectedGenre = genreTableView.getSelectionModel().getSelectedItem();
        selectedGenre.setName(editted.getNewValue().toString());
        GenresController.update(selectedGenre);
        fetchAllFromDB();
    }

    public void changeAlbumTitleCellEvent(TableColumn.CellEditEvent editted) throws SQLException {
        Album selectedAlbum = albumTableView.getSelectionModel().getSelectedItem();
        selectedAlbum.setTitle(editted.getNewValue().toString());
        AlbumsController.update(selectedAlbum);
        fetchAllFromDB();
    }

    public void deleteArtists() throws SQLException {
        ObservableList<Artist> selectedArtists;
        selectedArtists = artistTableView.getSelectionModel().getSelectedItems();
        for (Artist a : selectedArtists) {
            ArtistsController.delete(a.id);
        }
        try {
            fetchAllFromDB();
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
            fetchAllFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createSong() throws SQLException {

        if (!songTitleTextField.getText().toString().equals("")
            && songReleaseDateDatePicker.getValue() != null
            && songLengthMinutesChoiceBox.getValue() != null
            && songLengthSecondsChoiceBox.getValue() != null
            && songLengthTensOfSecondsChoiceBox.getValue() != null
            && artistTableView.getSelectionModel().getSelectedItems().size() > 0
            && albumTableView.getSelectionModel().getSelectedItems().size() > 0
            && genreTableView.getSelectionModel().getSelectedItems().size() > 0) {
            String releaseDate = songReleaseDateDatePicker.getValue().toString();
            String length = songLengthMinutesChoiceBox.getValue().toString() +
                    ":" +
                    songLengthTensOfSecondsChoiceBox.getValue().toString() +
                    songLengthSecondsChoiceBox.getValue().toString();
            Song song = SongsController.create(songTitleTextField.getText().toString(),
                                                releaseDate,
                                                length,
                                                albumTableView.getSelectionModel().getSelectedItem(),
                                                artistTableView.getSelectionModel().getSelectedItems(),
                                                genreTableView.getSelectionModel().getSelectedItem());
            songTableView.getItems().add(song);
        }
    }

    public void createAlbum() throws SQLException {
        if (!albumTitleTextField.getText().toString().equals("") && artistTableView.getSelectionModel().getSelectedItems().size() != 0) {
            Album album = AlbumsController.create(albumTitleTextField.getText().toString(), artistTableView.getSelectionModel().getSelectedItems().get(0));
            albumTableView.getItems().add(album);
        }
    }

    public void deleteAlbums() throws SQLException {
        ObservableList<Album> selectedAlbums;
        selectedAlbums = albumTableView.getSelectionModel().getSelectedItems();
        for (Album a : selectedAlbums) {
            AlbumsController.delete(a.id);
        }
        try {
            fetchAllFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteGenres() throws SQLException {
        ObservableList<Genre> selectedGenres;
        selectedGenres = genreTableView.getSelectionModel().getSelectedItems();
        for (Genre g : selectedGenres) {
            GenresController.delete(g.id);
        }
        try {
            fetchAllFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createGenre() throws SQLException {
        if (!genreNameTextField.getText().toString().equals("")) {
            Genre genre = GenresController.create(genreNameTextField.getText().toString());
            genreTableView.getItems().add(genre);
        }
    }

    private void fetchAllFromDB() throws SQLException {
        artistTableView.setItems(FXCollections.observableList(ArtistsController.index()));
        songTableView.setItems(FXCollections.observableList(SongsController.index()));
        albumTableView.setItems(FXCollections.observableList(AlbumsController.index()));
        genreTableView.setItems(FXCollections.observableList(GenresController.index()));
    }
}
