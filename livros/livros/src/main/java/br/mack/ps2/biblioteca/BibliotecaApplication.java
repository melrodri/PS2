package br.mack.ps2.biblioteca;

import br.mack.ps2.biblioteca.dao.DAOEmprestimo;git add  
import br.mack.ps2.biblioteca.dao.DAOLivros;
import br.mack.ps2.biblioteca.model.Emprestimo;
import br.mack.ps2.biblioteca.model.Livros; // se sua classe for Livro, troque aqui e abaixo

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {

    @Autowired private DAOEmprestimo daoEmprestimo;
    @Autowired private DAOLivros daoLivros;

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            mostrarMenu();
            opcao = lerInt(scanner, "Escolha uma opção: ");
            switch (opcao) {
                case 1 -> executarListagem(scanner);
                case 2 -> executarBusca(scanner);
                case 3 -> executarCriacao(scanner);
                case 4 -> executarAlteracao(scanner);
                case 5 -> executarExclusao(scanner);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("(1) Listar todos");
        System.out.println("(2) Buscar por dados específicos");
        System.out.println("(3) Criar um novo");
        System.out.println("(4) Alterar os dados");
        System.out.println("(5) Apagar por ID");
        System.out.println("(0) Sair");
    }

    private void executarListagem(java.util.Scanner sc) {
        int tipo = lerInt(sc, "Listar (1) Livros ou (2) Empréstimos? ");
        if (tipo == 1) listarLivros();
        else if (tipo == 2) listarEmprestimos();
        else System.out.println("Opção inválida!");
    }

    private void executarBusca(java.util.Scanner sc) {
        int tipo = lerInt(sc, "Buscar (1) Livro por título ou (2) Empréstimo por ID do livro? ");
        if (tipo == 1) {
            String titulo = lerLinha(sc, "Título: ");
            buscarLivrosPorTitulo(titulo);
        } else if (tipo == 2) {
            Long idLivro = lerLong(sc, "ID do livro: ");
            buscarEmprestimosPorIdLivro(idLivro);
        } else System.out.println("Opção inválida!");
    }

    private void executarCriacao(java.util.Scanner sc) {
        int tipo = lerInt(sc, "Criar (1) Livro ou (2) Empréstimo? ");
        if (tipo == 1) criarLivro(sc);
        else if (tipo == 2) criarEmprestimo(sc);
        else System.out.println("Opção inválida!");
    }

    private void executarAlteracao(java.util.Scanner sc) {
        int tipo = lerInt(sc, "Alterar (1) Livro ou (2) Empréstimo? ");
        if (tipo == 1) alterarLivro(sc);
        else if (tipo == 2) alterarEmprestimo(sc);
        else System.out.println("Opção inválida!");
    }

    private void executarExclusao(java.util.Scanner sc) {
        int tipo = lerInt(sc, "Apagar (1) Livro ou (2) Empréstimo? ");
        if (tipo == 1) apagarLivro(sc);
        else if (tipo == 2) apagarEmprestimo(sc);
        else System.out.println("Opção inválida!");
    }

    /* --------- LIVROS --------- */
    private void listarLivros() {
        for (Livros l : daoLivros.findAll()) {
            System.out.println(l.getId() + " | " + l.getTitulo() + " | " + l.getAutor()
                    + " | " + l.getGenero() + " | " + l.getAno());
        }
    }
    private void buscarLivrosPorTitulo(String titulo) {
        var lista = daoLivros.findByTituloContainingIgnoreCase(titulo);
        if (lista.isEmpty()) System.out.println("Nenhum livro encontrado.");
        for (Livros l : lista) {
            System.out.println(l.getId() + " | " + l.getTitulo() + " | " + l.getAutor()
                    + " | " + l.getGenero() + " | " + l.getAno());
        }
    }
    private void criarLivro(java.util.Scanner sc) {
        String titulo = lerLinha(sc, "Título: ");
        String autor  = lerLinha(sc, "Autor: ");
        String genero = lerLinha(sc, "Gênero: ");
        Integer ano   = lerInt(sc, "Ano: ");
        Livros novo = new Livros(null, titulo, autor, genero, ano);
        daoLivros.save(novo);
        System.out.println("Livro inserido!");
    }
    private void alterarLivro(java.util.Scanner sc) {
        Long id = lerLong(sc, "ID do livro: ");
        var livro = daoLivros.findById(id).orElse(null);
        if (livro == null) { System.out.println("Livro não encontrado!"); return; }
        livro.setTitulo(lerLinha(sc, "Novo título: "));
        livro.setAutor( lerLinha(sc, "Novo autor: "));
        livro.setGenero(lerLinha(sc, "Novo gênero: "));
        livro.setAno(   lerInt(sc, "Novo ano: "));
        daoLivros.save(livro);
        System.out.println("Livro atualizado!");
    }
    private void apagarLivro(java.util.Scanner sc) {
        Long id = lerLong(sc, "ID do livro para remover: ");
        daoLivros.deleteById(id);
        System.out.println("Livro removido!");
    }

    /* --------- EMPRÉSTIMOS --------- */
    private void listarEmprestimos() {
        for (Emprestimo e : daoEmprestimo.findAll()) {
            System.out.println(e.getId() + " | Livro: " + e.getIdLivro() + " | Data: " + e.getDataRetirada());
        }
    }
    private void buscarEmprestimosPorIdLivro(Long idLivro) {
        var lista = daoEmprestimo.findByIdLivro(idLivro);
        if (lista.isEmpty()) System.out.println("Nenhum empréstimo para esse livro.");
        for (Emprestimo e : lista) {
            System.out.println(e.getId() + " | Livro: " + e.getIdLivro() + " | Data: " + e.getDataRetirada());
        }
    }
    private void criarEmprestimo(java.util.Scanner sc) {
        Long idLivro = lerLong(sc, "ID do livro para empréstimo: ");
        if (!daoLivros.existsById(idLivro)) { System.out.println("Livro não encontrado!"); return; }
        Emprestimo emp = new Emprestimo();
        emp.setIdLivro(idLivro); // use Long, não int
        emp.setDataRetirada(new java.util.Date());
        daoEmprestimo.save(emp);
        System.out.println("Empréstimo registrado!");
    }
    private void alterarEmprestimo(java.util.Scanner sc) {
        Long id = lerLong(sc, "ID do empréstimo: ");
        var emp = daoEmprestimo.findById(id).orElse(null);
        if (emp == null) { System.out.println("Empréstimo não encontrado!"); return; }
        Long novoIdLivro = lerLong(sc, "Novo ID do livro: ");
        emp.setIdLivro(novoIdLivro);
        emp.setDataRetirada(new java.util.Date());
        daoEmprestimo.save(emp);
        System.out.println("Empréstimo atualizado!");
    }
    private void apagarEmprestimo(java.util.Scanner sc) {
        Long id = lerLong(sc, "ID do empréstimo para remover: ");
        daoEmprestimo.deleteById(id);
        System.out.println("Empréstimo removido!");
    }

    /* --------- HELPERS --------- */
    private int lerInt(java.util.Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) { System.out.print("Valor inválido. " + prompt); sc.next(); }
        int v = sc.nextInt(); sc.nextLine(); return v;
    }
    private Long lerLong(java.util.Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextLong()) { System.out.print("Valor inválido. " + prompt); sc.next(); }
        Long v = sc.nextLong(); sc.nextLine(); return v;
    }
    private String lerLinha(java.util.Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}
