package mac.dao;

import mac.model.Livro;
import java.util.*;

public class DAOLivros implements DAO<Livro> {
    private List<Livro> livros = new ArrayList<>();

    @Override
    public void inserir(Livro livro) {
        livros.add(livro);
    }

    @Override
    public Livro buscar(int id) {
        for (Livro l : livros) {
            if (l.getId() == id) return l;
        }
        return null;
    }

    @Override
    public void atualizar(Livro livro) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getId() == livro.getId()) {
                livros.set(i, livro);
                return;
            }
        }
    }

    @Override
    public void remover(int id) {
        livros.removeIf(l -> l.getId() == id);
    }
}
