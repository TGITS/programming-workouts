package com.example.chess;

/**
 * Pièces du jeu d'échecs avec leur valeur relative et leur symbole Unicode.
 * Contient également une enum imbriquée {@link Color} pour tester
 * la détection des enums dans des classes wrapper.
 */
public class ChessPiece {

    /**
     * Couleur d'une pièce.
     */
    public enum Color {
        /** Pièces blanches — jouent en premier. */
        WHITE,
        /** Pièces noires. */
        BLACK;

        /** Retourne la couleur adverse. */
        public Color opponent() {
            return this == WHITE ? BLACK : WHITE;
        }
    }

    /**
     * Type de pièce avec sa valeur centipawn (100 cp = 1 pion)
     * et ses symboles Unicode blanc et noir.
     */
    public enum Type {
        /** Le pion — pièce de base. */
        PAWN  (100,   "♙", "♟"),
        /** Le cavalier — se déplace en L. */
        KNIGHT(320,   "♘", "♞"),
        /** Le fou — se déplace en diagonale. */
        BISHOP(330,   "♗", "♝"),
        /** La tour — se déplace horizontalement et verticalement. */
        ROOK  (500,   "♖", "♜"),
        /** La dame — pièce la plus puissante. */
        QUEEN (900,   "♕", "♛"),
        /** Le roi — pièce à protéger à tout prix. */
        KING  (20000, "♔", "♚");

        private final int    centipawnValue;
        private final String whiteSymbol;
        private final String blackSymbol;

        Type(int centipawnValue, String whiteSymbol, String blackSymbol) {
            this.centipawnValue = centipawnValue;
            this.whiteSymbol    = whiteSymbol;
            this.blackSymbol    = blackSymbol;
        }

        public int    centipawnValue()    { return centipawnValue; }
        public String symbol(Color color) { return color == Color.WHITE ? whiteSymbol : blackSymbol; }
    }

    private final Type  type;
    private final Color color;

    public ChessPiece(Type type, Color color) {
        this.type  = type;
        this.color = color;
    }

    public Type  type()  { return type; }
    public Color color() { return color; }

    @Override
    public String toString() { return type.symbol(color); }
}
