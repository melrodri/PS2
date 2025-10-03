package br.mack.ps2.biblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.mack.ps2.biblioteca.dao.DAOEmprestimo;
import br.mack.ps2.biblioteca.dao.DAOLivros;
import br.mack.ps2.biblioteca.model.Emprestimo;
import br.mack.ps2.biblioteca.model.Livros;


@SpringBootApplication
public class BibliotecaApplication implements org.springframework.boot.CommandLineRunner {
	@Autowired
	private DAOEmprestimo daoEmprestimo;
	@Autowired
	private DAOLivros daoLivros;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		int opcao = -1;
		while (opcao != 0) {
			System.out.println("\n--- MENU ---");
			System.out.println("(1) Listar todos");
			System.out.println("(2) Buscar por dados específicos");
			System.out.println("(3) Criar um novo");
			System.out.println("(4) Alterar os dados");
			System.out.println("(5) Apagar por ID");
			System.out.println("(0) Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine();
			switch (opcao) {
				case 1: // Listar todos
					System.out.print("Listar (1) Livros ou (2) Empréstimos? ");
					int tipoListar = scanner.nextInt();
					scanner.nextLine();
					if (tipoListar == 1) {
						for (Livros l : daoLivros.findAll()) {
							System.out.println(l.getId() + " | " + l.getTitulo() + " | " + l.getAutor() + " | " + l.getGenero() + " | " + l.getAno());
						}
					} else if (tipoListar == 2) {
						for (Emprestimo e : daoEmprestimo.findAll()) {
							System.out.println(e.getId() + " | Livro: " + e.getIdLivro() + " | Data: " + e.getDataRetirada());
						}
					} else {
						System.out.println("Opção inválida!");
					}
					break;
				case 2: // Buscar por dados específicos
					System.out.print("Buscar (1) Livro por título ou (2) Empréstimo por ID do livro? ");
					int tipoBusca = scanner.nextInt();
					scanner.nextLine();
					if (tipoBusca == 1) {
						System.out.print("Título: ");
						String tituloBusca = scanner.nextLine();
						for (Livros l : daoLivros.findByTitulo(tituloBusca)) {
							System.out.println(l.getId() + " | " + l.getTitulo() + " | " + l.getAutor() + " | " + l.getGenero() + " | " + l.getAno());
						}
					} else if (tipoBusca == 2) {
						System.out.print("ID do livro: ");
						int idLivroBusca = scanner.nextInt();
						scanner.nextLine();
						for (Emprestimo e : daoEmprestimo.findAll()) {
							if (e.getIdLivro() == idLivroBusca) {
								System.out.println(e.getId() + " | Livro: " + e.getIdLivro() + " | Data: " + e.getDataRetirada());
							}
						}
					} else {
						System.out.println("Opção inválida!");
					}
					break;
				case 3: // Criar novo
					System.out.print("Criar (1) Livro ou (2) Empréstimo? ");
					int tipoNovo = scanner.nextInt();
					scanner.nextLine();
					if (tipoNovo == 1) {
						System.out.print("Título: ");
						String titulo = scanner.nextLine();
						System.out.print("Autor: ");
						String autor = scanner.nextLine();
						System.out.print("Gênero: ");
						String genero = scanner.nextLine();
						System.out.print("Ano: ");
						int ano = scanner.nextInt();
						scanner.nextLine();
						Livros novoLivro = new Livros(null, titulo, autor, genero, ano);
						daoLivros.save(novoLivro);
						System.out.println("Livro inserido com sucesso!");
					} else if (tipoNovo == 2) {
						System.out.print("ID do livro para empréstimo: ");
						Long idLivroEmp = scanner.nextLong();
						scanner.nextLine();
						if (daoLivros.existsById(idLivroEmp)) {
							Emprestimo emp = new Emprestimo();
							emp.setIdLivro(idLivroEmp.intValue());
							emp.setDataRetirada(new java.util.Date());
							daoEmprestimo.save(emp);
							System.out.println("Empréstimo registrado!");
						} else {
							System.out.println("Livro não encontrado!");
						}
					} else {
						System.out.println("Opção inválida!");
					}
					break;
				case 4: // Alterar dados
					System.out.print("Alterar (1) Livro ou (2) Empréstimo? ");
					int tipoAlt = scanner.nextInt();
					scanner.nextLine();
					if (tipoAlt == 1) {
						System.out.print("ID do livro: ");
						Long idLivro = scanner.nextLong();
						scanner.nextLine();
						Livros livroAtualizar = daoLivros.findById(idLivro).orElse(null);
						if (livroAtualizar != null) {
							System.out.print("Novo título: ");
							livroAtualizar.setTitulo(scanner.nextLine());
							System.out.print("Novo autor: ");
							livroAtualizar.setAutor(scanner.nextLine());
							System.out.print("Novo gênero: ");
							livroAtualizar.setGenero(scanner.nextLine());
							System.out.print("Novo ano: ");
							livroAtualizar.setAno(scanner.nextInt());
							scanner.nextLine();
							daoLivros.save(livroAtualizar);
							System.out.println("Livro atualizado com sucesso!");
						} else {
							System.out.println("Livro não encontrado!");
						}
					} else if (tipoAlt == 2) {
						System.out.print("ID do empréstimo: ");
						Long idEmp = scanner.nextLong();
						scanner.nextLine();
						Emprestimo empAtualizar = daoEmprestimo.findById(idEmp).orElse(null);
						if (empAtualizar != null) {
							System.out.print("Novo ID do livro: ");
							empAtualizar.setIdLivro(scanner.nextInt());
							scanner.nextLine();
							empAtualizar.setDataRetirada(new java.util.Date());
							daoEmprestimo.save(empAtualizar);
							System.out.println("Empréstimo atualizado!");
						} else {
							System.out.println("Empréstimo não encontrado!");
						}
					} else {
						System.out.println("Opção inválida!");
					}
					break;
				case 5: // Apagar por ID
					System.out.print("Apagar (1) Livro ou (2) Empréstimo? ");
					int tipoDel = scanner.nextInt();
					scanner.nextLine();
					if (tipoDel == 1) {
						System.out.print("ID do livro para remover: ");
						Long idRemoverLivro = scanner.nextLong();
						scanner.nextLine();
						daoLivros.deleteById(idRemoverLivro);
						System.out.println("Livro removido com sucesso!");
					} else if (tipoDel == 2) {
						System.out.print("ID do empréstimo para remover: ");
						Long idRemoverEmp = scanner.nextLong();
						scanner.nextLine();
						daoEmprestimo.deleteById(idRemoverEmp);
						System.out.println("Empréstimo removido com sucesso!");
					} else {
						System.out.println("Opção inválida!");
					}
					break;
				case 0:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida!");
			}
		}
		scanner.close();
	}
}
