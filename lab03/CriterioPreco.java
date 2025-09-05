class CriterioPrecoMaximo implements CriterioBusca {
    @Override
    public boolean testar(Produto p, String valor) {
        try {
            double max = Double.parseDouble(valor);
            return p.getPreco() <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

class CriterioPrecoMinimo implements CriterioBusca {
    @Override
    public boolean testar(Produto p, String valor) {
        try {
            double min = Double.parseDouble(valor);
            return p.getPreco() >= min;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
