package model;

public class AssentoEconomica extends Assento {

    public AssentoEconomica(String classe, double precoBase) {
        super(classe, precoBase);
    }

    @Override
    public double calcularPrecoFinal() {
        return this.getPrecoBase();
    }
}