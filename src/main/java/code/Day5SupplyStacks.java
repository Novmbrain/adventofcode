package code;

import org.junit.jupiter.api.Test;
import util.InputHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * @className: Day5SupplyStacks
 * @description: TODO
 * @author: Wenjie FU
 * @date: 05/12/2022
 */
public class Day5SupplyStacks {
  BufferedReader bufferedReader = InputHelper.readFile("day5Input.txt");

  private static List<Stack<Character>> intialization() {
    List<Stack<Character>> list = new ArrayList<>();

    Stack<Character> stack1 = new Stack<>();
    stack1.addAll(Arrays.asList('F', 'C', 'P', 'G', 'Q', 'R'));
    Stack<Character> stack2 = new Stack<>();
    stack2.addAll(Arrays.asList('W', 'T', 'C', 'P'));
    Stack<Character> stack3 = new Stack<>();
    stack3.addAll(Arrays.asList('B', 'H', 'P', 'M', 'C'));
    Stack<Character> stack4 = new Stack<>();
    stack4.addAll(Arrays.asList('L', 'T', 'Q', 'S', 'M', 'P', 'R'));
    Stack<Character> stack5 = new Stack<>();
    stack5.addAll(Arrays.asList('P', 'H', 'J', 'Z', 'V', 'G', 'N'));
    Stack<Character> stack6 = new Stack<>();
    stack6.addAll(Arrays.asList('D', 'P', 'J'));
    Stack<Character> stack7 = new Stack<>();
    stack7.addAll(Arrays.asList('L', 'G', 'P', 'Z', 'F', 'J', 'T', 'R'));
    Stack<Character> stack8 = new Stack<>();
    stack8.addAll(Arrays.asList('N', 'L', 'H', 'C', 'F', 'P', 'T', 'J'));
    Stack<Character> stack9 = new Stack<>();
    stack9.addAll(Arrays.asList('G', 'V', 'Z', 'Q', 'H', 'T', 'C', 'W'));

    list.addAll(
        Arrays.asList(stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8, stack9));
    return list;
  }

  @Test
  public void part1() throws IOException {

    List<Stack<Character>> crates = intialization();

    while (bufferedReader.ready()) {
      Integer[] commands = extractDigitFromString(bufferedReader.readLine());

      Stack<Character> from = crates.get(commands[1] - 1);
      Stack<Character> to = crates.get(commands[2] - 1);

      for (int i = 0; i < commands[0]; i++) {
        to.push(from.pop());
      }
    }

    crates.stream()
        .filter(stack -> stack.size() != 0)
        .forEach(stack -> System.out.print(stack.size() == 0 ? " " : stack.peek()));
  }

  @Test
  public void part2() throws IOException {
    List<Stack<Character>> crates = intialization();

    while (bufferedReader.ready()) {
      Integer[] commands = extractDigitFromString(bufferedReader.readLine());

      Stack<Character> from = crates.get(commands[1] - 1);
      Stack<Character> to = crates.get(commands[2] - 1);

      Stack<Character> temp = new Stack<>();
      for (int i = 0; i < commands[0]; i++) {
        temp.push(from.pop());
      }

      while (!temp.isEmpty()) {
        to.push(temp.pop());
      }
    }

    crates.stream()
        .filter(stack -> stack.size() != 0)
        .forEach(stack -> System.out.print(stack.size() == 0 ? " " : stack.peek()));
  }

  private static Integer[] extractDigitFromString(String input) {
    return Arrays.stream(input.split(" "))
        .filter(s -> Pattern.compile("[0-9]*").matcher(s).matches())
        .map(s -> Integer.valueOf(s))
        .toArray(Integer[]::new);
  }
}
