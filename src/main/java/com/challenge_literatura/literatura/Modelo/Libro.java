package com.challenge_literatura.literatura.Modelo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Libro {
    int id;
    String title;
    public List<Autor> authors;
    public List<String> languages;
    @SerializedName("download_count")
    int downloadCount;

    @Override
    public String toString() {
        return id + " - " +
                title + " | Autores: " +
                authors + " | Idiomas: " +
                languages + " | Descargas: " +
                downloadCount;
    }
}
