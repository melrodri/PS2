import model.Assento;
import model.AssentoEconomica;
import model.AssentoExecutiva;
import model.AssentoPrimeiraClasse;
import model.Passageiro;

public class Main {
    public static void main(String[] args) {
        // Criando assentos com parâmetros
        Assento a1 = new AssentoEconomica("Econômica", 500.0);
        Assento a2 = new AssentoExecutiva("Executiva", 500.0);
        Assento a3 = new AssentoPrimeiraClasse("Primeira Classe", 500.0);

        Passageiro p1 = new Passageiro("Melissa");

        // Econômica
        System.out.println("Comprando assento na classe Econômica...");
        p1.setAssento(a1);
        System.out.println("O assento do passageiro " + p1.getNome() +
                " custa R$ " + p1.getAssento().calcularPrecoFinal());

        // Upgrade Executiva
        System.out.println("\nRealizando upgrade para Executiva...");
        p1.setAssento(a2);
        System.out.println("O assento do passageiro " + p1.getNome() +
                " custa R$ " + p1.getAssento().calcularPrecoFinal());

        // Upgrade Primeira Classe
        System.out.println("\nRealizando upgrade para Primeira Classe...");
        p1.setAssento(a3);
        System.out.println("O assento do passageiro " + p1.getNome() +
                " custa R$ " + p1.getAssento().calcularPrecoFinal());
    }
}