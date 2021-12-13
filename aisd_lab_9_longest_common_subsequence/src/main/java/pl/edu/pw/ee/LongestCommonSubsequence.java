package pl.edu.pw.ee;

class LongestCommonSubsequence {

    private static final int[][] L = null;

    // jeżeli znajdzie nam dwa podciągi o tej samej długości to wyswietlamy jeden z
    // nich, a nie oba, ani żadnej tabicy etc.

    public LongestCommonSubsequence() {
    }

    public LongestCommonSubsequence(String firstStr, String secondStr) {

        char[] firstStringChar = new char[firstStr.length()];

        for (int i = 0; i < firstStr.length(); i++) {
            firstStringChar[i] = firstStr.charAt(i);
        }

        char[] secondStringChar = new char[secondStr.length()];

        for (int i = 0; i < secondStr.length(); i++) {
            secondStringChar[i] = secondStr.charAt(i);
        }

        String[] firstStrArray = new String[] { firstStr };
        String[] secondStrArray = new String[] { secondStr };

        for (int i = 0; i < firstStrArray.length; i++) {

        }

    }

    public String findLCS() {
        // TODO
        return null;
    }

    public void display() {
        // TODO

    }

    public static void main(String[] args) {
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence("AA", "BB");
    }

}
