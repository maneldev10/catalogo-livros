package com.emanuel.catalogo.service;

import com.emanuel.catalogo.model.Livro;
import com.emanuel.catalogo.model.Autor;
import com.emanuel.catalogo.dto.GutendexResponse;
import com.emanuel.catalogo.dto.GutendexBookDTO;
import com.emanuel.catalogo.dto.GutendexAuthorDTO;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import reactor.netty.http.client.HttpClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CatalogoService {

    private final List<Livro> livros = new ArrayList<>();

    private final WebClient webClient;

    public CatalogoService() {
        HttpClient httpClient = HttpClient.create()
                .followRedirect(true);

        this.webClient = WebClient.builder()
                .baseUrl("https://gutendex.com/books/")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    public Livro buscarLivroNaAPI(String titulo) {
        try {
            GutendexResponse response = webClient.get()
                    .uri(uriBuilder -> uriBuilder.queryParam("search", titulo).build())
                    .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                    .retrieve()
                    .bodyToMono(GutendexResponse.class)
                    .block();

            if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
                GutendexBookDTO dto = response.getResults().get(0);

                Autor autor = new Autor(
                        dto.getAuthors() != null && !dto.getAuthors().isEmpty()
                                ? dto.getAuthors().get(0).getName()
                                : "Desconhecido",
                        0,
                        0
                );

                String idioma = Optional.ofNullable(dto.getLanguage())
                        .filter(l -> !l.isEmpty())
                        .map(l -> l.get(0))
                        .orElse("unknown");

                return new Livro(
                        dto.getTitle(),
                        idioma,
                        dto.getDownloadCount(),
                        autor
                );
            } else {
                System.out.println("Livro não encontrado na API.");
            }

        } catch (WebClientResponseException e) {
            System.out.println("Erro na resposta da API: " + e.getMessage());
        } catch (WebClientRequestException e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        return null;
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
            return;
        }
        livros.forEach(System.out::println);
    }

    public void listarAutores() {
        if (livros.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
            return;
        }
        livros.stream()
                .map(Livro::getAutor)
                .distinct()
                .forEach(System.out::println);
    }

    public void listarAutoresPorAno(Scanner scanner) {
        System.out.print("Digite o ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        livros.stream()
                .map(Livro::getAutor)
                .distinct()
                .filter(a -> a.getAnoNascimento() <= ano && (a.getAnoFalecimento() >= ano || a.getAnoFalecimento() == 0))
                .forEach(System.out::println);
    }

    public void listarLivrosPorIdioma(Scanner scanner) {
        System.out.print("Digite o idioma (PT, EN, FR, ES): ");
        String idioma = scanner.nextLine().toUpperCase();
        livros.stream()
                .filter(l -> l.getIdioma().equalsIgnoreCase(idioma))
                .forEach(System.out::println);
    }

    public void buscarLivro(Scanner scanner) {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        Livro livro = buscarLivroNaAPI(titulo);
        if (livro != null) {
            adicionarLivro(livro);
            System.out.println("Livro adicionado: " + livro);
        }
    }
}
