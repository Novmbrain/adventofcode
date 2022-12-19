package code;

import org.junit.jupiter.api.Test;
import util.InputHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: Day6TuningTrouble
 * @description: TODO
 * @author: Wenjie FU
 * @date: 06/12/2022
 */
public class Day6TuningTrouble {
  BufferedReader bufferedReader = InputHelper.readFile("day6Input.txt");

  @Test
  public void part1() throws IOException {
    char[] input = bufferedReader.readLine().toCharArray();
    Map<Character, Integer> map = new HashMap<>();

    for (int i = 0 ; i <= 3; i++) {
      map.put(input[i], map.getOrDefault(input[i], 0) + 1);
    }

    for (int i = 4; i < input.length - 1; i++) {
      map.put(input[i], map.getOrDefault(input[i], 0) + 1);

      if (map.get(input[i - 4]) > 1) {
        map.replace(input[i - 4], map.get(input[i - 4]) - 1);
      } else {
        map.remove(input[i - 4]);
      }

      if (map.size() == 4) {
        System.out.println(i + 1);
         break;
      }
    }
  }

  @Test
  public void part2() throws IOException {
    char[] input = bufferedReader.readLine().toCharArray();
    Map<Character, Integer> map = new HashMap<>();

    for (int i = 0 ; i <= 13; i++) {
      map.put(input[i], map.getOrDefault(input[i], 0) + 1);
    }

    for (int i = 14; i < input.length - 1; i++) {
      map.put(input[i], map.getOrDefault(input[i], 0) + 1);

      if (map.get(input[i - 14]) > 1) {
        map.replace(input[i - 14], map.get(input[i - 14]) - 1);
      } else {
        map.remove(input[i - 14]);
      }

      if (map.size() == 14) {
        System.out.println(i + 1);
        break;
      }
    }
  }

}
