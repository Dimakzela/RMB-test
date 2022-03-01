package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class FileSystem {

    private static final Map<String, List<String>> map = new HashMap<>();

    public static List<String> findDuplicate(File[] files) throws IOException {

        if(files == null || files.length == 0)
            return Collections.emptyList();

        for(File file: files) {
            if(file.isDirectory()) {
                if(file.list().length > 0) {
                    findDuplicate(listFiles(file.getPath()));
                }
            }else {
                InputStream inputStream = new FileInputStream(file);
                String content = readFromInputStream(inputStream).trim();
                if(map.containsKey(content)) {
                    List<String> duplicateFiles = map.get(content);
                    duplicateFiles.add(file.getName());
                    map.put(content, duplicateFiles);
                }else {
                    List<String> list = new ArrayList<>();
                    list.add(file.getName());
                    map.put(content, list);
                }
            }
        }
        return toList(map);
    }

    private static List<String> toList(Map<String, List<String>> map) {
        List<String> duplicate = new ArrayList<>();
        for(List<String> strings: map.values()) {
            if(strings.size() > 1) {
                duplicate.addAll(strings);
            }
        }
        return duplicate;
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public static File[] listFiles(String dir) {
        return new File(dir).listFiles();
    }
}
