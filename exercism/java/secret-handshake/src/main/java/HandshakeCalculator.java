import java.util.*;

class HandshakeCalculator {

    private static final Map<Integer, Optional<Signal>> SIGNAL_BY_BINARY_VALUE = new HashMap<>();

    static {
        SIGNAL_BY_BINARY_VALUE.put(10000, Optional.empty());
        SIGNAL_BY_BINARY_VALUE.put(1000, Optional.of(Signal.JUMP));
        SIGNAL_BY_BINARY_VALUE.put(100, Optional.of(Signal.CLOSE_YOUR_EYES));
        SIGNAL_BY_BINARY_VALUE.put(10, Optional.of(Signal.DOUBLE_BLINK));
        SIGNAL_BY_BINARY_VALUE.put(1, Optional.of(Signal.WINK));
    }

    private static final int[] BINARY_VALUES_FOR_SIGNAL = new int[]{10000, 1000, 100, 10, 1};


    public List<Signal> calculateHandshake(int number) {
        return computeHandshake(decimalToBinary(number), new ArrayList<>());
    }

    private int decimalToBinary(int decimal) {
        return Integer.parseInt(Integer.toBinaryString(decimal));
    }

    private List<Signal> computeHandshake(int numberInBinary, List<Signal> handshake) {

        for (int value : BINARY_VALUES_FOR_SIGNAL) {
            if (numberInBinary >= value) {
                final List<Signal> temp = computeHandshake(numberInBinary - value, handshake);
                SIGNAL_BY_BINARY_VALUE.get(value).ifPresent(temp::add);
                if (!SIGNAL_BY_BINARY_VALUE.get(value).isPresent()) {
                    Collections.reverse(temp);
                }
                return temp;
            }
        }

        return handshake;
    }
}
