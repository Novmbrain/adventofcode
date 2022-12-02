package code;

import org.junit.jupiter.api.Test;
import util.InputHelper;

import java.io.*;
import java.util.OptionalLong;
import java.util.PriorityQueue;

/**
 * @className: CalorieCounting
 * @description: TODO
 * @author: Wenjie FU
 * @date: 01/12/2022
 */
public class Day1CalorieCounting {

  private static final BufferedReader bufferedReader = InputHelper.readFile("day1Input1.txt");

  @Test
  public void CalorieCountingPart1Solution1() throws IOException {
    int max = Integer.MIN_VALUE;
    int count = 0;
    String currentLine = "";
    while ((currentLine = bufferedReader.readLine()) != null) {
      if (currentLine.equals("")) {
        max = Math.max(max, count);
        count = 0;
      } else {
        count += Integer.valueOf(currentLine);
      }
    }

    System.out.println(max);
  }

  @Test
  public void CalorieCountingPart2() throws IOException {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(3, (n1, n2) -> n2 - n1);

    int count = 0;
    String currentLine = "";
    while ((currentLine = bufferedReader.readLine()) != null) {
      if (currentLine.equals("")) {
        priorityQueue.add(count);
        count = 0;
      } else {
        count += Integer.valueOf(currentLine);
      }
    }

    int result = 0;
    for (int i = 0; i < 3; i++) {
      result += priorityQueue.remove();
    }
    System.out.println(result);
  }
}
