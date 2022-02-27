public class Ticker {

    private String fromCurrency;
    private String toCurrency;
    private double bidPrice;
    private double askPrice;

    public Ticker(String fromCurrency, String toCurrency, double bidPrice, double askPrice) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public double getAskPrice() {
        return askPrice;
    }
}
