/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyalgotirhm.model;

import java.util.List;

/**
 *
 * @author arkan481
 *
 */
public class Band {

    private String name;
    private List<Music> songs;
    

    /**
     * A band model class
     * @param name is the name of the band
     * @param songs is the song that have been released by the band
     */
    
    public Band(String name, List<Music> songs) {
        this.name = name;
        this.songs = songs;
    }

    public Band(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSongs(List<Music> songs) {
        this.songs = songs;
    }

    public List<Music> getSongs() {
        return songs;
    }

}
