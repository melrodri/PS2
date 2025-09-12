import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:6543/postgres?user=postgres.jbeaeccfawnmeuitpdux&password=wvxEFumthE1sNHa4";
            Connection conn = DriverManager.getConnection(url);

            // Apenas menu CRUD, sem inserção automática

            java.util.Scanner scanner = new java.util.Scanner(System.in);
            int opcao = -1;
            while (opcao != 0) {
                System.out.println("\n--- MENU ---");
                System.out.println("1 - Inserir Livro");
                System.out.println("2 - Atualizar Livro");
                System.out.println("3 - Listar Livros");
                System.out.println("4 - Remover Livro");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer

                switch (opcao) {
                    case 1:
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Autor: ");
                        String autor = scanner.nextLine();
                        int novoId = inserirLivroRetornaId(conn, titulo, autor);
                        System.out.println("Livro inserido com ID: " + novoId);
                        break;
                    case 2:
                        System.out.print("ID do livro: ");
                        int idLivro = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Novo título: ");
                        String novoTitulo = scanner.nextLine();
                        System.out.print("Novo autor: ");
                        String novoAutor = scanner.nextLine();
                        atualizarLivro(conn, idLivro, novoTitulo, novoAutor);
                        break;
                    case 3:
                        listarLivros(conn);
                        break;
                    case 4:
                        System.out.print("ID do livro para remover: ");
                        int idRemoverLivro = scanner.nextInt();
                        scanner.nextLine();
                        removerLivro(conn, idRemoverLivro);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
            conn.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para inserir livro e retornar o ID gerado
    public static int inserirLivroRetornaId(Connection conn, String titulo, String autor) throws Exception {
        String sql = "INSERT INTO LIVROS (TITULO, AUTOR) VALUES (?, ?) RETURNING ID";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1, titulo);
        psmt.setString(2, autor);
        ResultSet rs = psmt.executeQuery();
        int id = -1;
        if (rs.next()) {
            id = rs.getInt("ID");
        }
        rs.close();
        psmt.close();
        return id;
    }

    // Métodos CRUD para LIVROS
    public static void inserirLivro(Connection conn, String titulo, String autor) throws Exception {
        String sql = "INSERT INTO LIVROS (TITULO, AUTOR) VALUES (?, ?)";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1, titulo);
        psmt.setString(2, autor);
        int qte = psmt.executeUpdate();
        if (qte >= 1) System.out.println("Livro inserido com sucesso");
        psmt.close();
    }

    public static void atualizarLivro(Connection conn, int id, String novoTitulo, String novoAutor) throws Exception {
        String sql = "UPDATE LIVROS SET TITULO = ?, AUTOR = ? WHERE ID = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1, novoTitulo);
        psmt.setString(2, novoAutor);
        psmt.setInt(3, id);
        int qte = psmt.executeUpdate();
        if (qte >= 1) System.out.println("Livro atualizado com sucesso");
        psmt.close();
    }

    public static void listarLivros(Connection conn) throws Exception {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM LIVROS");
        System.out.println("--- LIVROS ---");
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + " | " + rs.getString("TITULO") + " | " + rs.getString("AUTOR"));
        }
        rs.close();
        st.close();
    }

    public static void removerLivro(Connection conn, int id) throws Exception {
        String sql = "DELETE FROM LIVROS WHERE ID = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1, id);
        int qte = psmt.executeUpdate();
        if (qte >= 1) System.out.println("Livro removido com sucesso");
        psmt.close();
    }

    // Métodos CRUD para emprestimo
    public static void inserirEmprestimo(Connection conn, int idLivro, java.sql.Date dataRetirada) throws Exception {
        String sql = "INSERT INTO emprestimo (id_livro, data_retirada) VALUES (?, ?)";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1, idLivro);
        psmt.setDate(2, dataRetirada);
        int qte = psmt.executeUpdate();
        if (qte >= 1) System.out.println("Empréstimo inserido com sucesso");
        psmt.close();
    }

    public static void atualizarEmprestimo(Connection conn, int id, int novoIdLivro, java.sql.Date novaDataRetirada) throws Exception {
        String sql = "UPDATE emprestimo SET id_livro = ?, data_retirada = ? WHERE id = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1, novoIdLivro);
        psmt.setDate(2, novaDataRetirada);
        psmt.setInt(3, id);
        int qte = psmt.executeUpdate();
        if (qte >= 1) System.out.println("Empréstimo atualizado com sucesso");
        psmt.close();
    }

    public static void listarEmprestimos(Connection conn) throws Exception {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM emprestimo");
        System.out.println("--- EMPRÉSTIMOS ---");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " | " + rs.getInt("id_livro") + " | " + rs.getDate("data_retirada"));
        }
        rs.close();
        st.close();
    }

    public static void removerEmprestimo(Connection conn, int id) throws Exception {
        String sql = "DELETE FROM emprestimo WHERE id = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1, id);
        int qte = psmt.executeUpdate();
        if (qte >= 1) System.out.println("Empréstimo removido com sucesso");
        psmt.close();
    }
}
