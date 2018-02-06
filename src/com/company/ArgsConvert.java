package com.company;

import java.util.HashMap;

/**
 * Created by yurykotov on 03.05.17.
 */
public class ArgsConvert {

    public HashMap<String, String> convert(String [] args) {

        HashMap<String, String> argsMap = new HashMap<String, String>();

        if (args.length != 3) {
            return null;
        }

        argsMap.put("KEY_THREAD", args[0]);
        argsMap.put("KEY_DIR", args[1]);
        argsMap.put("KEY_FILE", args[2]);

        return argsMap;
    }
}

