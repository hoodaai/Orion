package static_factory;

/**
 * Created by varun on 20/09/15.
 */
public class CurrencyBeanFactory {

    public static Currency createInstance(String symbol, String country) {
        return new Currency(symbol, country);
    }

    public Currency createUKCurrencyInstance(){

        return new Currency("£", "UK");
    }

    public static Currency createUSCurrencyInstance(){

        return new Currency("$", "US");
    }

}
