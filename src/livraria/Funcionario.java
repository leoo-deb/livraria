package livraria;

import java.util.UUID;

public class Funcionario implements Comparable<Funcionario> {
    private String nome;
    private final String CPF;
    private String password;

    private final UUID SERIAL_USER;
    private final Integer ID_USER;
    private static int numberID = 1;

    public Funcionario(String cpf, String password, String nome) {
        this.SERIAL_USER = UUID.randomUUID();
        this.CPF = cpf;
        this.password = password;
        this.nome = nome;
        this.ID_USER = numberID++;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return CPF;
    }

    public String getPassword() {
        return password;
    }

    public UUID getId() {
        return SERIAL_USER;
    }

    @Override
    public String toString() {
        return String.format("""
                Nome: %s
                CPF: %s
                Serial: %s""", nome, CPF, SERIAL_USER.toString());
    }

    @Override
    public int compareTo(Funcionario o) {
        return -this.ID_USER.compareTo(o.ID_USER);
    }
}
