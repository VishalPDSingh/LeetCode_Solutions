class Solution {
    public int mostWordsFound(String[] sentences) {
        int maxWords = 0;
        for (String sentence : sentences) {
            maxWords = Math.max(maxWords, sentence.split(" ").length);
        }
        return maxWords;
    }
}