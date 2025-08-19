package com.challenge_literatura.literatura.Servicio;

import com.challenge_literatura.literatura.Modelo.Libro;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiGutendex {
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public List<Libro> buscarLibro(String termino) throws IOException, InterruptedException {
        String url = "https://gutendex.com/books/?search=" + termino.replace(" ", "%20");
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject json = gson.fromJson(response.body(), JsonObject.class);
        JsonArray results = json.getAsJsonArray("results");

        List<Libro> libros = new ArrayList<>();
        for (JsonElement e : results) {
            libros.add(gson.fromJson(e, Libro.class));
        }
        return libros;
    }
}
