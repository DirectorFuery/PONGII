package Utils;

public class Statics {
    public static int getHalfPointOfOddNumber(int number) {
        return (number - 1) / 2 + 1;
    }

    public static String repeatChar(char ch, int count) {
        return String.valueOf(ch).repeat(count);
    }
}
