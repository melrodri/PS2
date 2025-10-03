package br.mack.ps2.biblioteca.dao;
import org.springframework.data.repository.CrudRepository;
import br.mack.ps2.biblioteca.model.Livros;
import java.util.List;

public interface DAOLivros extends CrudRepository<Livros, Long> {
    List<Livros> findByTitulo(String titulo);
}