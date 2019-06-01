import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HandshakeCalculator {

    public List<Signal> calculateHandshake(int number) {
        final List<Signal> handshake = new ArrayList<>();
        for (Signal signal : Signal.values()) {
            if (signal.isPresentInNumber(number)) {
                if (signal == Signal.REVERSE) {
                    Collections.reverse(handshake);
                } else {
                    handshake.add(signal);
                }
            }
        }
        return handshake;
    }
}
