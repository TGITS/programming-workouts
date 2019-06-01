import java.util.*;

class HandshakeCalculator {

    private static final Map<Integer, Optional<Signal>> SIGNAL_BY_BINARY_VALUE = new HashMap<>();

    static {
        SIGNAL_BY_BINARY_VALUE.put(0b10000, Optional.empty());
        SIGNAL_BY_BINARY_VALUE.put(0b01000, Optional.of(Signal.JUMP));
        SIGNAL_BY_BINARY_VALUE.put(0b00100, Optional.of(Signal.CLOSE_YOUR_EYES));
        SIGNAL_BY_BINARY_VALUE.put(0b00010, Optional.of(Signal.DOUBLE_BLINK));
        SIGNAL_BY_BINARY_VALUE.put(0b00001, Optional.of(Signal.WINK));
    }

    private static final int[] BINARY_VALUES_FOR_SIGNAL = new int[]{0b10000, 0b01000, 0b00100, 0b00010, 0b00001};

    public List<Signal> calculateHandshake(int number) {
        return computeHandshake(number, new ArrayList<>());
    }

    private List<Signal> computeHandshake(int numberInBinary, List<Signal> handshake) {
        for (int value : BINARY_VALUES_FOR_SIGNAL) {
            if ((numberInBinary & value) == value) {
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
