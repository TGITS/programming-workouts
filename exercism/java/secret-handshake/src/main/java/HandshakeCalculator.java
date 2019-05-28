import java.util.*;

class HandshakeCalculator {

    private static final Map<Integer, Signal> SIGNAL_BY_BINARY_VALUE = new HashMap<>();
    static {
        SIGNAL_BY_BINARY_VALUE.put(1000, Signal.JUMP);
        SIGNAL_BY_BINARY_VALUE.put(100, Signal.CLOSE_YOUR_EYES);
        SIGNAL_BY_BINARY_VALUE.put(10, Signal.DOUBLE_BLINK);
        SIGNAL_BY_BINARY_VALUE.put(1, Signal.WINK);
    }

    private static final int[] BINARY_VALUES = new int[]{1000, 100, 10, 1};


    public List<Signal> calculateHandshake(int number) {
        return computeHandshake(decimalToBinary(number),new ArrayList<>());
    }

    private int decimalToBinary(int decimal) {
        return Integer.parseInt(Integer.toBinaryString(decimal));
    }

    private List<Signal> computeHandshake(int numberInBinary, List<Signal> handshake) {
        if(numberInBinary >= 10000) {
            final List<Signal> temp = computeHandshake(numberInBinary - 10000, handshake);
            Collections.reverse(temp);
            return temp;
        }

        for(int value:BINARY_VALUES) {
            if (numberInBinary >= value) {
                final List<Signal> temp = computeHandshake(numberInBinary - value, handshake);
                temp.add(SIGNAL_BY_BINARY_VALUE.get(value));
                return temp;
            }
        }
        
        return handshake;
    }
}
