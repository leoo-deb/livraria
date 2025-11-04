package livraria;

import exceptions.BookstoreException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Livraria {
    private String op;
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Livro> livros = new ArrayList<>();

    public void addLivro() {
        String titulo, sinopse, autor;
        do {
            System.out.println("---------------------");

            while (true) {
                System.out.print("Digite o titulo do livro: ");
                titulo = sc.nextLine().toUpperCase();

                if (titulo.matches("^[a-zA-Z\\d\\s]{1,40}$")) {
                    for (Livro verificarTitulo : livros) {
                        if (titulo.equals(verificarTitulo.getTitulo())) {
                            System.out.println("ERROR: Ja possui um livro com este titulo.");
                            return;
                        }
                    }
                    break;
                }
                System.out.println("ERROR: Titulo invalido.");
            }

            while (true) {
                System.out.print("Digite a sinopse do livro: ");
                sinopse = sc.nextLine();

                if (sinopse.matches("^[a-zA-Z\\d\\s]{3,}$")) {
                    break;
                }
                System.out.println("ERROR: Sinopse invalida.");
            }

            while (true) {
                System.out.print("Digite o autor(a) do livro: ");
                autor = sc.nextLine().toUpperCase();

                if (autor.matches("^[a-zA-Z\\s(-,|)?]{4,50}$")) {
                    break;
                }
                System.out.println("ERROR: Nome de autor invalido.");
            }

            Livro newLivro = new Livro(titulo.toUpperCase(), sinopse, autor.toUpperCase());
            System.out.println("SUCCESS: Livro adicionado com sucesso!");
            System.out.println("---------------------");
            System.out.println(newLivro);
            System.out.println("---------------------");
            livros.add(newLivro);

            System.out.print("Pressione a tecla (ENTER) para voltar ao inicio.");
            op = sc.nextLine();
        } while (!op.isBlank());
    }

    public void managerLivro() throws BookstoreException {
        int op2;
        if (!livros.isEmpty()) {
            do {
                ViewsLivraria.managerLivros();
                op2 = sc.nextInt();
                sc.nextLine();

                if (op2 == 1) {
                    while (true) {
                        viewIndex();
                        System.out.printf("""
                                Selecione qual livro sera editado
                                (OBS: Digite apenas o numero/index listado acima):""");
                        int index = sc.nextInt();
                        sc.nextLine();

                        System.out.println("---------------------");
                        System.out.print("Digite o novo titulo: ");
                        String newTitulo = sc.nextLine().toUpperCase();

                        System.out.print("Digite a nova sinopse: ");
                        String newSinopse = sc.nextLine();

                        System.out.print("Digite o novo autor(a): ");
                        String newAutor = sc.nextLine().toUpperCase();

                        try {
                            if (!(newTitulo.isBlank() | newSinopse.isBlank() | newAutor.isBlank())) {
                                livros.set(index, new Livro(newTitulo, newSinopse, newAutor));
                                System.out.println("SUCCESS: Livro editado com sucesso! ");
                                break;
                            } else {
                                System.out.println("---------------------");
                                System.out.println("ERROR: Verififique se todos os campos foram preenchidos.");
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.printf("ERROR: O index/livro (%d) selecionado nao existe no sistema.\n", index);
                        }
                    }
                }

                if (op2 == 2) {
                    viewIndex();
                    System.out.printf("""
                            Selecione qual livro sera removido
                            (OBS: Digite apenas o numero/index listado acima):""");
                    int index = sc.nextInt();
                    sc.nextLine();

                    try {
                        livros.remove(index);
                        System.out.println("SUCCESS: Livro removido com sucesso! ");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.printf("ERROR: O index/livro (%d) selecionado nao existe no sistema.\n", index);
                    }
                    System.out.println("---------------------");
                }
            } while (op2 != 3);
        } else {
            throw new BookstoreException("Nenhum livro foi adicionado no memento.");
        }
    }

    public void findLivros() throws BookstoreException {
        if (!livros.isEmpty()) {
            do {
                System.out.println("""
                        ---------------------
                        Deseja buscar por:
                        [1] Nome do livro
                        [2] Autor do livro
                        ---------------------""");
                System.out.print("Selecione: ");
                int search = sc.nextInt();
                sc.nextLine();

                if (search == 1) {
                    ArrayList<Livro> resul = new ArrayList<>();
                    System.out.print("Nome do livro: ");
                    String searchName = sc.nextLine().toLowerCase();

                    for (Livro searchLivro: livros) {
                        if ((searchLivro.getTitulo().toLowerCase()).contains(searchName)) {
                            resul.add(searchLivro);
                        }
                    }

                    if (resul.isEmpty()) {
                        throw new NoSuchElementException("Nehnhum livro encontrado.");
                    }

                    System.out.println("Livro(s) encontrado(s): " + resul.size());
                    for (Livro resulLivro: resul) {
                        System.out.format("-------LIVRO %d-------\n", (livros.indexOf(resulLivro) + 1));
                        System.out.println(resulLivro);
                        System.out.println("---------------------");
                    }
                }

                if (search == 2) {
                    ArrayList<Livro> resul = new ArrayList<>();
                    System.out.print("Nome autor: ");
                    String searchAuthor = sc.nextLine().toLowerCase();

                    for (Livro searchLivro: livros) {
                        if ((searchLivro.getAutor().toLowerCase()).contains(searchAuthor)) {
                            resul.add(searchLivro);
                        }
                    }

                    if (resul.isEmpty()) {
                        throw new NoSuchElementException("Nehnhum livro encontrado.");
                    }

                    System.out.println("Livro(s) encontrado(s): " + resul.size());
                    for (Livro resulLivro: resul) {
                        System.out.format("-------LIVRO %d-------\n", (livros.indexOf(resulLivro) + 1));
                        System.out.println(resulLivro);
                        System.out.println("---------------------");
                    }
                }
                System.out.print("Deseja fazer uma nova busca? [S/N].");
                op = sc.nextLine().toUpperCase();
            } while (!op.equals("N"));
        } else {
            throw new BookstoreException("Nenhum livro foi adicionado no memento.");
        }
    }

    public void viewLivros() throws BookstoreException {
        if (!livros.isEmpty()) {
            do {
                for (Livro viewLivro : livros) {
                    System.out.format("-------LIVRO %d-------\n", (livros.indexOf(viewLivro) + 1));
                    System.out.println(viewLivro);
                    System.out.println("---------------------");
                }
                System.out.print("Pressione a tecla (ENTER) para voltar ao inicio.");
                op = sc.nextLine();
            } while (!op.isBlank());
        } else {
            throw new BookstoreException("Nenhum livro foi adicionado no memento.");
        }
    }

    private void viewIndex() {
        for (Livro a: livros) {
            System.out.format("-------INDEX %d-------\n", livros.indexOf(a));
            System.out.println(a);
            System.out.println("---------------------");
        }
    }
}