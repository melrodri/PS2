package mac.dao;

import mac.model.Emprestimo;
import java.util.*;

public class DAOEmprestimos implements DAO<Emprestimo> {
    private List<Emprestimo> emprestimos = new ArrayList<>();

    @Override
    public void inserir(Emprestimo emp) {
        emprestimos.add(emp);
    }

    @Override
    public Emprestimo buscar(int id) {
        for (Emprestimo e : emprestimos) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    @Override
    public void atualizar(Emprestimo emp) {
        for (int i = 0; i < emprestimos.size(); i++) {
            if (emprestimos.get(i).getId() == emp.getId()) {
                emprestimos.set(i, emp);
                return;
            }
        }
    }

    @Override
    public void remover(int id) {
        emprestimos.removeIf(e -> e.getId() == id);
    }
}
