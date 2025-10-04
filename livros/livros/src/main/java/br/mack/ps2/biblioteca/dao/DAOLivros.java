package br.mack.ps2.biblioteca.dao;

import br.mack.ps2.biblioteca.model.Livros;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DAOLivros extends CrudRepository<Livros, Long> {
    List<Livros> findByTituloContainingIgnoreCase(String titulo);
}