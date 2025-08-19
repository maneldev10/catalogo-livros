package com.emanuel.catalogo.dto;

import java.util.List;

public class GutendexResponse {
    private List<GutendexBookDTO> results;

    public List<GutendexBookDTO> getResults() {
        return results;
    }

    public void setResults(List<GutendexBookDTO> results) {
        this.results = results;
    }
}
