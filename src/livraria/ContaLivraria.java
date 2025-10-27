package livraria;

import java.text.CollationElementIterator;
import java.util.*;

public class ContaLivraria {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private ArrayList<Funcionario> logAcesso = new ArrayList<>();
    private String op;

    public ContaLivraria() {
        funcionarios.add(new Funcionario("1", "1234", "admin"));
    }

    //ACESSA A CONTA
    public boolean access() {
        System.out.println("---------------------");
        System.out.print("CPF ou Serial: ");
        String cpf = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.println("---------------------");

        for (Funcionario acesso: funcionarios) {
            if ((cpf.equals(acesso.getCpf()) && senha.equals(acesso.getPassword()))
                    || (cpf.equals(acesso.getId().toString()) && senha.equals(acesso.getPassword()))) {
                System.out.printf("SUCCESS: Login efetuado com sucesso! Bem-vindo %s.\n", acesso.getNome());
                logAcesso.add(acesso);
                return false;
            }
        }
        System.out.println("CPF/Senha incorreto. Verifique-os novamente.");
        return true;
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
            Collections.sort(logAcesso);
            do {
                System.out.println("---------------------");
                System.out.println("Historico de acessos:");
                for (Funcionario log : logAcesso) {
                    System.out.printf("Nome: %s\t | \tSerial: %s\n", log.getNome(), log.getId().toString());
                }
                System.out.println("---------------------");

                    System.out.print("Pressione a tecla (ENTER) para voltar ao inicio.");
                    op = sc.nextLine();
                } while (!op.isBlank());
        } else {
            throw new NoSuchElementException("Ainda nao existe nenhum acesso a livravria.");
        }
    }
}