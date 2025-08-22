package model;

public class AssentoPrimeiraClasse extends Assento {
    private double acrescimo = 0.5;

    public AssentoPrimeiraClasse(String classe, double precoBase) {
        super(classe, precoBase);
    }

    @Override
    public double calcularPrecoFinal() {
        return this.getPrecoBase() + (this.getPrecoBase() * acrescimo);
    }
}

