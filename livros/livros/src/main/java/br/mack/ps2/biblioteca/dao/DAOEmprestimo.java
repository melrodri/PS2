package br.mack.ps2.biblioteca.dao;

import br.mack.ps2.biblioteca.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DAOEmprestimo extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByIdLivro(Long idLivro);
}
