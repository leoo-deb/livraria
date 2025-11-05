import exceptions.BookstoreException;
import exceptions.CredentialAuthenticationException;
import livraria.ContaLivraria;
import livraria.Livraria;
import livraria.ViewsLivraria;

import java.util.NoSuchElementException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int op, op2;
        Scanner sc = new Scanner(System.in);
        Livraria livraria = new Livraria();
        ContaLivraria conta = new ContaLivraria();

        do {
            ViewsLivraria.menu();
            op2 = sc.nextInt();
            sc.nextLine();

                if (op2 == 1) {
                    try {
                        conta.access();
                    } catch (CredentialAuthenticationException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }

                    do {
                        ViewsLivraria.menuLivraria();
                        op = sc.nextInt();
                        sc.nextLine();

                        if (op == 1) {
                            livraria.addLivro();
                        }

                        if (op == 2) {
                            try {
                                livraria.managerLivro();
                            } catch (BookstoreException e) {
                                System.out.println("ERROR: " + e.getMessage());
                            }
                        }

                        if (op == 3) {
                            try {
                                livraria.findLivros();
                            } catch (NoSuchElementException | BookstoreException e) {
                                System.out.println("ERROR: " + e.getMessage());
                            }
                        }

                        if (op == 4) {
                            try {
                                livraria.viewLivros();
                            } catch (BookstoreException e) {
                                System.out.println("ERROR: " + e.getMessage());
                            }
                        }
                    } while (op != 5);
                }

                if (op2 == 2) {
                    conta.create();
                }

                if (op2 == 3) {
                    try {
                        conta.logAcesso();
                    } catch (NoSuchElementException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                }
        } while (op2 != 4);
    }
}