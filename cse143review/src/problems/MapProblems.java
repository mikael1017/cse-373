package problems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * See the spec on the website for example behavior.
 */
public class MapProblems {

    /**
     * Returns true if any string appears at least 3 times in the given list; false otherwise.
     */
    public static boolean contains3(List<String> list) {
        HashMap<String, Integer> dict = new HashMap<>();
        for (String word: list) {
            if (dict.containsKey(word)) {
                dict.put(word, dict.get(word) + 1);
            } else {
                dict.put(word, 1);
            }
        }
        return dict.containsValue(3);
    }

    /**
     * Returns a map containing the intersection of the two input maps.
     * A key-value pair exists in the output iff the same key-value pair exists in both input maps.
     */
    public static Map<String, Integer> intersect(Map<String, Integer> m1, Map<String, Integer> m2) {
        Map<String, Integer> intersect = new HashMap<>();
        for (String s : m1.keySet()) {
            if (m2.containsKey(s)) {
                if (m1.get(s).equals(m2.get(s))) {
                    intersect.put(s, m1.get(s));
                }
            }
        }
        return intersect;
    }
}
