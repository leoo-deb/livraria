package livraria;

import exceptions.CredentialAuthenticationException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ContaLivraria {;
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private ArrayList<String> logAcesso = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private String op;

    //INICIA O SISTEMA COM UMA CONTA PRE CRIADA: ADMIN
    public ContaLivraria() {
        funcionarios.add(new Funcionario("1", "1", "admin"));
    }

    //ACESSA A CONTA
    public void access() {
        var logDataTime = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        String logFormatter = logDataTime.format(DateTimeFormatter.ofPattern("HH:mm:ss - dd/MM/yyyy"));

        System.out.println("---------------------");
        System.out.print("CPF ou Serial: ");
        String cpf = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.println("---------------------");

        for (Funcionario acesso: funcionarios) {
            if (((cpf.equals(acesso.getCpf()) || cpf.equals(acesso.getId().toString())) && senha.equals(acesso.getPassword()))) {
                System.out.printf("SUCCESS: Login efetuado com sucesso! Bem-vindo %s.\n", acesso.getNome());
                logAcesso.add(String.format("Nome: %-10s | \t\tSerial: %s (%s)", acesso.getNome(), acesso.getId().toString(),logFormatter));
                return;
            }
        }

        throw new CredentialAuthenticationException("CPF/Senha incorreto. Verifique-os novamente.");
    }

    //CRIA UMA CONTA
    public void create() {
       String name, cpf, senha;
       do {
           System.out.println("---------------------");
           while (true) {
               System.out.print("Nome e sobrenome: ");
               name = sc.nextLine().toUpperCase();

               if (!(name.matches("^([a-zA-Z\\s]{3,30})$"))) {
                   System.out.println("ERROR: O nome deve conter no minimo 3 caracteres.");
               } else {
                   break;
               }
           }

           loop:
           while (true) {
               System.out.print("Insira um CPF (Somente numeros): ");
               cpf = sc.nextLine();

               if (!cpf.matches("^\\d{3}\\d{3}\\d{3}\\d{2}$")) {
                   System.out.println("ERROR: Este CPF esta invalido. Verifico-o novamente.");
                   continue;
               }

               for (Funcionario verificar: funcionarios) {
                   if (cpf.equals(verificar.getCpf())) {
                       System.out.println("ERROR: Ja possuir uma conta cadastrada nesse CPF.");
                       continue loop;
                   }
               }
               break;
           }

           while (true) {
               System.out.print("Crie uma senha forte: ");
               senha = sc.next();
               sc.nextLine();

               if (!senha.matches("^[a-zA-Z\\d]{4,}$")) {
                   System.out.println("ERROR: A senha deve conter no minimo 4 caracteres.");
                   continue;
               }
               break;
           }

           while (true) {
               System.out.print("Confirme a senha: ");
               String confirmar = sc.next();
               sc.nextLine();

               if (!confirmar.equals(senha)) {
                   System.out.println("ERROR: A senha deve estar de acordo com a criada.");
                   continue;
               }
               break;
           }

           Funcionario create = new Funcionario(cpf, senha, name);
           funcionarios.add(create);
           System.out.println("SUCCESS: Conta criada com sucesso! Agora basta acessa-la no menu principa.");
           System.out.println("---------------------");
           System.out.println("Informacoes da Conta:");
           System.out.println(create);
           System.out.println("---------------------");

           System.out.print("Pressione a tecla (ENTER) para voltar ao inicio.");
           op = sc.nextLine();
       } while (!op.isBlank());
    }

    public void logAcesso() {
        if (!logAcesso.isEmpty()) {
            do {
                System.out.println("---------------------");
                System.out.println("Historico de acessos:");
                for (int i = logAcesso.size(); i >= 1; i--) {
                    System.out.println(logAcesso.get(i - 1));
                }
                System.out.println("---------------------");

                System.out.print("Pressione a tecla (ENTER) para voltar ao inicio.");
                op = sc.nextLine();
                } while (!op.isBlank());
        } else {
            throw new NoSuchElementException("Ainda nao existe nenhum acesso a livraria.");
        }
    }
}