package mac.model;

public class Emprestimo {
    private int id;
    private int idLivro;
    private String dataRetirada;

    public Emprestimo(int id, int idLivro, String dataRetirada) {
        this.id = id;
        this.idLivro = idLivro;
        this.dataRetirada = dataRetirada;
    }

    public int getId() { return id; }
    public int getIdLivro() { return idLivro; }
    public String getDataRetirada() { return dataRetirada; }

    public void setId(int id) { this.id = id; }
    public void setIdLivro(int idLivro) { this.idLivro = idLivro; }
    public void setDataRetirada(String dataRetirada) { this.dataRetirada = dataRetirada; }
}
