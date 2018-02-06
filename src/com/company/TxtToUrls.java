package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yurykotov on 03.05.17.
 */
public class TxtToUrls {

    static List<String> urls;
    static List<String> names;

    public static int getUrls() {
        return urls.size();
    }

    public List<String> getUrls(HashMap<String, String> map) throws IOException{
        int tmp = 0;
        urls = Files.readAllLines(Paths.get(map.get("KEY_FILE")), StandardCharsets.UTF_8);

        for (int i = 0; i < urls.size(); i++){
            StringBuffer sb = new StringBuffer(urls.get(i));
            tmp = sb.indexOf(" ");
            urls.set(i, sb.substring(0, tmp));
        }

        HashSet<String> hashSet = new HashSet<>();
        for(int i = 0; i < urls.size(); i++){
            hashSet.add(urls.get(i));
        }
        urls.clear();
        urls.addAll(hashSet);
        return urls;
    }

    public List<String> getNewNames(HashMap<String, String> map) throws IOException{
        int tmp = 0;
        names = Files.readAllLines(Paths.get(map.get("KEY_FILE")), StandardCharsets.UTF_8);

        for (int i = 0; i < names.size(); i++){
            StringBuffer sb = new StringBuffer(names.get(i));
            tmp = sb.indexOf(" ") + 1;
            names.set(i, sb.substring(tmp, sb.length()));
        }

        return names;
    }

}
