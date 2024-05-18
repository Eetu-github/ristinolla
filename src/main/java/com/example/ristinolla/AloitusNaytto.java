package com.example.ristinolla;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

import java.util.Map;

/**
 * Aloitusnäyttö jossa pelaaja voi valita nimen sekä aloittaa pelin
 * Aloitusnäytölle ilmestyy myös voittolaskuri
 */
public class AloitusNaytto {
    /**
     * StackPane aloitusnäyttö
     */
    private StackPane paneeli;
    /**
     * Ilmoiottaa näytölle tulevista viesteistä
     */
    private Label ilmoitus;
    /**
     * Aloita peli-nappi
     */
    private Button aloitusnappi;
    /**
     * Pelaa uudelleen-nappi
     */
    private Button uudelleenNappi;
    /**
     * Näyttää pelaaja 1
     */
    private Label lPelaaja1;
    /**
     * Näyttää Pelaaja 2
     */
    private Label lPelaaja2;
    /**
     * Tekstikenttä pelaajan 1 nimen täyttämiselle
     */
    private TextField tfPelaaja1;
    /**
     * Tekstikenttä pelaajan 2 tayttämiselle
     */
    private TextField tfPelaaja2;
    /**
     * Ilmoittaa voittajan
     */
    private Label voittoIlmoitus;

    /**
     * Palauttaa StackPanen eli aloitusnäytön
     *
     * @return Paneeli eli aloitusnäyttö
     */
    public StackPane getStackPane() {
        return paneeli;
    }

    /**
     * ALoitusnäytön ulkomuoto joka sisältää kaiken aloitusnäytöllä näkyvän
     * javafx-ympäristössä
     */
    public AloitusNaytto() {
        paneeli = new StackPane();
        paneeli.setMinSize(600, 200);
        paneeli.setStyle("-fx-border-color: black");

        ilmoitus = new Label("Ristinolla");
        ilmoitus.setFont(Font.font(24));
        ilmoitus.setTranslateY(-60);
        ilmoitus.setAlignment(Pos.CENTER);
        paneeli.getChildren().add(ilmoitus);

        uudelleenNappi = new Button("Pelaa uudelleen");
        uudelleenNappi.setMinSize(100, 30);
        uudelleenNappi.setTranslateY(-30);
        paneeli.getChildren().add(uudelleenNappi);

        aloitusnappi = new Button("Aloita Peli");
        aloitusnappi.setMinSize(100, 30);
        aloitusnappi.setTranslateY(-30);
        paneeli.getChildren().add(aloitusnappi);

        lPelaaja1 = new Label("Pelaaja 1: ");
        lPelaaja1.setFont(Font.font(18));
        lPelaaja1.setTranslateY(70);
        lPelaaja1.setTranslateX(-240);
        lPelaaja1.setAlignment(Pos.BOTTOM_LEFT);
        paneeli.getChildren().add(lPelaaja1);

        tfPelaaja1 = new TextField();
        tfPelaaja1.setFont(Font.font(18));
        tfPelaaja1.setAlignment(Pos.BOTTOM_LEFT);
        tfPelaaja1.setMaxWidth(150);
        tfPelaaja1.setTranslateX(-125);
        tfPelaaja1.setTranslateY(70);
        paneeli.getChildren().add(tfPelaaja1);

        lPelaaja2 = new Label("Pelaaja 2: ");
        lPelaaja2.setFont(Font.font(18));
        lPelaaja2.setTranslateY(70);
        lPelaaja2.setTranslateX(100);
        lPelaaja2.setAlignment(Pos.BOTTOM_RIGHT);
        paneeli.getChildren().add(lPelaaja2);

        tfPelaaja2 = new TextField();
        tfPelaaja2.setFont(Font.font(18));
        tfPelaaja2.setAlignment(Pos.BOTTOM_RIGHT);
        tfPelaaja2.setMaxWidth(150);
        tfPelaaja2.setTranslateY(70);
        tfPelaaja2.setTranslateX(215);
        paneeli.getChildren().add(tfPelaaja2);

        voittoIlmoitus = new Label();
        voittoIlmoitus.setFont(Font.font(18));
        voittoIlmoitus.setTranslateY(30);
        voittoIlmoitus.setAlignment(Pos.CENTER);
        paneeli.getChildren().add(voittoIlmoitus);
    }

    /**
     * Näyttää voitot aloitusnäytöllä
     *
     * @param voitot pelaajan voitot
     */
    public void naytaVoitot(Map<String, Integer> voitot) {
        StringBuilder voittoTeksti = new StringBuilder("Voitot: ");
        for (Map.Entry<String, Integer> entry : voitot.entrySet()) {
            voittoTeksti.append(entry.getKey()).append(": ").append(entry.getValue()).append("  ");
        }
        voittoIlmoitus.setText(voittoTeksti.toString());
    }

    /**
     * Päivittää pelin kulun mukaan vuoroilmoituksen aloitusnäytöllä
     *
     * @param pelaaja Pelaajan vuoro
     */
    public void paivitaVuoroIlmoitus(String pelaaja) {
        this.ilmoitus.setText("Pelaajan " + pelaaja + " vuoro");
    }

    /**
     * Päivittää ilmoituksen aloitusnäytöllä
     *
     * @param ilmoitus Uusi ilmoitus
     */
    public void paivitaIlmoitus(String ilmoitus) {
        this.ilmoitus.setText(ilmoitus);
    }

    /**
     * Asettaa aloita peli-napin näkyväksi
     */
    public void naytaaloitusnappi() {
        aloitusnappi.setVisible(true);
    }

    /**
     * Asettaa aloita peli-napin piiloon
     */
    public void piilotaaloitusnappi() {
        aloitusnappi.setVisible(false);
    }

    /**
     * Luo aloitusnapille toiminnon aloittaa peli
     *
     * @param onAction Aloita peli
     */
    public void aloitusnapilletoiminto(EventHandler<ActionEvent> onAction) {
        aloitusnappi.setOnAction(onAction);
    }

    /**
     * Asettaa pelaa uudelleen napin näkyväksi
     */
    public void naytauudelleenNappi() {
        uudelleenNappi.setVisible(true);
    }

    /**
     * Asettaa pelaa uudelleen-napin piiloon
     */
    public void piilotaUudelleenNappi() {
        uudelleenNappi.setVisible(false);
    }

    /**
     * Asettaa pelaa uudelleen napille toiminnon
     *
     * @param onAction Pelaa uudelleen
     */
    public void uudelleenNapilletoiminto(EventHandler<ActionEvent> onAction) {
        uudelleenNappi.setOnAction(onAction);
    }

    /**
     * Palauttaa pelaajan 1 nimen
     *
     * @return Pelaajan 1 nimi
     */
    public String getPelaaja1Nimi() {
        return tfPelaaja1.getText();
    }

    /**
     * Palauttaa pelaajan 2 nimen
     *
     * @return Pelaajan 2 nimi
     */
    public String getPelaaja2Nimi() {
        return tfPelaaja2.getText();
    }

    /**
     * Asettaa pelaajan 1 nimen
     *
     * @param nimi Pelaajan 1 nimi
     */
    public void setPelaaja1Nimi(String nimi) {
        tfPelaaja1.setText(nimi);
    }

    /**
     * Asettaa pelaajan 2 nimen
     *
     * @param nimi Pelaajan 2 nimi
     */
    public void setPelaaja2Nimi(String nimi) {
        tfPelaaja2.setText(nimi);
    }
}