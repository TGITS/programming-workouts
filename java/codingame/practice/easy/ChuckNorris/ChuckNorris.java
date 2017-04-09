import java.util.*;
import java.util.stream.*;
import java.io.*;
import java.math.*;


class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String message = in.nextLine();
        System.err.println("The initial message : " + message);
        String binaryMessage = stringToBinary(message);
        System.err.println("The message in binary : "+ binaryMessage);
        int binaryMessageLength = binaryMessage.length();
        int i = 0;
        boolean startNewBlock = true;
        Block currentBlock = null;
        char currentChar;
        List<Block> blocks = new ArrayList<Block>();
        while (i < binaryMessageLength) {
            currentChar = binaryMessage.charAt(i);
            if(startNewBlock) {
                currentBlock = new Block(currentChar);
                currentBlock.incTokenRepetition();
                blocks.add(currentBlock);
            }
            else {
                currentBlock.incTokenRepetition();
            }
            if ((i < binaryMessageLength-1) && (currentChar == binaryMessage.charAt(i+1))) {
                startNewBlock = false;
            }
            else {
                startNewBlock = true;
            }
            i++;
        }


        String answer = blocks.stream().map(block -> block.toString()).collect(Collectors.joining(" "));
        System.err.println("The message in unary : " +answer);
        System.out.println(answer);
    }

    public static String stringToBinary(String str) {
        StringBuilder result = new StringBuilder("");
        char[] messChar = str.toCharArray();

        for (int i = 0; i < messChar.length; i++) {
            String sequence = Integer.toBinaryString(messChar[i]);
            while(sequence.length() < 7) {
                sequence = "0" + sequence;
            }
            result.append(sequence);
        }

        return result.toString();
    }
}

class Block {
    private int tokenRepetition;
    private BlockType type;

    public Block(char initialToken){
        this.tokenRepetition = 0;
        this.type = BlockType.fromToken(initialToken);
    }

    public void incTokenRepetition(){
        this.tokenRepetition ++;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(type.prefix());
        sb.append(" ");
        for(int i = 0; i < this.tokenRepetition ; i++) {
            sb.append(type.outputToken());
        }
        return sb.toString();
    }
}

enum BlockType {
    ZERO ("00", '0', '0'),
    ONE ("0", '1', '0');

    private final String prefix;
    private final char token;
    private final char outputToken;

    BlockType(String prefix, char token, char outputToken) {
        this.prefix = prefix;
        this.token = token;
        this.outputToken = outputToken;
    }

    public String prefix(){
        return this.prefix;
    }

    public char token(){
        return this.token;
    }

    public char outputToken(){
        return this.outputToken;
    }

    private static Map<Character,BlockType> blockTypeByToken = new HashMap<Character,BlockType>();
    static {
        for(BlockType blockType : values()) {
            blockTypeByToken.put(blockType.token(), blockType);
        }
    }

	public static BlockType fromToken(char token) {
		final BlockType value = blockTypeByToken.get(token);
		if (value != null) {
			return value;
		}
		throw new IllegalArgumentException("Unkown token : " + Character.toString(token));
	}
}
