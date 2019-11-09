import java.math.BigInteger;

class Grains {

    private static final String errorMessage = "square must be between 1 and 64";
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private BigInteger[] board = new BigInteger[64];

    BigInteger grainsOnSquare(final int square) {
        if (square < 1 || square > 64) {
            throw new IllegalArgumentException(errorMessage);
        }
        int indice = square - 1;
        if (board[indice] == null) {
            board[indice] = TWO.pow(indice);
        }
        return board[indice];
    }

    BigInteger grainsOnBoard() {
        BigInteger sum = BigInteger.ZERO;
        for (int i = 1; i < 65; i++) {
            sum = sum.add(grainsOnSquare(i));
        }
        return sum;
    }

}
