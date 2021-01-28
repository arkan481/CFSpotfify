/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotifyalgotirhm;

/**
 *
 * @author arkan481
 */
public class SpotifyAlgotirhm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
//        Spotify spotify = new Spotify();
//        spotify.run();

        String iniKataKataNya = "anekamakanan.";
        int totalSemuaKataSebelumTitik = 0;
        int totalSemuaVocalSebelumTitik = 0;

        for (int i = 0; i < iniKataKataNya.length(); i++) {
            if (iniKataKataNya.charAt(i) == '.') {
                break;
            }

            totalSemuaKataSebelumTitik++;
            if (iniKataKataNya.charAt(i) == 'a'
                    || iniKataKataNya.charAt(i) == 'A'
                    || iniKataKataNya.charAt(i) == 'i'
                    || iniKataKataNya.charAt(i) == 'I'
                    || iniKataKataNya.charAt(i) == 'u'
                    || iniKataKataNya.charAt(i) == 'U'
                    || iniKataKataNya.charAt(i) == 'e'
                    || iniKataKataNya.charAt(i) == 'E'
                    || iniKataKataNya.charAt(i) == 'o'
                    || iniKataKataNya.charAt(i) == 'O') {
                totalSemuaVocalSebelumTitik++;
            }

        }

        System.out.println("jumlah semua kata: " + totalSemuaKataSebelumTitik + "\njumlah semua vocal: " + totalSemuaVocalSebelumTitik);

    }

}
