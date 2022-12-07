package org.geekbrains;

import java.awt.event.TextEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class lesson3_hometask1 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Задача: поиск решения в уравнении для пропущенных цифр вида A? + B? + ... = C, где");
        System.out.println(" A, B - цифра искомого числа (положительное число)");
        System.out.println(" ? - пропущенная цифра искомого числа (положительное число)");
        System.out.println(" Пример: 4? + 2? = 65, где A=4 B=2 искомые цифры числа A и B [[2,3], [3,2], ...]");

        //String expression = "1?+2?=3?";
        //String expression = "1?+2?=36";
        //String expression = "36=1?+2?";

        System.out.print("Введите уравнение: ");
        String expression = in.nextLine();
        while (!checkInputException(expression)) {
            System.out.print("Введите уравнение: ");
            expression = in.nextLine();
        }
        System.out.println("Уравнение: " + expression);
        in.close();

        findSolutionException(expression);
    }

    private static boolean checkInputException(String expression) {
        //убраем пробелы
        expression = expression.replace(" ", "");

        //проверка символа '='
        int indexCharEquals = expression.indexOf('=');
        if (indexCharEquals == -1) {
            System.out.println("Ошибка ввода уравнения: не обнаружен символ равенства '='!");
            return false;
        }
        int countCharEquals = 1;
        for (int i = indexCharEquals + 1; i < expression.length(); i++) {
            if (expression.charAt(i) == '=')
                countCharEquals++;
        }
        if (countCharEquals > 1) {
            System.out.println("Ошибка ввода уравнения: найдено больше 1 символа равенства '='!");
            return false;
        }

        //проверяем что слева и справа от = есть что-то
        indexCharEquals = expression.indexOf('=');
        if (indexCharEquals == 0) {
            System.out.println("Ошибка ввода уравнения: слева от знака равенства нет выражения!");
            return false;
        }
        if (indexCharEquals == expression.length() - 1) {
            System.out.println("Ошибка ввода уравнения: справа от знака равенства нет выражения!");
            return false;
        }

        //проверка на недопустимые символы
        int countTermInLeft = 0;
        int countTermInRight = 0;
        int countCharSumInLeft = 0;
        int countCharSumInRight = 0;
        int countCharQuestionInLeft = 0;
        int countCharQuestionInRight = 0;
        boolean isLeft = true;
        for (int i = 0; i < expression.length(); i++) {
            char charExpression = expression.charAt(i);
            if (charExpression == '=') {
                isLeft = false;
                continue;
            }
            if (charExpression == '+') {
                if (isLeft) countCharSumInLeft++;
                else countCharSumInRight++;
                continue;
            }
            if (charExpression == '?') {
                if (isLeft) countCharQuestionInLeft++;
                else countCharQuestionInRight++;
                continue;
            }
            int charInt = (int) charExpression;
            //48-57 0-9
            //65-90 A-Z, 97-122 a-z
            if (charInt < 48 || charInt > 57) {
                System.out.printf("Ошибка ввода уравнения: обнаружены недопустимый символ (%s)!", charExpression);
                System.out.println("");
                return false;
            }
        }

        if (countCharQuestionInLeft == 0 && countCharQuestionInRight == 0) {
            System.out.println("Ошибка ввода уравнения: в выражении отсутствует симлол '?'!");
            return false;
        }

        return true;
    }

    public static void findSolutionException(String expression) {
        //убраем пробелы
        expression = expression.replace(" ", "");

        int countCharQuestionsInLeft = 0;
        int countCharQuestionsInRight = 0;

        String term = "";
        int indexCharEquals = 0;

        //определение всех слогаемых слева
        List<String> termsInLeft = new ArrayList<String>();
        List<String> termsWithQuestionInLeft = new ArrayList<String>();
        for (int i = 0; i < expression.length(); i++) {
            char charExpression = expression.charAt(i);
            if (charExpression == '=') {
                termsInLeft.add(term);
                if(term.indexOf('?')!=-1)
                    termsWithQuestionInLeft.add(term);
                term = "";
                indexCharEquals = i;
                break;
            }
            if (charExpression == '+') {
                termsInLeft.add(term);
                term = "";
            } else {
                term = term + charExpression;
            }
        }

        //определение всех слогаемых справа
        List<String> termsInRight = new ArrayList<String>();
        List<String> termsWithQuestionInRight = new ArrayList<String>();
        for (int i = indexCharEquals + 1; i < expression.length(); i++) {
            char charExpression = expression.charAt(i);
            if (i == expression.length() - 1) {
                term = term + charExpression;
                termsInRight.add(term);
                if(term.indexOf('?')!=-1)
                    termsWithQuestionInRight.add(term);
                continue;
            }
            if (charExpression == '+') {
                termsInRight.add(term);
                term = "";
            } else {
                term = term + charExpression;
            }
        }

        int[] numbersForReplaceInLeft = new int[termsInLeft.size()];
        int[] numbersForReplaceInRight = new int[termsInRight.size()];

        List<int[]> listCombinationsLeft = new ArrayList<int[]>();
        if(termsWithQuestionInLeft.size()>0) {
            int[] startCombinationLeft = new int[termsInLeft.size()];
            listCombinationsLeft = generateCombination(termsInLeft.size(), termsInLeft.size() - 1, startCombinationLeft);
        }
        else{
            listCombinationsLeft.add(new int[] {0});
        }

        List<int[]> listCombinationsRight = new ArrayList<int[]>();
        if(termsWithQuestionInRight.size()>0) {
            int[] startCombinationRight = new int[termsInRight.size()];
            listCombinationsRight = generateCombination(termsInRight.size(), termsInRight.size() - 1, startCombinationRight);
        }
        else{
            listCombinationsRight.add(new int[] {0});
        }

        for( int[] combinationLeft:listCombinationsLeft){
            for( int[] combinationRight:listCombinationsRight){
                if (checkSolution(termsInLeft, combinationLeft, termsInRight, combinationRight)) {
                    System.out.print("Решение найдено: ");
                    System.out.println(buildingSolution(termsInLeft, combinationLeft, termsInRight, combinationRight));
                }
            }
        }
    }

    public static List<int[]> generateCombination(int countTerms, int index, int[] combination){
        List<int[]> listCombinations = new ArrayList<int[]>();
        int[] newCombination = new int[countTerms];
        for (int i=0; i<=9; i++) {
            newCombination = combination.clone();
            newCombination[index] = i;
            if (index != 0) {
                int newIndex = index - 1;
                listCombinations.addAll(generateCombination(countTerms, newIndex, newCombination));
            } else {
                listCombinations.add(newCombination);
            }
        }
        return listCombinations;
    }

    public static boolean checkSolution(List<String> termsInLeft, int[] numbersForReplaceInLeft, List<String> termsInRight, int[] numbersForReplaceInRight) {
        int sumInLeft = 0;
        int i = 0;
        for (String termInLeft : termsInLeft) {
            if (termInLeft.indexOf('?') != -1)
                termInLeft = termInLeft.replace('?', (char) ('0' + numbersForReplaceInLeft[i]));
            sumInLeft += Integer.parseInt(termInLeft);
            i++;
        }
        int sumInRight = 0;
        int j = 0;
        for (String termInRight : termsInRight) {
            if (termInRight.indexOf('?') != -1)
                termInRight = termInRight.replace('?', (char) ('0' + numbersForReplaceInRight[j]));
            sumInRight += Integer.parseInt(termInRight);
            j++;
        }

        if (sumInLeft == sumInRight) return true;
        return false;
    }

    public static String buildingSolution(List<String> termsInLeft, int[] numbersForReplaceInLeft, List<String> termsInRight, int[] numbersForReplaceInRight) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String termInLeft : termsInLeft) {
            if (i != 0) sb.append(" + ");
            sb.append(termInLeft.replace('?', (char) ('0' + numbersForReplaceInLeft[i])));
            i++;
        }
        sb.append(" = ");
        int sumInRight = 0;
        int j = 0;
        for (String termInRight : termsInRight) {
            if (j != 0) sb.append(" + ");
            sb.append(termInRight.replace('?', (char) ('0' + numbersForReplaceInRight[j])));
            j++;
        }

        return sb.toString();
    }
}
