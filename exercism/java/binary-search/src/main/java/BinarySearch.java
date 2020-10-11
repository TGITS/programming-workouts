import java.util.List;

public class BinarySearch {

    private List<Integer> sortedList;

    public BinarySearch(List<Integer> sortedList) {
        this.sortedList = sortedList;
    }

    public int indexOf(int valueToSearch) throws ValueNotFoundException {
        int minIndexOfSearchSpace = 0;
        int maxIndexOfSearchSpace = sortedList.size() - 1;
        int middleIndexOfSearchSpace;
        int numberOfElementsToSearch;
        while (maxIndexOfSearchSpace >= minIndexOfSearchSpace) {
            numberOfElementsToSearch = maxIndexOfSearchSpace - minIndexOfSearchSpace + 1;
            middleIndexOfSearchSpace = minIndexOfSearchSpace + numberOfElementsToSearch / 2;

            if (valueToSearch == sortedList.get(middleIndexOfSearchSpace)) {
                return middleIndexOfSearchSpace;
            }

            if (valueToSearch < sortedList.get(middleIndexOfSearchSpace)) {
                maxIndexOfSearchSpace = middleIndexOfSearchSpace - 1;
            } else {
                minIndexOfSearchSpace = middleIndexOfSearchSpace + 1;
            }
        }

        throw new ValueNotFoundException("Value not in array");
    }
}