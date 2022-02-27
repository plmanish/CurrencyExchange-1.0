import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private Map<String, List<TickerPrice>> adj = new HashMap<>();
    private double maxExchange;
    private List<String> exchangePath;

    public void addTicker(Ticker tickerRelation) {
        String base = tickerRelation.getFromCurrency();
        String quote = tickerRelation.getToCurrency();
        double bidPrice = tickerRelation.getBidPrice();
        double askPrice = tickerRelation.getAskPrice();

        adj.putIfAbsent(base, new ArrayList<>());
        adj.putIfAbsent(quote, new ArrayList<>());

        adj.get(base).add(new TickerPrice(quote, 1.0/bidPrice));
        adj.get(quote).add(new TickerPrice(base, askPrice));
    }

    public void findMaxExchange(String baseCurrency, String quoteCurrency, double amount) {
        maxExchange = Integer.MIN_VALUE;
        exchangePath = new ArrayList<>();

        util();
    }

    private void util() {

    }
}
