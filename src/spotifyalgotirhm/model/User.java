/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyalgotirhm.model;

import java.util.List;
import javax.management.ConstructorParameters;

/**
 *
 * @author arkan481
 */
public class User {
    
    private String name;
    private List<Music> listenTo;
    private List<Music> recommendation;
    

    /**
    * User Model Class
     * @param name the name of the user
     * @param listenTo the array list of music that was recently played by the user
     * @param recommendation the array list of music that will be determined by the algorithm -
     * based on the music that is listened to by the user
     */
    
    public User(String name, List<Music> listenTo, List<Music> recommendation) {
        this.name = name;
        this.listenTo = listenTo;
        this.recommendation = recommendation;
    }

    public User(String name,List<Music> listenTo) {
        this.name = name;
        this.listenTo = listenTo;
    }

    public List<Music> getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(List<Music> recommendation) {
        this.recommendation = recommendation;
    }

    public void setListenTo(List<Music> listenTo) {
        this.listenTo = listenTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Music> getListenTo() {
        return listenTo;
    }
    
}
