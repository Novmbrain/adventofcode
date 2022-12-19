package code.day7;

import lombok.Data;
import util.InputHelper;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @className: FileSystem
 * @description: TODO
 * @author: Wenjie FU
 * @date: 19/12/2022
 */
@Data
public class FileSystem {
  private Directory rootDirectory;

  public static final int MAX_FILE_SYSTEM_SIZE = 70000000;

  /**
   * @param fileName input file should be under resources folder
   */
  public FileSystem(String fileName) throws IOException {
    rootDirectory = convertCommandsToFileSystem(InputHelper.readFile(fileName));
  }

  /**
   * @param sizeCriteria find all of the directories with a total size of at most sizeCriteria
   * @return
   */
  public int findTotalSizeOfDirectoriesBelowSizeCriteria(int sizeCriteria) {
    return scanFileSystemBasedOnSizeCriteria(rootDirectory, sizeCriteria);
  }

  public void printFileSystem() {
    scanFileSystemAndPrint(rootDirectory);
  }

  public int currentFreeSpace() {
    return MAX_FILE_SYSTEM_SIZE - rootDirectory.getDirectorySize();
  }

  public int freeFileSystemSpace(int expectedUnusedSpace) {
    return scanFileSystemAndFreeSpace(rootDirectory, expectedUnusedSpace - currentFreeSpace());
  }

  private static Directory convertCommandsToFileSystem(BufferedReader bufferedReader)
      throws IOException {
    bufferedReader.readLine();
    Directory rootDirectory = new Directory("/");

    Directory currentDirectory = rootDirectory;

    while (bufferedReader.ready()) {
      String commandLine = bufferedReader.readLine();
      currentDirectory = analysesCommand(currentDirectory, commandLine);
    }

    while (currentDirectory != rootDirectory) {
      currentDirectory.getParentDirectory().addToTotalFileSize(currentDirectory.getDirectorySize());
      currentDirectory = currentDirectory.getParentDirectory();
    }

    return rootDirectory;
  }

  private static Directory analysesCommand(Directory currentDirectory, String command) {
    String[] parsedCommand = command.split(" ");
    if (parsedCommand[0].equals("$")) {
      if (parsedCommand[1].equals("cd")) {
        if (parsedCommand[2].equals("..")) { // cd ..
          currentDirectory
              .getParentDirectory()
              .addToTotalFileSize(currentDirectory.getDirectorySize());
          currentDirectory = currentDirectory.getParentDirectory();
        } else { // cd folder name
          if (!currentDirectory.isChildFolder(parsedCommand[2])) {
            currentDirectory.addChildFolder(new Directory(parsedCommand[2], currentDirectory));
          }
          currentDirectory = currentDirectory.getChildFolderByName(parsedCommand[2]);
        }
      }
    } else if (!parsedCommand[0].equals("dir")) { // file
      currentDirectory.addToTotalFileSize(Integer.valueOf(parsedCommand[0]));
    }

    return currentDirectory;
  }

  private void scanFileSystemAndPrint(Directory directory) {
    System.out.println(directory.getDirectoryName() + " : " + directory.getDirectorySize());

    for (Directory childDirectory : directory.getMap().values()) {
      scanFileSystemAndPrint(childDirectory);
    }
  }

  private int scanFileSystemBasedOnSizeCriteria(Directory directory, int sizeCriteria) {

    int subDirectorySize = 0;
    for (Directory childDirectory : directory.getMap().values()) {
      subDirectorySize += scanFileSystemBasedOnSizeCriteria(childDirectory, sizeCriteria);
    }

    return directory.getDirectorySize() <= sizeCriteria
        ? directory.getDirectorySize() + subDirectorySize
        : subDirectorySize;
  }

  private int scanFileSystemAndFreeSpace(Directory directory, int expectedUnusedSpace) {

    int smallestDirectorySize = MAX_FILE_SYSTEM_SIZE;

    for (Directory childDirectory : directory.getMap().values()) {
      int smallestChildDirectorySize =
          scanFileSystemAndFreeSpace(childDirectory, expectedUnusedSpace);

      if (smallestChildDirectorySize == -1) continue;

      smallestDirectorySize = Math.min(smallestDirectorySize, smallestChildDirectorySize);
    }

    if (directory.getDirectorySize() >= expectedUnusedSpace) {
      return Math.min(directory.getDirectorySize(), smallestDirectorySize);
    } else {
      return smallestDirectorySize == MAX_FILE_SYSTEM_SIZE ? -1 : smallestDirectorySize;
    }
  }
}
