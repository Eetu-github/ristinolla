package com.example.ristinolla;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Ristinolla-luokka joka toteuttaa päälogiikan pelistä
 *
 * @author Eetu Salonen
 * @version 1.0 2024/04/10
 */
public class RistiNolla extends Application {
    /**
     * Aloitusnäyttö luokka
     */
    private AloitusNaytto aloitusnaytto;
    /**
     * Pelilauta luokka
     */
    private PeliLauta peliLauta;
    /**
     * Pelaajan 1 nimi
     */
    String pelaaja1Nimi;
    /**
     * Pelaajan 2 nimi
     */
    String pelaaja2Nimi;
    /**
     * Metodi voittojen laskemiseen
     * Lähde: https://stackoverflow.com/questions/383570/mapping-of-strings-to-integers
     */
    private Map<String, Integer> voitot;

    /**
     * Ohjelman käynnistyksen ja toiminnallisuuden määrittely
     *
     * @param ristinolla Sovelluksen pääikkuna
     */
    @Override
    public void start(Stage ristinolla) {
        BorderPane root = new BorderPane();
        Scene kehys = new Scene(root, 600, 800);
        ristinolla.setTitle("Ristinolla");
        ristinolla.setScene(kehys);
        ristinolla.show();

        aloitusnaytto = new AloitusNaytto();
        aloitusnaytto.aloitusnapilletoiminto(this::aloitaPeli);
        aloitusnaytto.uudelleenNapilletoiminto(this::aloitaPeli);
        root.setTop(aloitusnaytto.getStackPane());

        voitot = new HashMap<>();
    }

    /**
     * Javafx:lle pelilaudan luominen
     * piilottaa myös aloita peli ja pelaa uudelleen nappulat
     *
     * @param e ActionEvent
     */
    private void aloitaPeli(ActionEvent e) {
        aloitusnaytto.piilotaaloitusnappi();
        aloitusnaytto.piilotaUudelleenNappi();
        pelaaja1Nimi = aloitusnaytto.getPelaaja1Nimi();
        pelaaja2Nimi = aloitusnaytto.getPelaaja2Nimi();
        peliLauta = new PeliLauta(aloitusnaytto, pelaaja1Nimi, pelaaja2Nimi, this);
        BorderPane paneeli2 = new BorderPane();
        paneeli2.getChildren().add(peliLauta.getStackPane());
        paneeli2.getChildren().add(aloitusnaytto.getStackPane());
        Scene kehys2 = new Scene(paneeli2, 600, 800);
        Stage peli = new Stage();
        peli.setScene(kehys2);
        peli.show();
        aloitusnaytto.paivitaVuoroIlmoitus(pelaaja1Nimi);
    }

    /**
     * Lisää voiton pelaajalle sekä näyttää voittoluvut
     *
     * @param pelaaja Voittanut pelaaja
     */
    public void kirjaaVoitto(String pelaaja) {
        voitot.put(pelaaja, voitot.getOrDefault(pelaaja, 0) + 1);
        aloitusnaytto.paivitaIlmoitus("Pelaaja " + pelaaja + " Voitti");
        aloitusnaytto.naytaVoitot(voitot);
    }

    /**
     * Pääohjelma joka käynnistää sovelluksen
     *
     * @param args Main-metodi
     */
    public static void main(String[] args) {
        launch(args);
    }
}
