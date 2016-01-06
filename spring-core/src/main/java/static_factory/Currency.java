package static_factory;

/**
 * Created by varun on 20/09/15.
 */
public class Currency {

    private String symbol;
    private String country;

    public Currency(String symbol, String country) {
        this.symbol = symbol;
        this.country = country;
    }

    public void sayHello() {
        System.out.println("Currency Symbol: " + symbol + " Country: "+ country);
    }
}
