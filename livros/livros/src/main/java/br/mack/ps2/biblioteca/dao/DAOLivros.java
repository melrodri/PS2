package br.mack.ps2.biblioteca.dao;

import br.mack.ps2.biblioteca.model.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DAOLivros extends JpaRepository<Livros, Long> {
    List<Livros> findByTituloContainingIgnoreCase(String titulo);
}
