package spring_dependency_injection;

import org.springframework.beans.factory.annotation.Required;

/*
 * Converts lowercase string to uppercase.
 */
public class StringConverter {

    private String string;
    private Integer acceptableLength;
    private Converter converter;

    public StringConverter() {
    }

    public StringConverter(String string, Integer acceptableLength, Converter converter) {
        this.string = string;
        this.acceptableLength = acceptableLength;
        this.converter = converter;
    }

    public void converter() {
        converter.convert(string, acceptableLength);
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setAcceptableLength(Integer acceptableLength) {
        this.acceptableLength = acceptableLength;
    }

    //@Required annotation on a setter method makes the property a required dependency.
    public void setConverter(Converter converter) {
        this.converter = converter;
    }
}
