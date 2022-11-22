package org.geekbrains;

public class Solution151 {
    public static void main(String[] args) {
        String s = "the sky is blue";
        System.out.println(reverseWords(s));
    }

    public static String reverseWords(String s) {
        int countWords = 0;
        int index = 0;
        int indexStartWord = -1;
        int indexStopWord = -1;

        StringBuilder word = null;
        StringBuilder res = new StringBuilder();

        while (index < s.length()) {
            if (s.charAt(index) == ' ') {
                if (indexStartWord != -1)
                    indexStopWord = index - 1;
                else {
                    index++;
                    continue;
                }
            } else {
                indexStopWord = index;
                if (indexStartWord == -1) {
                    word = new StringBuilder();
                    indexStartWord = index;
                }
                if(index != s.length()-1) {
                    index++;
                    continue;
                }
            }

            for (int i = indexStartWord; i <= indexStopWord; i++) {
                word.append(s.charAt(i));
            }
            countWords++;

            if (countWords > 1)
                res.insert(0, " ");
            res.insert(0, word);

            index++;
            indexStartWord = -1;
        }

        return res.toString();
    }
}
