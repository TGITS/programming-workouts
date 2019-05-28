import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HandshakeCalculator {

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
        if(numberInBinary >= 1000) {
            final List<Signal> temp = computeHandshake(numberInBinary - 1000, handshake);
            temp.add(Signal.JUMP);
            return temp;
        }
        if(numberInBinary >= 100) {
            final List<Signal> temp = computeHandshake(numberInBinary - 100, handshake);
            temp.add(Signal.CLOSE_YOUR_EYES);
            return temp;
        }
        if(numberInBinary >= 10) {
            final List<Signal> temp = computeHandshake(numberInBinary - 10, handshake);
            temp.add(Signal.DOUBLE_BLINK);
            return temp;
        }
        if(numberInBinary >= 1) {
            final List<Signal> temp = computeHandshake(numberInBinary - 1, handshake);
            temp.add(Signal.WINK);
            return temp;
        }
        return handshake;
    }
}
