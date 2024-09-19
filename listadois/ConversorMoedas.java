import java.util.HashMap;
import java.util.Map;

public class ConversorMoedas {
    private final Map<String, Double> taxas;

    public ConversorMoedas() {
        taxas = new HashMap<>();
        // Valores fictícios para simplificação; substitua com taxas reais.
        taxas.put("USD", 1.0);
        taxas.put("EUR", 0.85);
        taxas.put("JPY", 110.0);
        taxas.put("GBP", 0.75);
        taxas.put("BRL", 5.0);
    }

    public double converter(double valor, String moedaDe, String moedaPara) {
        if (!taxas.containsKey(moedaDe) || !taxas.containsKey(moedaPara)) {
            throw new IllegalArgumentException("Moeda inválida");
        }
        double taxaDe = taxas.get(moedaDe);
        double taxaPara = taxas.get(moedaPara);
        return valor * (taxaPara / taxaDe);
    }
}
