package main;

import Utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *读取文件内容的数字去重新统一命名，不要求文件内容按照顺序放
 */
public class FileOperateNorm {
    public static void main(String[] args) {
        String dirName = args[0];
        List<File> fileList = FileUtils.getFileList(dirName);
        FileUtils.changeFileName(fileList);
    }

}

