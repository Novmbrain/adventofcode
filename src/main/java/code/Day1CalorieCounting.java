package code;

import com.google.common.collect.MinMaxPriorityQueue;
import org.junit.jupiter.api.Test;
import util.InputHelper;

import java.io.*;
import java.util.Arrays;
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

    while (bufferedReader.ready()) {
      String currentLine = bufferedReader.readLine();
      if (currentLine.equals("")) {
        max = Math.max(max, count);
        count = 0;
      } else {
        count += Integer.valueOf(currentLine);
      }
    }

    System.out.println(max);
  }

  /**
   * 200116
   * @throws IOException
   */
  @Test
  public void CalorieCountingPart2() throws IOException {

    MinMaxPriorityQueue<Integer> maxHeap =
        MinMaxPriorityQueue.<Integer>orderedBy((n1, n2) -> n2 - n1).maximumSize(3).create();

    int count = 0;

    while (bufferedReader.ready()) {
      String currentLine = bufferedReader.readLine();
      if (currentLine.equals("")) {
        maxHeap.add(count);
        count = 0;
      } else {
        count += Integer.valueOf(currentLine);
      }
    }

    System.out.println(maxHeap.stream().mapToInt(number -> number.intValue()).sum());
  }
}
