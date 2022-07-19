
public class IntervalException extends ArrayIndexOutOfBoundsException {

    private String someString;

    public IntervalException (String string) {
        this.someString = string;
        System.out.println("Exception IntervalException");
    }

    public void myOwnExceptionMsg() {
        System.err.println("This is exception message for string: " + someString);
    }
}