package code.day7;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: Folder
 * @description: TODO
 * @author: Wenjie FU
 * @date: 19/12/2022
 */
@Data
public class Directory {

  private String directoryName;
  private int directorySize = 0;
  private Directory parentDirectory;
  private Map<String, Directory> map = new HashMap<>();

  public Directory(String directoryName) {
    this.directoryName = directoryName;
  }

  public Directory(int directorySize) {
    this.directorySize = directorySize;
  }

  public Directory(String directoryName, Directory parentDirectory) {
    this.directoryName = directoryName;
    this.parentDirectory = parentDirectory;
  }

  public boolean isChildFolder(String directoryName) {
    return map.containsKey(directoryName);
  }

  public Directory getChildFolderByName(String directoryName) {
    return map.get(directoryName);
  }

  public void addChildFolder(Directory directory) {
    map.put(directory.getDirectoryName(), directory);
  }

  public int addToTotalFileSize(int totalFileSize) {
    return this.directorySize += totalFileSize;
  }
}
