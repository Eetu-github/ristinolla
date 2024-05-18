package com.example.ristinolla;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * Pelilauta hallinnoi pelin tilaa ja tarkistaa voittovaihtoehdot pelin edetessä
 */
public class PeliLauta {
    /**
     * Pelilauta
     */
    private StackPane lauta;
    /**
     * pelilaudan ruudukko
     */
    private Ruutu[][] peliLauta = new Ruutu[3][3];
    /**
     * Pelaajan vuoro alkaen X pelaajasta
     */
    private char pelaajavuoro = 'X';
    /**
     * Aloitusnäyttö luokka
     */
    private AloitusNaytto aloitusnaytto;
    /**
     * Ilmoittaa onko peli loppu
     * alkuasetus false
     */
    private boolean peliLoppu = false;
    /**
     * Pelaajan 1 nimi
     */
    private String pelaaja1Nimi;
    /**
     * Pelaajan 2 nimi
     */
    private String pelaaja2Nimi;
    /**
     * RistiNolla luokka
     */
    private RistiNolla peli;

    /**
     * Palauttaa StackPanen joka on pelilauta
     *
     * @return lauta eli pelilauta
     */
    public StackPane getStackPane() {
        return lauta;
    }

    /**
     * @param aloitusnaytto Pelin aloitusnäyttö
     * @param pelaaja1Nimi  Pelaajan 1 nimi
     * @param pelaaja2Nimi  Pelaajan 2 nimi
     * @param peli          Viittaus pääohjelmaan
     */
    public PeliLauta(AloitusNaytto aloitusnaytto, String pelaaja1Nimi, String pelaaja2Nimi, RistiNolla peli) {
        this.aloitusnaytto = aloitusnaytto;
        this.pelaaja1Nimi = pelaaja1Nimi;
        this.pelaaja2Nimi = pelaaja2Nimi;
        this.peli = peli;
        lauta = new StackPane();
        lauta.setMinSize(500, 300);
        lauta.setTranslateX(300);
        lauta.setTranslateY(500);
        lisaapeliLauta();

        aloitusnaytto.setPelaaja1Nimi(pelaaja1Nimi);
        aloitusnaytto.setPelaaja2Nimi(pelaaja2Nimi);
    }

    /**
     * Lisää pelilautaan ruudut
     */
    private void lisaapeliLauta() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ruutu ruutu = new Ruutu();
                ruutu.getStackPane().setTranslateX((j * 200) - 200);
                ruutu.getStackPane().setTranslateY((i * 200) - 200);
                lauta.getChildren().add(ruutu.getStackPane());
                peliLauta[i][j] = ruutu;
            }
        }
    }

    /**
     * vaihtaa vuoroa pelilaudalla vuoron perään X ja O:n välillä
     */
    public void vaihdaVuoro() {
        String vuoroIlmoitus;
        if (pelaajavuoro == 'X') {
            pelaajavuoro = 'O';
            vuoroIlmoitus = pelaaja2Nimi;
            aloitusnaytto.paivitaIlmoitus("Pelaajan " + vuoroIlmoitus + " vuoro");
        } else {
            pelaajavuoro = 'X';
            vuoroIlmoitus = pelaaja1Nimi;
            aloitusnaytto.paivitaIlmoitus("Pelaajan " + vuoroIlmoitus + " vuoro");
        }
    }

    /**
     * get metodi pelaajan vuorolle
     *
     * @return Pelaajan vuoro
     */
    public String getPelaajaVuoro() {
        return String.valueOf(pelaajavuoro);
    }

    /**
     * Metodit voittajan tarkistamiseen
     * Tarkistaa jokaisen siirron jälkeen onko voittajaa löytynyt
     */
    public void tarkistaVoittaja() {
        tarkistaRivit();
        tarkistaPysty();
        tarkistaVylaOala();
        tarkistaOylaVala();
        onkoTaysi();
    }

    /**
     * Tarkistaa rivit
     */
    private void tarkistaRivit() {
        for (int i = 0; i < 3; i++) {
            if (peliLauta[i][0].getVuoro().equals(peliLauta[i][1].getVuoro()) &&
                    peliLauta[i][0].getVuoro().equals(peliLauta[i][2].getVuoro()) &&
                    !peliLauta[i][0].getVuoro().isEmpty()) {
                String voittaja = peliLauta[i][0].getVuoro();
                loppu(voittaja);
                return;
            }
        }
    }

    /**
     * tarkistaa pystyrivit
     */
    private void tarkistaPysty() {
        if (!peliLoppu) {
            for (int j = 0; j < 3; j++) {
                if (peliLauta[0][j].getVuoro().equals(peliLauta[1][j].getVuoro()) &&
                        peliLauta[0][j].getVuoro().equals(peliLauta[2][j].getVuoro()) &&
                        !peliLauta[0][j].getVuoro().isEmpty()) {
                    String voittaja = peliLauta[0][j].getVuoro();
                    loppu(voittaja);
                    return;
                }
            }
        }
    }

    /**
     * Tarkistaa laudan vasemmalta ylhäältä oikealle alas
     */
    private void tarkistaVylaOala() {
        if (!peliLoppu) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (peliLauta[0][0].getVuoro().equals(peliLauta[1][1].getVuoro()) &&
                            peliLauta[0][0].getVuoro().equals(peliLauta[2][2].getVuoro()) &&
                            !peliLauta[0][0].getVuoro().isEmpty()) {
                        String voittaja = peliLauta[0][0].getVuoro();
                        loppu(voittaja);
                        return;
                    }
                }
            }
        }
    }

    /**
     * tarkistaa laudan oikealta ylhäältä vasemmalle alas
     */
    private void tarkistaOylaVala() {
        if (!peliLoppu) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (peliLauta[0][2].getVuoro().equals(peliLauta[1][1].getVuoro()) &&
                            peliLauta[0][2].getVuoro().equals(peliLauta[2][0].getVuoro()) &&
                            !peliLauta[0][2].getVuoro().isEmpty()) {
                        String voittaja = peliLauta[0][2].getVuoro();
                        loppu(voittaja);
                        return;
                    }
                }
            }
        }
    }

    /**
     * tarkistaa onko kaikki ruudut täynnä
     * Jos on, peli ilmoittaa "tasapeli" ja peli alkaa uudelleen
     */
    private void onkoTaysi() {
        if (!peliLoppu) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (peliLauta[i][j].getVuoro().isEmpty()) {
                        return;
                    }
                }
            }
            peliLoppu = true;
            aloitusnaytto.paivitaIlmoitus("Tasapeli");
            aloitusnaytto.naytauudelleenNappi();
        }
    }

    /**
     * Jos X tai O voitti peli ilmoittaa siitä, kirjaa voiton ja peli alkaa uudelleen
     *
     * @param voittaja Pelin voittaja
     */
    private void loppu(String voittaja) {
        peliLoppu = true;
        String voittajaNimi;
        if (voittaja.equals("X")) {
            voittajaNimi = pelaaja1Nimi;
        } else {
            voittajaNimi = pelaaja2Nimi;
        }
        aloitusnaytto.paivitaIlmoitus("pelaaja " + voittajaNimi + " voitti!!!");
        aloitusnaytto.naytauudelleenNappi();

        peli.kirjaaVoitto(voittajaNimi);
    }

    /**
     * yksi ruutu pelilaudalla
     */
    private class Ruutu {
        private StackPane pane;
        private Label label;

        public Ruutu() {
            pane = new StackPane();
            pane.setMinSize(200, 200);

            Rectangle ruutu = new Rectangle();
            ruutu.setWidth(200);
            ruutu.setHeight(200);
            ruutu.setFill(Color.TRANSPARENT);
            ruutu.setStroke(Color.BLACK);

            pane.getChildren().add(ruutu);

            label = new Label("");
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(26));
            pane.getChildren().add(label);
            /**
             * Pelilaudalle hiiren klikkauksella haluttuun ruutuun valitaan X tai O riippuen
             * pelaajan vuorosta
             */
            pane.setOnMouseClicked(mouseEvent -> {
                if (label.getText().isEmpty() && !peliLoppu) {
                    label.setText(getPelaajaVuoro());
                    vaihdaVuoro();
                    tarkistaVoittaja();
                }
            });
        }

        /**
         * palauttaa StackPanen
         *
         * @return pane
         */
        public StackPane getStackPane() {
            return pane;
        }

        /**
         * Palauttaa kumman vuoro pelissä on
         *
         * @return Vuoro
         */
        public String getVuoro() {
            return label.getText();
        }

        /**
         * setteri vuoro metodille
         *
         * @param vuoro
         */
        public void setVuoro(String vuoro) {
            label.setText(vuoro);
        }
    }
}
