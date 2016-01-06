package circular_dependency;

/**
 * Created by varun on 19/09/15.
 */
public class Builder {
    private Creator creator;

    // do not use constructor to create object to avoid circular dependency. instead use setter-dependency injection
   /* public Builder(Creator creator) {
    this.creator = creator;
    }*/

    public void sayHello() {
        System.out.println("Hello !!");
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }
}
