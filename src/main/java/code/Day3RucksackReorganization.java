package code;

import org.junit.jupiter.api.Test;
import util.InputHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @className: Day3RucksackReorganization
 * @description: TODO
 * @author: Wenjie FU
 * @date: 03/12/2022
 */
public class Day3RucksackReorganization {
  private static final BufferedReader bufferedReader = InputHelper.readFile("day3input.txt");

  /**
   * 7597
   * @throws IOException
   */
  @Test
  public void part1() throws IOException {
    List<Character> sharedItems = new LinkedList<>();

    while (bufferedReader.ready()) {
      String currentLine = bufferedReader.readLine();

      String firstHalfString = currentLine.substring(0, currentLine.length() / 2);
      String secondHalfString = currentLine.substring(currentLine.length() / 2);

      Set<Character> set =
          firstHalfString.chars().mapToObj(e -> (char) e).collect(Collectors.toSet());

      Character sameItem =
          secondHalfString
              .chars()
              .mapToObj(e -> (char) e)
              .filter(item -> set.contains(item))
              .findFirst()
              .get();

      sharedItems.add(sameItem);
    }

    int sum =
        sharedItems.stream()
            .map(item -> Character.isLowerCase(item) ? item - 'a' + 1 : item - 'A' + 27)
            .mapToInt(mappedItem -> mappedItem)
            .sum();
  }

  @Test
  public void part2() throws IOException {
    List<Character> sharedItems = new LinkedList<>();

    while (bufferedReader.ready()) {
      String line1 = bufferedReader.readLine();
      String line2 = bufferedReader.readLine();
      String line3 = bufferedReader.readLine();

      Set<Character> set1 = line1.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
      Set<Character> set2 = line2.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());

      Character sameItem =
          line3
              .chars()
              .mapToObj(e -> (char) e)
              .filter(item -> set1.contains(item))
              .filter(item -> set2.contains(item))
              .findFirst()
              .get();

      sharedItems.add(sameItem);
    }

    int sum =
        sharedItems.stream()
            .map(item -> Character.isLowerCase(item) ? item - 'a' + 1 : item - 'A' + 27)
            .mapToInt(mappedItem -> mappedItem)
            .sum();
  }
}
