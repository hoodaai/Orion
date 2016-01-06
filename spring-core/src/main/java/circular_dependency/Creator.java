package circular_dependency;

/**
 * Created by varun on 19/09/15.
 */
public class Creator {

    private Builder builder;

    public Creator(Builder builder) {
        this.builder = builder;
    }

    public void sayHello() {
        System.out.println("Hello !!!!");
    }
}
