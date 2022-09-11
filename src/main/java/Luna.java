import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Luna {

    private final static Map<Integer, String> PAYMENT_SYSTEM = new HashMap<>();

    static {
        PAYMENT_SYSTEM.put(2, "Мир");
        PAYMENT_SYSTEM.put(30, "Diners Club");
        PAYMENT_SYSTEM.put(36, "Diners Club");
        PAYMENT_SYSTEM.put(38, "Diners Club");
        PAYMENT_SYSTEM.put(31, "JCB International");
        PAYMENT_SYSTEM.put(35, "JCB International");
        PAYMENT_SYSTEM.put(34, "American Express");
        PAYMENT_SYSTEM.put(37, "American Express");
        PAYMENT_SYSTEM.put(4, "VISA");
        PAYMENT_SYSTEM.put(50, "Maestro");
        PAYMENT_SYSTEM.put(56, "Maestro");
        PAYMENT_SYSTEM.put(57, "Maestro");
        PAYMENT_SYSTEM.put(58, "Maestro");
        PAYMENT_SYSTEM.put(51, "MasterCard");
        PAYMENT_SYSTEM.put(52, "MasterCard");
        PAYMENT_SYSTEM.put(53, "MasterCard");
        PAYMENT_SYSTEM.put(54, "MasterCard");
        PAYMENT_SYSTEM.put(55, "MasterCard");
        PAYMENT_SYSTEM.put(60, "Discover");
        PAYMENT_SYSTEM.put(62, "China UnionPay");
        PAYMENT_SYSTEM.put(63, "Maestro");
        PAYMENT_SYSTEM.put(67, "Maestro");
        PAYMENT_SYSTEM.put(7, "УЭК");
    }


    private Luna() {
    }

    public static void getReport(int[] cardNumber) {
        System.out.println("------------------------------------");
        System.out.println("Card number - " + convert(cardNumber));
        System.out.println("alg Luna - " + calc(cardNumber));
        System.out.println("Payment system - " + determinePS(cardNumber));
        System.out.println("BIN - " + determineBIN(cardNumber));
        System.out.println("------------------------------------\n");
    }

    public static void find(int[] cardNumber, int index) {
        for (int i = 0; i < 9; i++) {
            cardNumber[index] = i;
            if (calc(cardNumber)) {
                getReport(cardNumber);
            }
        }
    }

    private static String convert(int[] arr) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i % 4 == 0) result.append(" ");
            result.append(arr[i]);
        }
        return result.toString();
    }

    private static boolean calc(int[] cardNumber) {
        if (cardNumber.length != 16) return false;
        int[] arr = cardNumber.clone();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) arr[i] *= 2;
            if (arr[i] > 9) arr[i] -= 9;
            sum += arr[i];
        }
        return sum % 10 == 0;
    }

    private static String determinePS(int[] cardNumber) {
        if (PAYMENT_SYSTEM.containsKey(cardNumber[0])) {
            return PAYMENT_SYSTEM.get(cardNumber[0]);
        } else if (PAYMENT_SYSTEM.containsKey(cardNumber[0] * 10 + cardNumber[1])) {
            return PAYMENT_SYSTEM.get(cardNumber[0] * 10 + cardNumber[1]);
        }
        return "EMPTY";
    }

    private static String determineBIN(int[] cardNumber) {
        return cardNumber[1] + "" + cardNumber[2] + "" + cardNumber[3] + "" + cardNumber[4] + "" + cardNumber[5] + "" + cardNumber[6];
    }
}
