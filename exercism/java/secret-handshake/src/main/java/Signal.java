/**
 * The algorithm implemented in the class @link HandshakeCalculator rely on the declaration order
 * WINK, DOUBLE_BLINK, CLOSE_YOUR_EYES, JUMP and REVERSE.
 * As a matter of the value in the array return by the method values() are guaranteed to
 * be in the declared order and the algorithm rely on this order.
 */
enum Signal {
    WINK(0b00001),
    DOUBLE_BLINK(0b00010),
    CLOSE_YOUR_EYES(0b00100),
    JUMP(0b01000),
    REVERSE(0b10000);

    private final int mask;

    Signal(int mask) {
        this.mask = mask;
    }

    public boolean isPresentInNumber(int number) {
        return (number & mask) == mask;
    }
}
