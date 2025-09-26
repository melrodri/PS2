package br.mack.ps2.biblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.mack.ps2.biblioteca.dao.DAOEmprestimo;
import br.mack.ps2.biblioteca.model.Emprestimo;


@SpringBootApplication
public class BibliotecaApplication {
	@Autowired
	private DAOEmprestimo daoEmprestimo;


	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}
	public void run(String... args) throws Exception{
		Emprestimo e1 = new Emprestimo();
		e1.setIdLivro(1);
		e1.setDataRetirada(new java.util.Date());
		// this.DAOEmprestimo.save(e1);
	}
}
