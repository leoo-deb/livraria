package livraria;

import java.util.UUID;

public class Funcionario {
    private String nome;
    private final String cpf;
    private String password;
    private final UUID id;

    public Funcionario(String cpf, String password, String nome) {
        this.id = UUID.randomUUID();
        this.cpf = cpf;
        this.password = password;
        this.nome = nome;
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
        return id;
    }

    @Override
    public String toString() {
        return String.format("""
                Nome: %s
                CPF: %s
                UUID: %s""", nome, cpf, id.toString());
    }
}
