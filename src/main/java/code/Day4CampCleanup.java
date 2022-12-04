package code;

import org.junit.jupiter.api.Test;
import util.InputHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: Day4CampCleanup
 * @description: TODO
 * @author: Wenjie FU
 * @date: 04/12/2022
 */
public class Day4CampCleanup {
  BufferedReader bufferedReader = InputHelper.readFile("day4input.txt");

  @Test
  public void part1() throws IOException {
    int count = 0;
    while (bufferedReader.ready()) {
      String currentLine = bufferedReader.readLine();
      List<List<Integer>> pairOfSection = converInputToList(currentLine);

      if (ifOneRangeContainsAnother(pairOfSection)) count++;
    }
    System.out.println("Part1 result: " + count);
  }

  @Test
  public void part2() throws IOException {
    int count = 0;
    while (bufferedReader.ready()) {
      String currentLine = bufferedReader.readLine();
      List<List<Integer>> rangeOfSections = converInputToList(currentLine);

      if (ifOneRangeOverlapsWithAnother(rangeOfSections)) count++;
    }
    System.out.println("Part2 result: " + count);
  }

  private static List<List<Integer>> converInputToList(String line) {
    return Arrays.stream(line.split(","))
        .map(
            range ->
                Arrays.stream(range.split("-"))
                    .map(boundary -> Integer.valueOf(boundary))
                    .collect(Collectors.toList()))
        .collect(Collectors.toList());
  }

  private static boolean ifOneRangeContainsAnother(List<List<Integer>> rangeOfSections) {
    List<Integer> firstRangeOfSection = rangeOfSections.get(0);
    List<Integer> SecondRangeOfSection = rangeOfSections.get(1);

    return (firstRangeOfSection.get(0) <= SecondRangeOfSection.get(0)
            && firstRangeOfSection.get(1) >= SecondRangeOfSection.get(1))
        || (firstRangeOfSection.get(0) >= SecondRangeOfSection.get(0)
            && firstRangeOfSection.get(1) <= SecondRangeOfSection.get(1));
  }

  private static boolean ifOneRangeOverlapsWithAnother(List<List<Integer>> rangeOfSections) {
    List<Integer> firstRangeOfSection = rangeOfSections.get(0);
    List<Integer> secondRangeOfSection = rangeOfSections.get(1);

    return ifOneRangeContainsAnother(rangeOfSections)
        || (firstRangeOfSection.get(0) >= secondRangeOfSection.get(0)
            && firstRangeOfSection.get(0) <= secondRangeOfSection.get(1))
        || (firstRangeOfSection.get(1) <= secondRangeOfSection.get(1)
            && firstRangeOfSection.get(1) >= secondRangeOfSection.get(0));
  }
}
