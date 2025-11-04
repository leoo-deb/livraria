package livraria;

public class Livro {
    private final String titulo;
    private final String sinopse;
    private final String autor;

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
