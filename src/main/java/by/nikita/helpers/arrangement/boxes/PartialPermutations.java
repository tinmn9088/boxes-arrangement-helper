package by.nikita.helpers.arrangement.boxes;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.stream.IntStream;

/**
 * See: <a href=
 * "https://en.wikipedia.org/wiki/Partial_permutation#Restricted_partial_permutations">Partial
 * permutation on Wikipedia</a>
 */
public class PartialPermutations implements Permutations {

    /**
     * @implNote is always >=2.
     */
    private final int n;

    /**
     * @param n permutation length.
     * @throws IllegalArgumentException if {@code n} < 2.
     */
    public PartialPermutations(int n) {
        if (n < 2) {
            throw new IllegalArgumentException(MessageFormat.format("n cannot be less than 2: {0}", n));
        }

        this.n = n;
    }

    @Override
    public Iterator<int[]> iterator() {
        return new PartialPermutationsIterator();
    }

    private class PartialPermutationsIterator implements Iterator<int[]> {

        private final int[] permutation;

        /**
         * @implNote if -1 then no permutations left.
         */
        private int mark;

        private boolean firstPermutation;

        public PartialPermutationsIterator() {
            permutation = IntStream.rangeClosed(1, n).toArray();
            firstPermutation = true;
        }

        @Override
        public boolean hasNext() {

            // look from the end for such i when permutation[i] < permutation[i + 1],
            // start from the second last element
            int i = n - 2;

            while (i != -1 && permutation[i] >= permutation[i + 1]) {
                i--;
            }

            return (/* update mark */ mark = i) != -1;
        }

        /**
         * @throws IllegalStateException no permutations left.
         */
        @Override
        public int[] next() {

            if (firstPermutation) {

                // do nothing, need to return original sequence
                firstPermutation = false;
            } else {

                if (mark < 0) {
                    throw new IllegalStateException();
                }

                if (n == 2) {

                    swap(0, 1);

                } else {

                    // look from the end for such i when permutation[i] > permutation[mark]
                    // start from the last element
                    int i = n - 1;

                    while (permutation[i] <= permutation[mark]) {
                        i--;
                    }

                    // swap permutation[mark] with permutation[i]
                    swap(mark, i);

                    // now elements to the right of permutation[mark] are sorted in descending
                    // order,
                    // reverse to sort in ascending order
                    int left = mark + 1;
                    int right = n - 1;
                    while (left < right) {
                        swap(left, right);
                        left++;
                        right--;
                    }
                }
            }

            // new permutation is ready
            return permutation.clone();
        }

        private void swap(int index1, int index2) {
            int buf = permutation[index1];
            permutation[index1] = permutation[index2];
            permutation[index2] = buf;
        }
    }
}
