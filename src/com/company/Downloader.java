package com.company;


import java.util.HashMap;
import java.util.List;


/**
 * Created by yurykotov on 03.05.17.
 */
public class Downloader {

    private HashMap<String, String> argsMap = null;
    public static int count = 0;

    public static int getCount() {
        return count;
    }
    public static void setCount(){
        count++;
    }

    public Downloader(String[] inArgs) throws Exception{

        System.out.println("Инициализация...");

            // конвертация входного массива
            argsMap = new ArgsConvert().convert(inArgs);
            if (argsMap == null){
                System.out.println("Введите корректные агрументы");
                return;
            }

            // достаем директорию
            String tmp = argsMap.get("KEY_DIR");
            if (tmp == null) {
                tmp = ".";
                argsMap.put("KEY_DIR", tmp);
            }

            // достаем links.txt
            tmp = argsMap.get("KEY_FILE");
            if (tmp == null) {
                System.out.println("Ссылки не найдены!");
                return;
            }

            // находим новые имена файлов
            List<String> newNames = new TxtToUrls().getNewNames(argsMap);

            // забираем ссылки из файла
            List<String> urls = new TxtToUrls().getUrls(argsMap);

            // достаем кол-во потоков
            int numThread = 3;
            if (argsMap.containsKey("KEY_THREAD")) {
                numThread = Integer.parseInt(argsMap.get("KEY_THREAD"));
            }

            System.out.println("...ок");
            System.out.println();

            // побежали
            Download dl = new Download(numThread, urls, newNames, argsMap.get("KEY_DIR"));

    }



}
