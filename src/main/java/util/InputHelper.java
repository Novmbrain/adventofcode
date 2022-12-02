package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @className: InputHelper
 * @description: TODO
 * @author: Wenjie FU
 * @date: 01/12/2022
 **/
public class InputHelper {
    public static BufferedReader readFile(String resourceName) {
        InputStream in = InputHelper.class.getClassLoader().getResourceAsStream(resourceName);
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        return new BufferedReader(inputStreamReader);
    }
}
