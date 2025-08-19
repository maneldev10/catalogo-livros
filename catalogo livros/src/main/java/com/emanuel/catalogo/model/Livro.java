package com.emanuel.catalogo.model;

public class Livro {
    private String titulo;
    private String idioma;
    private int downloads;
    private Autor autor;

    public Livro(String titulo, String idioma, int downloads, Autor autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.downloads = downloads;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getDownloads() {
        return downloads;
    }

    public Autor getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", idioma='" + idioma + '\'' +
                ", downloads=" + downloads +
                ", autor=" + autor.getNome() +
                '}';
    }
}
