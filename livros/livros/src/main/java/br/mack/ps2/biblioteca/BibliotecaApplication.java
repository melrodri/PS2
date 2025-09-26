package br.mack.ps2.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.mack.ps2.biblioteca.model.Emprestimos;


@SpringBootApplication
public class bibliotecaApplication {
	@Autowired
	private DAOEmprestimo daoEmprestimo;


	public static void main(String[] args) {
		SpringApplication.run(bibliotecaApplication.class, args);
	}
	public void run(String... args) throws Exception{
		Emprestimos e1 = new Emprestimos();
		e1.setIdLivro(1);
		e1.setDataRetirada(new java.util.Date());
		// this.DAOEmprestimo.save(e1);
	}
}
