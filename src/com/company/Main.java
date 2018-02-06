package com.company;

import static com.company.Download.getSizeAll;
import static com.company.Downloader.getCount;
import static com.company.TxtToUrls.getUrls;

public class Main {

    public static void main(String[] args)  {

        long startTime = System.currentTimeMillis();

        try {
            Downloader down = new Downloader(args);
        }catch (Exception E){
            System.out.println("Проверьте аргументы командной строки!");
            return;
        }

        int count = getCount();
        int urls = getUrls();
        double size = getSizeAll();
        long allTime = (System.currentTimeMillis() - startTime) / 1000;
        int timeMin = (int)allTime / 60;
        int timeSec = (int)allTime % 60;
        double percent = (((double)count / (double)urls) * 100);

        System.out.printf("Завершено: %.1f%%\n", percent);
        System.out.printf("Загружено: %d файла(ов) | ", count);
        System.out.printf("%.1f mb.\n", (double)getSizeAll() / 1024);
        System.out.println("Общее время: " + timeMin + " минут(ы) " + timeSec + " секунд(ы)");
        System.out.printf("Средняя скорость: %.1f kb/sec", (size / allTime));
    }
}
