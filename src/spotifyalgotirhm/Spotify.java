/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyalgotirhm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import spotifyalgotirhm.model.Band;
import spotifyalgotirhm.model.Music;
import spotifyalgotirhm.model.User;
import spotifyalgotirhm.model.genre.Alternative;
import spotifyalgotirhm.model.genre.Blues;
import spotifyalgotirhm.model.genre.Funk;
import spotifyalgotirhm.model.genre.Pop;

/**
 *
 * @author arkan481
 */
public class Spotify {

    /**
     * Operation Class All of app operation will be stored in this class
     */
    private User currentUser;
    private ArrayList<Music> userPlayList = new ArrayList<>();
    private ArrayList<Music> userReccomendation = new ArrayList<>();
    private ArrayList<Music> allMusic = new ArrayList<>();
    private ArrayList<User> dummyUsers = new ArrayList<>();

    // this variable will be used to find out all of the necessary data for the algoritm testing
    private ArrayList<Map<String,String>> debugVar = new ArrayList<>();

    // Method to run the app
    public void run() {
        populateData();
        /*
        * Setting the current user for a run-time session
        * userPlayList is an empty list of music that will changed as the user plays a song
        * userRecommendation is an empty list of music that will be changed and defined by the 
        * Collaborative Filtering Algorithm
         */
        currentUser = new User("Current User", userPlayList);
        currentUser.setRecommendation(userReccomendation);
        dummyUsers.add(currentUser);
        try {
            menu();
        } catch (InterruptedException ex) {
            Logger.getLogger(Spotify.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void menu() throws InterruptedException {
        boolean stop = false;
        Scanner sc1 = new Scanner(System.in);
        int option;
        System.out.println("Welcome to Spotify!");
        while (stop == false) {
            System.out.println("\n1. Play a song");
            System.out.println("2. Recently Played");
            System.out.println("3. Song Recommendation");
            System.out.println("4. Debug Algorithm");
            System.out.println("5. Exit");
            System.out.print("Your Option: ");
            option = sc1.nextInt();
            switch (option) {
                case 1:
                    musicList(allMusic);
                    break;
                case 2:
                    musicList(currentUser.getListenTo());
                    break;
                case 3:
                    musicList(currentUser.getRecommendation());
                    break;
                case 4:
                    debugAlgorithm();
                    break;
                case 5:
                    stop = true;
                    break;
                default:
                    System.out.println("Option Not Found...");
            }
        }
    }

    private void debugAlgorithm() {
        System.out.println(currentUser.getName() + " is definetly interested in");
        System.out.format("%-5s%-25s%-15s%-7s", "No","Title", "Band", "Genre");
        System.out.println("");
        for (int i = 0; i < currentUser.getListenTo().size(); i++) {
            System.out.format("%-5d%-25s-15s%-7s", i + 1,currentUser.getListenTo().get(i).getTitle(),currentUser.getListenTo().get(i).getBand().getName(),
                    currentUser.getListenTo().get(i).getGenre().getGenre());
            System.out.println("");
        }
        System.out.println(currentUser.getName() + " calculated recommendation");
        System.out.format("%-5s%-25s%-15s%-7s", "No","Title", "Band", "Genre");
        System.out.println("");
        for (int i = 0; i < currentUser.getRecommendation().size(); i++) {
            System.out.format("%-5d%-25s%-15s%-7s", i + 1,currentUser.getRecommendation().get(i).getTitle() ,currentUser.getRecommendation().get(i).getBand().getName(),
                    currentUser.getRecommendation().get(i).getGenre().getGenre());
            System.out.println("");
        }
        System.out.println("the recommendation is inherited from");
        for (int i = 0; i < debugVar.size(); i++) {
            System.out.println(debugVar.get(i));
        }

    }

    private void musicList(List<Music> musics) throws InterruptedException {
        if (musics.size() == 0) {
            System.out.println("We don't currently have any data on that, try listening to some music first");
        } else {
            boolean stop = false;
            Scanner sc1 = new Scanner(System.in);
            int option;
            int possibleIndex = musics.size();
            System.out.format("%-5s%-25s%-15s%-7s", "No", "Title", "Band", "Genre");
            System.out.println("");
            System.out.format("%-5s%-25s%-15s%-7s", "--", "-----", "----", "-----");
            System.out.println("");
            for (int i = 0; i < musics.size(); i++) {
                System.out.format("%-5d%-25s%-15s%-7s", i + 1, musics.get(i).getTitle(), musics.get(i).getBand().getName(), musics.get(i).getGenre().getGenre());
                System.out.println("");
            }
            while (stop == false) {
                System.out.print("Your Option: ");
                option = sc1.nextInt();
                if (option > possibleIndex) {
                    System.out.println("Invalid Option");
                } else {
                    stop = true;
                    playMusic(musics.get(option - 1));
                    break;
                }
            }
        }
    }

    private void defineAlgorithm(Music music) {
        for (int i = 0; i < dummyUsers.size(); i++) {
            // First for loop is being used to loop every available user in the app
            if (dummyUsers.get(i).getListenTo().contains(music)) {
                /*
                IF theres a similarity to a user in the app then the algorithm will grab all of those 
                particular user playlist
                 */
                for (int j = 0; j < dummyUsers.get(i).getListenTo().size(); j++) {
                    /**
                     * after grabbing every song in the similar user playlist
                     * the program will re-set the current user recommendation
                     */
                    
                    Map<String,String> map = new HashMap<>();
                    map.put(dummyUsers.get(i).getName(), dummyUsers.get(i).getListenTo().get(j).getTitle());
                    debugVar.add(map);
                    
                    if (userReccomendation.contains(dummyUsers.get(i).getListenTo().get(j))) {
                        // checking if the value in the list duplicates
                    } else {
                        userReccomendation.add(dummyUsers.get(i).getListenTo().get(j));
                    }

                }
            }
        }
        currentUser.setRecommendation(userReccomendation);
    }

    private void suggestNextSong(Music music) {
        // TODO : SORT BY BAND OR GENRE
        // TODO : USER RECOMMENDATION DATA LIKE GENRE, ETC
        defineAlgorithm(music);
    }

    private void playMusic(Music music) throws InterruptedException {
        System.out.println("Song " + music.getTitle() + " Is Now Playing");
        for (int i = 0; i < 3; i++) {
            Thread.sleep(500);
            System.out.print(".");
        }
        // Setting the recently played song for the current user
        List<Music> tempPlayList = currentUser.getListenTo();
        if (!tempPlayList.contains(music)) {
            // Checking if value duplicates
            tempPlayList.add(music);
            currentUser.setListenTo(tempPlayList);
        }
        suggestNextSong(music);
    }

    private void populateData() {

        // Populating the band
        Band rhcp = new Band("RHCP");
        Band gd = new Band("Grateful Dead");
        Band jmt = new Band("JM Trio");

        // Populating the music
        Music californiacation = new Music("Californiacation", rhcp, new Alternative());
        Music scarTissue = new Music("Scar Tissue", rhcp, new Alternative());

        Music althea = new Music("Althea", gd, new Blues());
        Music sdStreet = new Music("Shakedown Street", gd, new Funk());

        Music gravity = new Music("Gravity", jmt, new Pop());
        Music helpless = new Music("Helpless", jmt, new Funk());

        // Populating the band's songs
        // RHCP Band
        List<Music> rhcpSongs = new ArrayList<>();
        rhcpSongs.add(californiacation);
        rhcpSongs.add(scarTissue);
        rhcp.setSongs(rhcpSongs);

        // GD Band
        List<Music> gdSongs = new ArrayList<>();
        gdSongs.add(althea);
        gdSongs.add(sdStreet);
        gd.setSongs(gdSongs);

        // JMT Band
        List<Music> jmtSongs = new ArrayList<>();
        jmtSongs.add(gravity);
        jmtSongs.add(helpless);
        jmt.setSongs(jmtSongs);

        // Populating the user
        // User 1
        List<Music> dummyMusicList1 = new ArrayList<>();
        dummyMusicList1.add(californiacation);
        dummyMusicList1.add(althea);
        dummyMusicList1.add(sdStreet);
        User dummyUser1 = new User("dummy user 1", dummyMusicList1);

        // User 2
        List<Music> dummyMusicList2 = new ArrayList<>();
        dummyMusicList2.add(gravity);
        dummyMusicList2.add(californiacation);
        dummyMusicList2.add(sdStreet);
        User dummyUser2 = new User("dummy user 2", dummyMusicList2);

        // User 3
        List<Music> dummyMusicList3 = new ArrayList<>();
        dummyMusicList3.add(gravity);
        dummyMusicList3.add(scarTissue);
        dummyMusicList3.add(helpless);
        User dummyUer3 = new User("dummy user 3", dummyMusicList3);

        // Populating app data
        allMusic.add(althea);
        allMusic.add(sdStreet);
        allMusic.add(californiacation);
        allMusic.add(scarTissue);
        allMusic.add(gravity);
        allMusic.add(helpless);
        dummyUsers.add(dummyUser1);
        dummyUsers.add(dummyUser2);
        dummyUsers.add(dummyUer3);
    }

}
