package livraria;

public class ViewsLivraria {

    public static void menu() {
        System.out.println("""
                ---------------------
                [1] Acessar livraria
                [2] Criar conta
                [3] Logs de acesso
                [4] Sair
                ---------------------""");
        System.out.print("Escolha uma opcao: ");
    }

    public static void menuLivraria() {
        System.out.println("""
                ---------------------
                [1] Adicionar livro
                [2] Gerenciar livro
                [3] Buscar livro
                [4] Listar livros
                [5] Sair
                ---------------------""");
        System.out.print("Escolha uma opcao: ");
    }

    public static void managerLivros() {
        System.out.println("""
                        ---------------------
                        [1] Editar livro
                        [2] Remover livro
                        [3] Sair
                        ---------------------""");
        System.out.print("Escolha uma opcao: ");
    }
}
