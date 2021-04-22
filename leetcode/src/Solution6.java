import java.util.ArrayList;
import java.util.List;

public class Solution6 {
    public static void main(String [] args) {
        String s = "abc";
        String attempt = "abc";
        int numRows = 1;
        String ans = convert2(s, numRows);
        assert attempt.equals(ans);
        System.out.println(attempt);
        System.out.println(ans);
    }

    /**
     * 模拟解法
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        int h = numRows;
        int w = s.length();

        char [][] table = new char[h][w];
        int index = 0;
        int direction = 1;
        int x = 0, y = 0;
        while (index < s.length()) {
            table[x][y] = s.charAt(index);
            index++;

            if (direction == 1) {
                if (x == h - 1) {
                    direction = -1;
                    if (x - 1 >= 0) {
                        x--;
                    }
                    y++;
                } else {
                    if (x + 1 < h) {
                        x++;
                    }
                }
            } else {
                if (x == 0) {
                    direction = 1;
                    if (x + 1 < h) {
                        x++;
                    } else {
                        y++;
                    }
                } else {
                    if (x - 1 >= 0) {
                        x--;
                    }
                    y++;
                }
            }

            if (y >= w) {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int h_index = 0; h_index < h; h_index++) {
            for (int w_index = 0; w_index < w; w_index++) {
                if (table[h_index][w_index] != '\u0000') {
                    sb.append(table[h_index][w_index]);
                }
            }
        }

        return sb.toString();
    }

    /**
     * 上述模拟解法的优化版
     * 思路类似，但简化里很多细节
     * @param s
     * @param numRows
     * @return
     */
    public static String convert2(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);

            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }

            curRow += goingDown ? 1 : -1;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : rows) {
            res.append(sb);
        }
        return res.toString();
    }
}
