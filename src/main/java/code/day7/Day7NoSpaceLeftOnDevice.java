package code.day7;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @className: Day7NoSpaceLeftOnDevice
 * @description: TODO
 * @author: Wenjie FU
 * @date: 16/12/2022
 */
public class Day7NoSpaceLeftOnDevice {

  @Test
  public void solution1() throws IOException {
    FileSystem fileSystem = new FileSystem("day7Input.txt");
    fileSystem.printFileSystem();
    System.out.println(fileSystem.findTotalSizeOfDirectoriesBelowSizeCriteria(100000));
  }

  @Test
  public void solution2() throws IOException {
    FileSystem fileSystem = new FileSystem("day7Input.txt");
    System.out.println(fileSystem.freeFileSystemSpace(30000000));
  }
}
