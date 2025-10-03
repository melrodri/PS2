package br.mack.ps2.biblioteca.dao;
import org.springframework.data.repository.CrudRepository;
import br.mack.ps2.biblioteca.model.livros;
import java.util.List;

public interface DAOLivros extends CrudRepository<livros, Long>{
        List<livros> findByTitulo(String titulo);
        List<livros> findByAutor(String autor);
        List<livros> findByGenero(String genero);
        List<livros> findByAno(int ano);
public interface DAOEmprestimo extends CrudRepository<Emprestimo, Long> {
	List<Emprestimo> findByNome(String nome);
}


}