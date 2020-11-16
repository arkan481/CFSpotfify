/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import spotifyalgotirhm.Spotify;
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
public class UnitTest1 extends TestCase {

    private static final Spotify instance = new Spotify();

    public UnitTest1(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // Test apakah dummy list (band, music, genre) sesuai: before class
    @BeforeClass
    public void testDummyList() {
        instance.populateData();

        ArrayList<Music> allMusic = new ArrayList<>();

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

        // Populating app data
        allMusic.add(althea);
        allMusic.add(sdStreet);
        allMusic.add(californiacation);
        allMusic.add(scarTissue);
        allMusic.add(gravity);
        allMusic.add(helpless);

        assertEquals(musicListToString(allMusic), musicListToString(instance.getAllMusic()));

        printWhatUserListens(instance.getDummyUsers());

        instance.run();
    }

    @Before
    public void testRecentlyPlayedBefore() {
        System.out.println("\nTesting Current User Recently Played Before Test: ");

        List<String> actualRecentlyPlayed = musicListToString(instance.getCurrentUser().getListenTo());

        printMusicLists(actualRecentlyPlayed);

        List<String> expectedRecentlyPlayed = new ArrayList<>();

        assertEquals(expectedRecentlyPlayed, actualRecentlyPlayed);

    }

    // Test apakah rekomendasi nya tepat: Before - After
    @Test
    public void testRecommendation() {
        try {
            System.out.println("");
            instance.playMusic(instance.getAllMusic().get(4));
        } catch (InterruptedException ex) {
            Logger.getLogger(UnitTest1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Saga Test Option Not Found
    
    // Saga Test Music List Kosong

    // Test recently played: Before - After
    @After
    public void testRecentlyPlayedAfter() {
        System.out.println("\nTesting Current User Recently Played After Test: ");

        List<String> actualRecentlyPlayed = musicListToString(instance.getCurrentUser().getListenTo());

        printMusicLists(actualRecentlyPlayed);

        List<Music> expectedRecentlyPlayed = new ArrayList<>();
        Band jmt = new Band("JM Trio");
        Music gravity = new Music("Gravity", jmt, new Pop());
        expectedRecentlyPlayed.add(gravity);

        assertEquals(musicListToString(expectedRecentlyPlayed), actualRecentlyPlayed);
    }

    // Test finished recommendation: After class
    @AfterClass
    public void testFinalRecommendation() {
        System.out.println("\nThe final recommendation is: ");
//        printMusicLists(musicListToString(instance.getUserReccomendation()));
        instance.debugAlgorithm();
    }

    private void printMusicLists(List<String> musicTitle) {
        if (musicTitle.isEmpty()) {
            System.out.println("Music List is empty");
        }

        for (int i = 0; i < musicTitle.size(); i++) {
            System.out.println(i + 1 + ". " + musicTitle.get(i));
        }
    }

    private void printWhatUserListens(List<User> users) {
        for (int i = 0; i < users.size(); i++) {
            System.out.println("User: " + users.get(i).getName());
            System.out.println("Listen to: ");

            for (int j = 0; j < users.get(i).getListenTo().size(); j++) {
                System.out.println(j + 1 + ". " + users.get(i).getListenTo().get(j).getTitle());
            }

            System.out.println("\n");
        }
    }

    private List<String> musicListToString(List<Music> musics) {
        List<String> musicTitles = new ArrayList();

        for (int i = 0; i < musics.size(); i++) {
            musicTitles.add(musics.get(i).getTitle());
        }

        return musicTitles;
    }
}
