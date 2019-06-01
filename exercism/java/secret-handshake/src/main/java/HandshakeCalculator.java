import java.util.*;

class HandshakeCalculator {

    private final Signal[] signals ;

    public HandshakeCalculator() {
        signals = Signal.values();
        Arrays.sort(this.signals, Comparator.comparingInt(Signal::mask).reversed());
    }

    public List<Signal> calculateHandshake(int number) {
        return computeHandshake(number, new ArrayList<>());
    }

    private List<Signal> computeHandshake(int number, List<Signal> handshake) {
        for (Signal signal :  signals) {
            if ((number & signal.mask()) == signal.mask()) {
                final List<Signal> temp = computeHandshake(number - signal.mask(), handshake);
                if (signal == Signal.REVERSE) {
                    Collections.reverse(temp);
                } else {
                    temp.add(signal);
                }
                return temp;
            }
        }

        return handshake;
    }
}
