package pl.edu.pw.ee;

class LongestCommonSubsequence {

    private int[][] lcsArray;
    private final String firstStr;
    private final String secondStr;

    public LongestCommonSubsequence(String firstStr, String secondStr) {

        if (firstStr == null || secondStr == null) {
            throw new IllegalArgumentException("Params cannot be null");
        }
        this.firstStr = firstStr;
        this.secondStr = secondStr;
        buildLcsArray();

    }

    private void buildLcsArray() {
        int firstStrLength = firstStr.length();
        int secondStrLength = secondStr.length();
        lcsArray = new int[firstStrLength + 1][secondStrLength + 1];
        for (int i = 0; i < firstStrLength; i++) {
            for (int j = 0; j < secondStrLength; j++) {
                if (firstStr.charAt(i) == secondStr.charAt(j)) {
                    lcsArray[i + 1][j + 1] = 1 + lcsArray[i][j];
                } else {
                    lcsArray[i + 1][j + 1] = Math.max(lcsArray[i + 1][j], lcsArray[i][j + 1]);
                }
            }
        }
    }

    public String findLCS() {
        StringBuilder lcs = new StringBuilder();
        int i = firstStr.length();
        int j = secondStr.length();
        while (i > 0 && j > 0) {
            if (firstStr.charAt(i - 1) == secondStr.charAt(j - 1)) {
                lcs.append(firstStr.charAt(i - 1));
                i--;
                j--;
            } else if (lcsArray[i - 1][j] > lcsArray[i][j - 1])
                i--;
            else
                j--;
        }
        return lcs.reverse().toString();
    }

    public void display() {
        Table table = new Table(lcsArray, firstStr, secondStr);
        table.display();
    }

    public static void main(String[] args) {

        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence(
                "rzeczy_nie_trzeba\n_się_spieskzyć", "często_z_odkrywaniem");
        longestCommonSubsequence.display();
        System.out.println(longestCommonSubsequence.findLCS());
    }

}