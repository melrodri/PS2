package mac.dao;

public interface DAO<T> {
    void inserir(T obj);
    T buscar(int id);
    void atualizar(T obj);
    void remover(int id);
}
