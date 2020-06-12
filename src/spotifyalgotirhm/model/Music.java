/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyalgotirhm.model;

import spotifyalgotirhm.model.genre.Genre;

/**
 *
 * @author arkan481
 */
public class Music {
    
    private String title;
    private Band band;
    private Genre genre;
    
    /**
     * Music model class
     * @param title the title of the song
     * @param band a band data type from the band model
     * @param genre a genre data type from the genre model
     */
    
    public Music(String title,Band band,Genre genre) {
        this.title = title;
        this.band = band;
        this.genre = genre;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Band getBand() {
        return band;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
}
