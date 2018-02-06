package com.company;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.*;

import static com.company.Downloader.setCount;

/**
 *
 * Created by yurykotov on 04.05.17.
 */
public class Download implements Callable<String>{

    List<String> urls;
    List<String> newNames;

    long startTime;
    long downTime;
    long size;
    static long sizeAll;
    String urlOut;
    String fileOut;
    String dirOut;

    public static long getSizeAll() {
        return sizeAll;
    }

    public Download(int numThread, List<String> urlIn, List<String> fileIn, String dirIn){
        urls = urlIn;
        newNames = fileIn;
        dirOut = dirIn;
        startTime = System.currentTimeMillis();

        ExecutorService execService = Executors.newFixedThreadPool(numThread);

        Future<String> future;
        for(int i = 0; i < urlIn.size(); i++) {

            try {
                urlOut = urls.get(i);
                fileOut = newNames.get(i);
                future = execService.submit(this::call);
                future.get();
            }catch (Exception e){
                e.getMessage();
            }

        }

        execService.shutdown();
    }

    public String call() {

            System.out.println("Downloading : " + fileOut);
            downTime = 0;
            size = 0;
            try {
                URL url = new URL(urlOut);
                File file = new File(dirOut + fileOut);
                FileUtils.copyURLToFile(url, file);
                size = FileUtils.sizeOf(file);
            }catch (MalformedURLException e){
                return null;
            }catch (IOException e){
                return null;
            }
            downTime = (System.currentTimeMillis() - startTime) / 1000;
            System.out.print(fileOut + " | Download time: " + downTime + " sec.");
            System.out.println(" | Size: " + (size / 1024)+ " kb.");
            sizeAll += (size / 1024);
            System.out.println();
            setCount();
            return Thread.currentThread().getName();

    }
}
