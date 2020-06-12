/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyalgotirhm.model.genre;

/**
 *
 * @author arkan481
 */
public abstract class Genre {
    
    /**
     * Genre Base Model Class
     * This class will be inherited by all available genre
     */
    
    public Genre() {
        
    }
    
    // This method should be overridden by the child class and should return the name of the genre
    public abstract String getGenre();
}
