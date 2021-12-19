package pl.edu.pw.ee;

public class Cell {
    private final char[][] chars = new char[3][3];

    public Cell(int value, MarkType marktype) {
        for (int i = 0; i < chars.length; ++i)
            for (int j = 0; j < chars[i].length; ++j)
                chars[i][j] = ' ';
        chars[1][1] = (char) (value + '0');

        switch (marktype) {
            case LEFT:
                chars[1][0] = '<';
                break;
            case UP:
                chars[0][1] = '^';
                break;
            case DIAGONAL:
                chars[0][0] = '\\';
                break;
            case EMPTY:
                break;
        }
    }

    public Cell(char value) {
        for (int i = 0; i < chars.length; ++i) {
            for (int j = 0; j < chars[i].length; ++j)
                chars[i][j] = ' ';

            if (value == '\n') {
                chars[1][0] = '\\';
                chars[1][1] = 'n';
            } else {
                chars[1][1] = value;
            }
        }
    }

    public void displayRow(int i) {
        for (char c : chars[i])
            System.out.print(c);
    }

}
