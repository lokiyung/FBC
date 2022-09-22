package Utils;

import java.io.File;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {
//    /**
//     *
//     * @param args 漫画名称，空格分隔,不指定的话修改所有漫画
//     * 最后一项flag 必须指定，如果资源是按照顺序放进去的，只是名字有问题，那就指定为0
//     *            如果资源是乱序放进去的，那就指定为1
//     */
//    public static void main(String[] args) {
//
//
//
//                if (flag=="0") {
//                    for (String comic : comiclists) {
//                        List<File> fileList = getFileList(comic);
//                        changeFileNameBegan(fileList);
//                    }
//                }else  if (flag=="1"){
//                    for (String comic : comiclists) {
//                        List<File> fileList = getFileList(comic);
//                        changeFileName(fileList);
//                    }
//                }else {
//                    System.out.println("flag参数非法");
//                    System.exit(0);
//                }
//
//            }
////        List<File> fileList = getFileList("");
////        changeFileName(fileList);
//
//    }


    /**
     * 获取文件列表
     * @param Dir
     * @return
     */
    public static List<File> getFileList(String Dir) {
        String dirName = null;
        if (Dir == null || Dir.length() == 0) {
            dirName = new String("D:" + File.separator + "comichouse");
            System.out.println("未指定路径，使用默认路径/home/leonkennedy/comcichouse");
        } else {
            dirName = new String(Dir);
            System.out.println("指定的路径为: "+dirName);
        }
        File dir = new File(dirName);
        List<File> fileList = null;
        if (dir.isDirectory()) {
            File[] listFiles = dir.listFiles();
            List<File> files = Arrays.asList(listFiles);
//            System.out.println(files.toString());
            fileList =  files;

        }
        return fileList;
    }

    public static ArrayList<String> getAb(List<File> filelist) {
        ArrayList<String> fileNameList = new ArrayList<>();
        for (File file : filelist) {
            String name = file.getAbsolutePath();
            fileNameList.add(name);
        }
        return fileNameList;
    }


        /**
         * 截取字符串中的数字
         * @param content
         * @return
         */
        public static List<String> getNumberList(String content){
            List<String> numList = new ArrayList<>();
            Pattern p = Pattern.compile("\\d{1,}");
            Matcher m = p.matcher(content);
            while(m.find()) {
                numList.add(m.group());
            }
            return numList;
        }

    /**
     *没用到，对文件对象排序的
     * @param filelist
     */
    public static void orderByName(List<File> filelist) {

        for (File file : filelist) {
            File[] listFiles = file.listFiles();
            List fileList = Arrays.asList(listFiles);
            Collections.sort(fileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    int n1 = Integer.parseInt(getNumberList(o1.getName()).get(0));
                    int n2 = Integer.parseInt(getNumberList(o2.getName()).get(0));
                    return n1-n2;
                }
            });
            fileList.forEach(System.out::println);

        }


    }

    /**
     * 获取漫画名字的列表，暂时没用到
     * @param filelist
     * @return
     */
    public static ArrayList<String> getFileName(List<File> filelist) {
        ArrayList<String> fileNameList = new ArrayList<>();
        for (File file : filelist) {
            String name = file.getName();
            fileNameList.add(name);
        }
        return fileNameList;
    }

    /**
     * 1，2，3，4，5这样按顺序改名，要求文件的内容是按顺序放的
     * @param fileList
     */
    public static void changeFileNameBegan(List<File> fileList) {
        //读取文件类型，暂时不需要
//        try {
//            Properties p = new Properties();
//
//            InputStream fs = new FileInputStream("src/main/resources/FileType");
//            p.load(fs);
//            String type = p.getProperty("type");
//            List<String> fileType = Arrays.asList(type.split(","));
            for (File file : fileList) {
                int index = 1;
                String absolutePath = file.getAbsolutePath();
                String name = file.getName();
                for (File listFile : file.listFiles()) {
                    String split = listFile.getName();
                    String[] split1 = split.split("\\.");
                    System.out.println(split);
                    String suffix = split1[1];
                    String page = String.valueOf(index);

                    File newFile = new File(absolutePath + File.separator + name + "-" + page + "." + suffix);
                    System.out.println(newFile);
                    listFile.renameTo(newFile);
                    index++;
                }
            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

    /**
     * 读取文件内容的数字去重新统一命名，不要求文件内容按照顺序放
     * @param fileList
     */
    public static void changeFileName(List<File> fileList) {
            for (File file : fileList) {
                String absolutePath = file.getAbsolutePath();
                String name = file.getName();
                for (File listFile : file.listFiles()) {
                    String split = listFile.getName();
                    String index = getNumberList(split).get(0);
                    String[] split1 = split.split("\\.");
                    String suffix = split1[1];
                    System.out.println(split+"  "+index+"  "+suffix);

                    File newFile = new File(absolutePath + File.separator + name + "_" + index + "." + suffix);
//                    System.out.println(newFile);
                    listFile.renameTo(newFile);

                }
            }



    }


}
