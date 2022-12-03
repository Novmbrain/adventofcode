package code;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import util.InputHelper;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @className: Day2RockPaperScissors
 * @description: TODO
 * @author: Wenjie FU
 * @date: 02/12/2022
 */
public class Day2RockPaperScissors {
  private static final BufferedReader bufferedReader = InputHelper.readFile("day2input.txt");

  private static final ImmutableMap<String, Integer> shapeToScorePart1 =
      ImmutableMap.of("X", 1, "Y", 2, "Z", 3);

  private static final ImmutableMap<String, ImmutableMap<String, Integer>> myShapeToScore =
      ImmutableMap.of(
          "X", // Rock
          ImmutableMap.<String, Integer>of("A", 3, "B", 0, "C", 6),
          "Y", // Paper
          ImmutableMap.<String, Integer>of("A", 6, "B", 3, "C", 0),
          "Z", // Scissors
          ImmutableMap.<String, Integer>of("A", 0, "B", 6, "C", 3));

  /**
   * 12679
   *
   * @throws IOException
   */
  @Test
  public void part1() throws IOException {

    int count = 0;
    while (bufferedReader.ready()) {
      String[] plays = bufferedReader.readLine().split(" ");
      count += shapeToScorePart1.get(plays[1]);
      count += myShapeToScore.get(plays[1]).get(plays[0]);
    }

    System.out.println("Part1 result: " + count);
  }

  private static final ImmutableMap<String, Integer> endToScore =
      ImmutableMap.of("X", 0, "Y", 3, "Z", 6);

  private static final ImmutableMap<String, Integer> shapeToScorePart2 =
      ImmutableMap.of("A", 1, "B", 2, "C", 3);

  private static final ImmutableMap<String, ImmutableMap<String, String>>
      expectedEndToExpectedShape =
          ImmutableMap.of(
              "X", // Lose
              ImmutableMap.<String, String>of("A", "C", "B", "A", "C", "B"),
              "Y", // Draw
              ImmutableMap.<String, String>of("A", "A", "B", "B", "C", "C"),
              "Z", // Win
              ImmutableMap.<String, String>of("A", "B", "B", "C", "C", "A"));

  /**
   * 14470
   *
   * @throws IOException
   */
  @Test
  public void part2() throws IOException {
    int count = 0;

    while (bufferedReader.ready()) {
      String[] plays = bufferedReader.readLine().split(" ");
      count += endToScore.get(plays[1]);
      count += shapeToScorePart2.get(expectedEndToExpectedShape.get(plays[1]).get(plays[0]));
    }

    System.out.println("Part2 result: " + count);
  }
}
