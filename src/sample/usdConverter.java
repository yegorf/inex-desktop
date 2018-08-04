package sample;

public class usdConverter {

    private double value = 0.0;

    public void convertUah(double sum, double usdRate) {
        value += sum/usdRate;
        System.out.println(value);
    }

    public void convertEur(double sum, double usdRate, double eurRate) {
        value += (sum*eurRate)/usdRate;
        System.out.println(value);
    }

    public void convertRub(double sum, double rubRate, double usdRate) {
        value += (sum*rubRate)/usdRate;
        System.out.println(value);
    }

    public double getValue() {
        return value;
    }

}
