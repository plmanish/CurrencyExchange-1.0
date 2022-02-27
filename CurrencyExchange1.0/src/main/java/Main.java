import com.google.gson.Gson;

import java.util.*;

public class Main {
    static class Ticker {
        String baseCurrency;
        String quoteCurrency;
        double bidValue;
        double askValue;

        Ticker(String base, String quote, double bid, double ask) {
            baseCurrency = base;
            quoteCurrency = quote;
            bidValue = bid;
            askValue = ask;
        }
    }

    static class TickerPrice {
        String ticker;
        double price;

        TickerPrice(String name, double price) {
            ticker = name;
            this.price = price;
        }
    }

    static class Graph {
        Map<String, List<TickerPrice>> adj = new HashMap<>();

        double maxExchangePrice;
        List<String> exchangePath;
        Set<String> visited;

        public void addTicker(Ticker ticker) {
            String base = ticker.baseCurrency;
            String quote = ticker.quoteCurrency;
            double bidValue = ticker.bidValue;
            double askValue = ticker.askValue;

            adj.putIfAbsent(base, new ArrayList<>());
            adj.putIfAbsent(quote, new ArrayList<>());
            adj.get(base).add(new TickerPrice(quote, 1.0/bidValue));
            adj.get(quote).add(new TickerPrice(base, askValue));
        }

        public void printGraph() {
            System.out.println("Generated adjacency list");
            for (Map.Entry<String, List<TickerPrice>> ticker : adj.entrySet()) {
                System.out.print(ticker.getKey() + ": ");
                for (TickerPrice tickerPrice : ticker.getValue())
                    System.out.print("(" + tickerPrice.ticker + ", " + tickerPrice.price + "), ");
                System.out.println();
            }
            System.out.println();
        }

        public void getMaxExchange(String base, String quote, double amount) {
            maxExchangePrice = Integer.MIN_VALUE;
            exchangePath = new ArrayList<>();
            visited = new HashSet<>();

            List<String> currPath = new ArrayList<>();
            currPath.add(base);
            util(base, quote, amount, currPath);

            System.out.println("Max exchange price: " + maxExchangePrice);
            System.out.println("Exchange path: " + exchangePath);
        }

        private void util(String base, String target, double amount, List<String> path) {
            if (base.equals(target)) {
                if (maxExchangePrice < amount) {
                    maxExchangePrice = amount;
                    exchangePath = new ArrayList<>(path);
                }
                return;
            }

            if (!adj.containsKey(base)) {
                return;
            }

            visited.add(base);

            for (TickerPrice next : adj.get(base)) {
                String ticker = next.ticker;
                double wt = next.price;
                if (!visited.contains(ticker)) {
                    path.add(ticker);
                    util(ticker, target, wt * amount, path);
                    path.remove(path.size() - 1);
                }
            }
            visited.remove(visited.size() - 1);
        }
    }

    public static void main(String[] args) {
        Ticker t1 = new Ticker("USD", "BTC", 1000, 990);
        Ticker t2 = new Ticker("EUR", "BTC", 1200, 1150);
        Ticker t3 = new Ticker("USD", "ETH", 200, 180);
        Ticker t4 = new Ticker("EUR", "ETH", 220, 210);
        Ticker t5 = new Ticker("ETH", "BTC", 5.6, 5.5);

        Gson gson = new Gson();
        String json1 = "{\"baseCurrency\": \"USD\", \"quoteCurrency\": \"BTC\", \"bidValue\": \"1000\", \"askValue\": \"990\"}";
        String json2 = "{\"baseCurrency\": \"EUR\", \"quoteCurrency\": \"BTC\", \"bidValue\": \"1200\", \"askValue\": \"1150\"}";
        String json3 = "{\"baseCurrency\": \"USD\", \"quoteCurrency\": \"ETH\", \"bidValue\": \"200\", \"askValue\": \"180\"}";
        String json4 = "{\"baseCurrency\": \"EUR\", \"quoteCurrency\": \"ETH\", \"bidValue\": \"220\", \"askValue\": \"210\"}";
        String json5 = "{\"baseCurrency\": \"ETH\", \"quoteCurrency\": \"BTC\", \"bidValue\": \"5.6\", \"askValue\": \"5.5\"}";

        Ticker t11 = gson.fromJson(json1, Ticker.class);
        Ticker t21 = gson.fromJson(json2, Ticker.class);
        Ticker t31 = gson.fromJson(json3, Ticker.class);
        Ticker t41 = gson.fromJson(json4, Ticker.class);
        Ticker t51 = gson.fromJson(json5, Ticker.class);

        Graph graph = new Graph();
//        graph.addTicker(t1);
//        graph.addTicker(t2);
//        graph.addTicker(t3);
//        graph.addTicker(t4);
//        graph.addTicker(t5);

        graph.addTicker(t11);
        graph.addTicker(t21);
        graph.addTicker(t31);
        graph.addTicker(t41);
        graph.addTicker(t51);

        graph.printGraph();

        graph.getMaxExchange("USD", "EUR", 100);
        graph.getMaxExchange("EUR", "USD", 100);
    }
}
