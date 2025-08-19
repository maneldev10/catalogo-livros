package com.emanuel.catalogo.dto;

import java.util.List;


public class GutendexBookDTO {
    private String title;
    private List<String> languages;
    private int download_count;
    private List<GutendexAuthorDTO> authors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLanguage() {
        return languages;
    }

    public void setLanguage(List<String> languages) {
        this.languages = languages;
    }

    public int getDownloadCount() {
        return download_count;
    }

    public void setDownloadCount(int download_count) {
        this.download_count = download_count;
    }

    public List<GutendexAuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<GutendexAuthorDTO> authors) {
        this.authors = authors;
    }
}
