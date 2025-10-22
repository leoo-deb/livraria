package livraria;

import java.util.UUID;

public class Funcionario implements Comparable<Funcionario> {
    private String nome;
    private final String cpf;
    private String password;
    private final UUID serial;
    private final Integer ID_USER;
    private static int numberID = 1;

    public Funcionario(String cpf, String password, String nome) {
        this.serial = UUID.randomUUID();
        this.cpf = cpf;
        this.password = password;
        this.nome = nome;
        this.ID_USER = numberID++;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassword() {
        return password;
    }

    public UUID getId() {
        return serial;
    }

    @Override
    public String toString() {
        return String.format("""
                Nome: %s
                CPF: %s
                Serial: %s""", nome, cpf, serial.toString());
    }

    @Override
    public int compareTo(Funcionario o) {
        return -this.ID_USER.compareTo(o.ID_USER);
    }
}
