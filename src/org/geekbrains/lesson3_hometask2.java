package org.geekbrains;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class lesson3_hometask2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Задача: 2 - Дан массив целых чисел (List<Integer) удалить из него все чётные числа. Посмотрите на метод removeAll (можно сохранить все четные значения в другой список и передать его в removeAll или используйте итератор");

        System.out.print("Введите количество чисел: ");
        String stringCount = in.nextLine();
        while (!isDigit(stringCount)) {
            System.out.print("Введите количество чисел: ");
            stringCount = in.nextLine();
        }
        in.close();

        int count = Integer.parseInt(stringCount);

        List<Integer> listNumbers = generateRandomArrayIntNumbers(count);
        System.out.print("Список чисел: ");
        print(listNumbers);

        List<Integer> listEvenNumbers = getEvenNumbersByArray(listNumbers);
        System.out.print("Список чётных чисел: ");
        print(listEvenNumbers);

        listNumbers.removeAll(listEvenNumbers);
        System.out.print("Используем removeAll для удаления чётных чисел: ");
        print(listNumbers);
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static List<Integer> generateRandomArrayIntNumbers(int countAll) {
        List<Integer> list = new ArrayList<Integer>();
        if (countAll <= 0) return list;

        while (list.size() < countAll) {
            list.add((int) (Math.random() * 100));
        }

        return list;
    }

    private static List<Integer> getEvenNumbersByArray(List<Integer> listNumbers) {
        List<Integer> listEvenNumbers = new ArrayList<Integer>();
        for (Integer i : listNumbers) {
            if (i % 2 == 0) {
                listEvenNumbers.add(i);
            }
        }
        return listEvenNumbers;
    }

    private static void print(List<Integer> listNumbers) {
        for (Integer i : listNumbers) {
            System.out.print(i.toString() + " ");
        }
        System.out.println("");
    }
}
