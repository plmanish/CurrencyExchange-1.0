public class TickerPrice {
    private String ticker;
    private double price;

    public TickerPrice(String ticker, double price) {
        this.ticker = ticker;
        this.price = price;
    }

    public String getTicker() {
        return ticker;
    }

    public double getPrice() {
        return price;
    }
}
