package com.emanuel.catalogo;

import com.emanuel.catalogo.service.CatalogoService;
import java.util.Scanner;

public class CatalogoApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CatalogoService service = new CatalogoService();
        int opcao = -1;

        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1 - Buscar livro pelo título");
            System.out.println("2 - Listar livros registrados");
            System.out.println("3 - Listar autores");
            System.out.println("4 - Listar autores em determinado ano");
            System.out.println("5 - Listar livros em determinado idioma");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // consumir o Enter

            switch (opcao) {
                case 1:
                    service.buscarLivro(scanner);
                    break;
                case 2:
                    service.listarLivros();
                    break;
                case 3:
                    service.listarAutores();
                    break;
                case 4:
                    service.listarAutoresPorAno(scanner);
                    break;
                case 5:
                    service.listarLivrosPorIdioma(scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);  // ponto e vírgula obrigatório no final

        scanner.close();
    }
}
