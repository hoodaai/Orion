package spring_dependency_injection;

/**
 *
 */
public class UpperCaseConverter implements Converter{

    @Override
    public void convert(String string, Integer acceptableLength) {
        if(string.length() <= acceptableLength) {
            string = string.toUpperCase();
            System.out.println(string);
        } else {
            System.out.println("String length is not acceptable");
        }
    }
}
