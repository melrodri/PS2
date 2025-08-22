package model;

public class AssentoExecutiva extends Assento {
    private double acrescimo = 0.3;

    public AssentoExecutiva(String classe, double precoBase) {
        super(classe, precoBase);
    }

    @Override
    public double calcularPrecoFinal() {
        return this.getPrecoBase() + (this.getPrecoBase() * acrescimo);
    }
}