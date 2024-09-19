public class Main {
    public static void main(String[] args) {
        // Conversão de números inteiros para romanos
        ConversorRomanos conversorRomanos = new ConversorRomanos();
        int numero = 1994;
        String romano = conversorRomanos.converterParaRomano(numero);
        System.out.println("Número romano de " + numero + " é " + romano);

        // Conversão de moedas
        ConversorMoedas conversorMoedas = new ConversorMoedas();
        double valorEmDolares = 100;
        double valorEmEuros = conversorMoedas.converter(valorEmDolares, "USD", "EUR");
        System.out.println("$" + valorEmDolares + " é equivalente a €" + valorEmEuros);
    }
}
