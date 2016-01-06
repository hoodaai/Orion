package factory_bean;

/**
 * Created by varun on 20/09/15.
 */
public class SocialEngine {

    private String connectorURL;

    public void sayHello() {
        System.out.println("Hello !!" + this.connectorURL);
    }

    public void setConnectorURL(String connectorURL) {
        this.connectorURL = connectorURL;
    }
}
