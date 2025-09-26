package br.mack.ps2.biblioteca.dao;
import org.springframework.data.repository.CrudRepository;
import br.mack.ps2.biblioteca.model.Emprestimo;

public interface DAOEmprestimo extends CrudRepository<Emprestimo, Long>{

}
