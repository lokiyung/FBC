package main;

import Utils.FileUtils;

import java.io.File;
import java.util.List;

/**
 * 1，2，3，4，5这样按顺序改名，要求文件的内容是按顺序放的
 */
public class FileOperateSep {
    public static void main(String[] args) {
        String dirName = args[0];
        List<File> fileList = FileUtils.getFileList(dirName);
        FileUtils.changeFileNameBegan(fileList);

    }

}
