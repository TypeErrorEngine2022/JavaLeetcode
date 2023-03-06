import javax.naming.spi.StateFactory;

public class LC67_AddBinary {
    public static String addBinaryString(String a, String b) {
        if (a.equals("0")) return b;
        if (b.equals("0")) return a;

        StringBuilder res = new StringBuilder();
        int aPtr = a.length() - 1;
        int bPtr = b.length() - 1;

        int carryIn = 0;
        int sum = 0;
        while (aPtr >= 0 || bPtr >= 0 || carryIn != 0) {
            if (aPtr >= 0 && bPtr >= 0) {
                int digit1 = a.charAt(aPtr) - '0';
                int digit2 = b.charAt(bPtr) - '0';
                sum = digit1 + digit2 + carryIn;
            }
            else if (aPtr >= 0) {
                sum = (a.charAt(aPtr) - '0') + carryIn;
            }
            else if (bPtr >= 0){
                sum = (b.charAt(bPtr) - '0') + carryIn;
            }
            else if (carryIn != 0) {
                sum = carryIn;
            }
            carryIn = sum / 2;
            if (sum % 2 != 0) res.append('1');
            else res.append('0');
            aPtr--;
            bPtr--;
        }
        res.reverse();
        return res.toString();
    }

    // will overflow -> abandoned
    /*public static String addBinaryNumber(String a, String b) {
        if (a.equals("0")) return b;
        if (b.equals("0")) return a;

        long aNum = 0;

        for (int i = a.length() - 1; i >= 0; i--) {
            aNum += Math.pow(2, a.length() - 1 - i) * (a.charAt(i) - '0');
        }
        for (int i = b.length() - 1; i >= 0; i--) {
            aNum += Math.pow(2, b.length() - 1 - i) * (b.charAt(i) - '0');
        }

        System.out.println(aNum);

        StringBuilder res = new StringBuilder();
        while (aNum > 0) {
            long digit = aNum % 2;
            aNum /= 2;
            res.append(digit);
        }
        res.reverse();
        return res.toString();
    }*/

    public static void main(String[] args) {
        String a = "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101";
        String b = "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011";
        System.out.println(addBinaryString(a, b));
    }
}
