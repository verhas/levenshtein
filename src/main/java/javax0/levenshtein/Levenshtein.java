package javax0.levenshtein;

/**
 * Simplified and limited version of Levenshtein distance calculation.
 * For more information on this distance google Levenshtein.
 *
 * <p> This implementation is used to guess the parameter name in case
 * it is not found because the user made a typo. In that case the error
 * message suggest the closest keyword. Thus, the calculation of the
 * distance is limited to {@code MAX_COST}. Because of this the
 * recursive and "slow" algorithm is used as it cannot explode
 * exponentially because the maximal cost is limited to {@code
 * MAX_COST}.
 */
public class Levenshtein {

    // snipline MAX_COST
    private static final int MAX_COST = 5;

    /**
     * Calculate the distance between two strings. The distance between two strings is the number of character
     * changes that are needed to change one string to the other.
     *
     * @param x one of the strings
     * @param y the other string
     * @return the distance or max 5
     */
    public static int distance(String x, String y) {
        final Levenshtein lev = new Levenshtein();
        lev.maxCost = MAX_COST;
        return lev.calculate(x, y, 0);
    }

    private int maxCost;

    private int calculate(String x, String y, int cost) {
        if (cost >= maxCost) {
            return maxCost;
        }
        if (x.isEmpty()) {
            return y.length();
        }

        if (y.isEmpty()) {
            return x.length();
        }
        int thisCost = x.charAt(0) == y.charAt(0) ? 0 : 1;
        int substitution = calculate(x.substring(1), y.substring(1), cost + thisCost)
            + thisCost;
        int insertion = calculate(x, y.substring(1), cost + 1) + 1;
        final var min = Math.min(substitution, insertion);
        int deletion = calculate(x.substring(1), y, cost + 1) + 1;
        return Math.min(min, deletion);
    }
}
