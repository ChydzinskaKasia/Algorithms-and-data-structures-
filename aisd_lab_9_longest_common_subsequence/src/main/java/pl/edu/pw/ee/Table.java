package pl.edu.pw.ee;

public class Table {
    private Cell[][] cells;
    private final int[][] lcsArray;
    private final String first;
    private final String second;

    public Table(int[][] lcsArray, String firstString, String secondString)

    {
        this.lcsArray = lcsArray.clone();
        first = firstString;
        second = secondString;
        cells = new Cell[lcsArray.length][lcsArray[0].length];
        for (int i = 0; i < cells.length; ++i)
            for (int j = 0; j < cells[i].length; ++j)
                cells[i][j] = new Cell(lcsArray[i][j], MarkType.EMPTY);
        drawPath(firstString, secondString);
        buildHeaders();
    }

    private void buildHeaders() {
        Cell[][] cellsCopy = cells.clone();
        cells = new Cell[lcsArray.length + 1][lcsArray[0].length + 1];
        for (int row = 1; row < cells.length; ++row) {
            for (int col = 1; col < cells[0].length; ++col) {
                cells[row][col] = cellsCopy[row - 1][col - 1];
            }
        }
        int idx = 2;
        cells[0][0] = new Cell(' ');
        cells[1][0] = new Cell(' ');
        for (char c : first.toCharArray()) {
            cells[idx++][0] = new Cell(c);
        }

        idx = 2;
        cells[0][1] = new Cell(' ');
        for (char c : second.toCharArray()) {
            cells[0][idx++] = new Cell(c);
        }
    }

    private void drawPath(String first, String second) {
        int rowIdx = lcsArray.length - 1;
        int colIdx = lcsArray[0].length - 1;
        while (rowIdx != 0 && colIdx != 0) {

            if (first.charAt(rowIdx - 1) == second.charAt(colIdx - 1)) {
                cells[rowIdx][colIdx] = new Cell(lcsArray[rowIdx][colIdx], MarkType.DIAGONAL);
                rowIdx--;
                colIdx--;

            } else if (lcsArray[rowIdx][colIdx - 1] > lcsArray[rowIdx - 1][colIdx]) {
                cells[rowIdx][colIdx] = new Cell(lcsArray[rowIdx][colIdx], MarkType.LEFT);
                colIdx--;
            } else {
                cells[rowIdx][colIdx] = new Cell(lcsArray[rowIdx][colIdx], MarkType.UP);
                rowIdx--;
            }
        }
    }

    public void display() {
        System.out.println();
        for (int i = 0; i < cells.length; ++i) {
            for (int j = 0; j < 3; ++j) {
                for (Cell c : cells[i])
                    c.displayRow(j);
                System.out.println();
            }

        }
    }
}
