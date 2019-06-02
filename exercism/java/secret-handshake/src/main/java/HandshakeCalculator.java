import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HandshakeCalculator {

    private final static Signal[] SIGNALS = Signal.values();

    public List<Signal> calculateHandshake(int number) {
        final List<Signal> handshake = new ArrayList<>();
        for (Signal signal : SIGNALS) {
            if (signal.isPresentInNumber(number)) {
                handshake.add(signal);
            }
        }
        if (handshake.remove(Signal.REVERSE)) {
            Collections.reverse(handshake);
        }
        return handshake;
    }

}
