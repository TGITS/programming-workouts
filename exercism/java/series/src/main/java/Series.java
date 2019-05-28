import java.util.ArrayList;
import java.util.List;

public class Series {

    private static final String SLICE_SIZE_TOO_SMALL = "Slice size is too small.";
    private static final String SLICE_SIZE_TOO_BIG = "Slice size is too big.";


    private final String number;

    public Series(String s) {
        this.number = s;
    }

    public List<String> slices(int sliceLength) {
        if (sliceLength <= 0) {
            throw new IllegalArgumentException(SLICE_SIZE_TOO_SMALL);
        }

        final int numberLength = this.number.length();

        if (sliceLength > numberLength) {
            throw new IllegalArgumentException(SLICE_SIZE_TOO_BIG);
        }

        int maxNumberOfSlices = numberLength - sliceLength;

        List<String> slices = new ArrayList<>();

        for (int i = 0; i <= maxNumberOfSlices; i++) {
            slices.add(number.substring(i, i + sliceLength));
        }

        return slices;
    }
}
