public class Main {

    public static void main(String[] args) {

        TemperatureConverter converter = new TemperatureConverter();

        System.out.println("F 32 -> C = " + converter.fahrenheitToCelsius(32));
        System.out.println("C 0  -> F = " + converter.celsiusToFahrenheit(0));
        System.out.println("K 273.15 -> C = " + converter.kelvinToCelsius(273.15));
        System.out.println("Is 60°C extreme? " + converter.isExtremeTemperature(60));
    }
}