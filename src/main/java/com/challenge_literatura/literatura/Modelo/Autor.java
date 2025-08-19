package com.challenge_literatura.literatura.Modelo;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Autor {
    String name;
    @SerializedName("birth_year")
    public Integer birthYear;
    @SerializedName("death_year")
    public Integer deathYear;

    @Override
    public String toString() {
        return name + " (" +
                birthYear + " - " +
                (deathYear != null ? deathYear : "vivo") + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autor autor)) return false;
        return Objects.equals(name, autor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
