package by.nikita.helpers.arrangement.boxes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartialPermutationsTest {

    private static void assertPermutationEquals(int[][] expectedPermutations, PartialPermutations actualPermutations) {
        int i = 0;

        for (int[] actualPermutation : actualPermutations) {
            assertArrayEquals(expectedPermutations[i++], actualPermutation);
        }
    }

    @Test
    void constructorShouldNotAllowNotNaturalArgument() {
        assertThrows(IllegalArgumentException.class, () -> new PartialPermutations(-1));
        assertThrows(IllegalArgumentException.class, () -> new PartialPermutations(0));
        assertThrows(IllegalArgumentException.class, () -> new PartialPermutations(1));
        assertDoesNotThrow(() -> new PartialPermutations(2));
    }

    @Test
    void iteratorShouldGeneratePermutationsFor2() {
        PartialPermutations permutations = new PartialPermutations(2);

        int[][] expectedPermutations = new int[][] {
                new int[] { 1, 2 }, new int[] { 2, 1 },
        };

        assertPermutationEquals(expectedPermutations, permutations);
    }

    @Test
    void iteratorShouldGeneratePermutationsFor3() {
        PartialPermutations permutations = new PartialPermutations(3);

        int[][] expectedPermutations = new int[][] {
                new int[] { 1, 2, 3 },
                new int[] { 1, 3, 2 },
                new int[] { 2, 1, 3 },
                new int[] { 2, 3, 1 },
                new int[] { 3, 1, 2 },
                new int[] { 3, 2, 1 },
        };

        assertPermutationEquals(expectedPermutations, permutations);
    }

    @Test
    void iteratorShouldGeneratePermutationsFor4() {
        PartialPermutations permutations = new PartialPermutations(4);

        int[][] expectedPermutations = new int[][] {

                // 1-6
                new int[] { 1, 2, 3, 4 },
                new int[] { 1, 2, 4, 3 },
                new int[] { 1, 3, 2, 4 },
                new int[] { 1, 3, 4, 2 },
                new int[] { 1, 4, 2, 3 },
                new int[] { 1, 4, 3, 2 },

                // 7-12
                new int[] { 2, 1, 3, 4 },
                new int[] { 2, 1, 4, 3 },
                new int[] { 2, 3, 1, 4 },
                new int[] { 2, 3, 4, 1 },
                new int[] { 2, 4, 1, 3 },
                new int[] { 2, 4, 3, 1 },

                // 13-18
                new int[] { 3, 1, 2, 4 },
                new int[] { 3, 1, 4, 2 },
                new int[] { 3, 2, 1, 4 },
                new int[] { 3, 2, 4, 1 },
                new int[] { 3, 4, 1, 2 },
                new int[] { 3, 4, 2, 1 },

                // 19-24
                new int[] { 4, 1, 2, 3 },
                new int[] { 4, 1, 3, 2 },
                new int[] { 4, 2, 1, 3 },
                new int[] { 4, 2, 3, 1 },
                new int[] { 4, 3, 1, 2 },
                new int[] { 4, 3, 2, 1 },
        };

        assertPermutationEquals(expectedPermutations, permutations);
    }
}