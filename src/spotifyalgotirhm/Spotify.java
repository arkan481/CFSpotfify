/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyalgotirhm;

import java.util.ArrayList;
import java.util.List;
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

    public void run() {
        populateData();
        currentUser = new User("Current User", userPlayList);
        currentUser.setRecommendation(userPlayList);
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
            System.out.println("4. Exit");
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
                    stop = true;
                    break;
                default:
                    System.out.println("Option Not Found...");
            }
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
                }else {
                    stop = true;
                    playMusic(musics.get(option-1));
                    break;
                }
            }
        }
    }

    private void defineAlgorithm() {

    }

    private void suggestNextSong() {
        // TODO : SORT BY BAND OR GENRE
        // TODO : USER RECOMMENDATION DATA LIKE GENRE, ETC
    }

    private void playMusic(Music music) throws InterruptedException {
        System.out.println("Song " + music.getTitle() + " Is Now Playing");
        for (int i = 0; i < 3; i++) {
            Thread.sleep(500);
            System.out.print(".");
        }
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
        List<Music> dummyMusicList1 = new ArrayList<>();
        User dummyUser1 = new User("dummy user 1", dummyMusicList1);

        // Populating app data
        allMusic.add(althea);
        allMusic.add(sdStreet);
        allMusic.add(californiacation);
        allMusic.add(scarTissue);
        allMusic.add(gravity);
        allMusic.add(helpless);
    }

}
