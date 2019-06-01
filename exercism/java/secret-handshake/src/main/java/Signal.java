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

    public int mask() {
        return mask;
    }
}
