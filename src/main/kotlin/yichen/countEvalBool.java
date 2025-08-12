package yichen;

public class countEvalBool {
    public int countEval(String s, int result) {
        Integer[][][] cache = new Integer[s.length()][s.length()][2];

        calcRange(s, 0, s.length() - 1, cache);

        return cache[0][s.length() - 1][result];
    }

    public void calcRange(String src, int start, int end, Integer[][][] cache) {
        if (cache[start][end][0] != null) {
            return;
        }

        if (start == end) {
            int result = src.charAt(start) - '0';
            saveToCache(result, start, end, cache);
        } else if (end == start + 2) {
            int result = calcValue(src.charAt(start), src.charAt(end), src.charAt(start + 1));
            saveToCache(result, start, end, cache);
        } else {
            cache[start][end][0] = 0;
            cache[start][end][1] = 0;

            for (int i = start; i <= end - 2; i = i + 2) {
                char operator = src.charAt(i + 1);
                calcRange(src, start, i, cache);
                calcRange(src, i + 2, end, cache);

                int left0Count = cache[start][i][0];
                int left1Count = cache[start][i][1];
                int right0Count = cache[i + 2][end][0];
                int right1Count = cache[i + 2][end][1];

                switch (operator) {
                    case '&':
                        cache[start][end][0] += left0Count * right0Count;
                        cache[start][end][0] += left1Count * right0Count;
                        cache[start][end][0] += left0Count * right1Count;
                        cache[start][end][1] += left1Count * right1Count;
                        break;
                    case '|':
                        cache[start][end][0] += left0Count * right0Count;
                        cache[start][end][1] += left1Count * right0Count;
                        cache[start][end][1] += left0Count * right1Count;
                        cache[start][end][1] += left1Count * right1Count;
                        break;
                    case '^':
                        cache[start][end][0] += left0Count * right0Count;
                        cache[start][end][0] += left1Count * right1Count;
                        cache[start][end][1] += left1Count * right0Count;
                        cache[start][end][1] += left0Count * right1Count;
                }
            }
        }
    }

    public void saveToCache(int result, int start, int end, Integer[][][] cache) {
        if (result == 0) {
            cache[start][end][0] = 1;
            cache[start][end][1] = 0;
        } else {
            cache[start][end][0] = 0;
            cache[start][end][1] = 1;
        }
    }

    public int calcValue(char first, char second, char operator) {
        return switch (operator) {
            case '&' -> (first - '0') & (second - '0');
            case '|' -> (first - '0') | (second - '0');
            case '^' -> (first - '0') ^ (second - '0');
            default -> throw new IllegalArgumentException();
        };
    }
}
