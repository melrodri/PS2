package br.mack.ps2.biblioteca.dao;

import br.mack.ps2.biblioteca.model.Emprestimo;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DAOEmprestimo extends CrudRepository<Emprestimo, Long> {
    List<Emprestimo> findByIdLivro(Long idLivro);
}