package livraria;

public class Livro {
    private String titulo;
    private String sinopse;
    private String autor;

    public Livro(String titulo, String sinopse, String autor) {
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return String.format("""
                Titulo: %s
                Sinopse: %s
                Autor(a): %s""", titulo, sinopse, autor);
    }
}
