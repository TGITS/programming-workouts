/*

Since this exercise has a difficulty of > 4 it doesn't come
with any starter implementation.
This is so that you get to practice creating classes and methods
which is an important part of programming in Java.

Please remove this comment when submitting your solution.

*/

import java.util.List;

public class BinarySearch {

    private List<Integer> sortedList;

    public BinarySearch(List<Integer> sortedList) {
        this.sortedList = sortedList;
    }

    public int indexOf(int i) throws ValueNotFoundException {
        int middle_index = sortedList.size() / 2;
        if (sortedList.get(middle_index) == i) {
            return middle_index;
        } else if (i < sortedList.get(middle_index)) {
            return search(i, 0, middle_index);
        } else {
            return search(i, middle_index + sortedList.size() % 2, sortedList.size() - 1);
        }
    }

    private int search(int i, int start_index, int end_index) throws ValueNotFoundException {
        if (start_index == end_index && sortedList.get(start_index) != i) {
            throw new ValueNotFoundException("Value not in array");
        }
        int size = end_index - start_index;
        int middle_index = size / 2;
        if (sortedList.get(middle_index) == i) {
            return middle_index;
        } else if (sortedList.get(start_index) == i) {
            return start_index;
        } else if (sortedList.get(end_index) == i) {
            return end_index;
        } else if (i < sortedList.get(middle_index)) {
            return search(i, 0, middle_index);
        } else {
            return search(i, middle_index + 1 + (size % 2), end_index);
        }
    }
}