import java.util.ArrayList;
import java.util.List;

public class StockMarketSimulator {
	public static void main(String[] args) {
		Stock apple = new Stock("AAPL", 150.0f);
		Stock google = new Stock("GOOGL", 2800.0f);

		Investor john = new Investor("John");
		Investor jane = new Investor("Jane");

		john.addStock(apple);
		jane.addStock(apple);
		jane.addStock(google);

		apple.updatePrice(155.0f);
		google.updatePrice(2850.0f);
		apple.updatePrice(160.0f);
	}

	// Class Stock
	static class Stock {
		private String symbol;
		private float price;
		private final List<Investor> investors = new ArrayList<>();

		public Stock(String symbol, float price) {
			this.symbol = symbol;
			this.price = price;
		}

		public String getSymbol() {
			return symbol;
		}

		public float getPrice() {
			return price;
		}

		public void registerInvestor(Investor investor) {
			investors.add(investor);
		}

		public void unregisterInvestor(Investor investor) {
			investors.remove(investor);
		}

		public void updatePrice(float newPrice) {
			price = newPrice;
			for (Investor investor : investors) {
				investor.update(this, newPrice);
			}
		}
	}

	// Class Investor
	static class Investor {
		private String name;
		private final List<Stock> stocks = new ArrayList<>();

		public Investor(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void addStock(Stock stock) {
			stocks.add(stock);
			stock.registerInvestor(this);
		}

		public void removeStock(Stock stock) {
			stocks.remove(stock);
			stock.unregisterInvestor(this);
		}

		public void update(Stock stock, float price) {
			System.out.printf("Investor %s notified. New price of %s is %.2f%n", name, stock.getSymbol(), price);
		}
	}
}
