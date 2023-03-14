package com.taogen.app;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Taogen
 */
public class ExportSourceCode {
    private static final List<String> EXPORT_SOURCE_CODE_SUFFIXES =
            Arrays.asList("java", "js", "vue");
    /**
     * 1页word=25行代码
     */
    private static final Integer MAX_EXPORT_LINES = 1250;

    public static void main(String[] args) throws IOException {
        String sourceCodeDirPath = "D:\\temp\\code\\src";
        String outputDirPath = "D:/temp/code";
        String outputFileName = String.format("code-by-stack--%s.txt", System.currentTimeMillis());
        exportCodeDepthFirst(sourceCodeDirPath, outputDirPath, outputFileName, EXPORT_SOURCE_CODE_SUFFIXES, MAX_EXPORT_LINES);
        // exportCodeBreadthFirst(sourceCodeDirPath, outputDirPath, outputFileName, exportSourceCodeSuffixes, maxExportLines);
    }

    public static void exportCodeDepthFirst(String sourceCodeDirPath,
                                            String outputDirPath,
                                            String outputFileName,
                                            List<String> exportSourceCodeSuffixes,
                                            Integer maxExportLines) throws IOException {
        System.out.println("To export source code using depth first...");
        File sourceCodeDir = new File(sourceCodeDirPath);
        File outputDir = new File(outputDirPath);
        if (!sourceCodeDir.exists() || !sourceCodeDir.isDirectory()) {
            System.out.println("Source code directory does not exist.");
            return;
        }
        if (!outputDir.exists() || !outputDir.isDirectory()) {
            outputDir.mkdirs();
        }
        Deque<File> dirDeque = new ArrayDeque<>();
        Set<File> pushedFileSet = new HashSet<>();
        dirDeque.push(sourceCodeDir);
        Integer totalLineCount = 0;
        while (!dirDeque.isEmpty()) {
            if (maxExportLines != null && totalLineCount > maxExportLines) {
                break;
            }
            File currDir = dirDeque.peek();
            File[] files = currDir.listFiles();
            if (files != null) {
                List<File> subDirList = Arrays.stream(files)
                        .filter(item -> item.isDirectory() && !pushedFileSet.contains(item))
                        .collect(Collectors.toList());
                if (subDirList != null && !subDirList.isEmpty()) {
                    Collections.sort(subDirList, Comparator.comparing(File::getName, String::compareToIgnoreCase).reversed());
                    for (File file : subDirList) {
                        dirDeque.push(file);
                        pushedFileSet.add(file);
                        System.out.println("push dir: " + file.getName());
                    }
                } else {
                    File dir = dirDeque.poll();
                    System.out.println("pop dir: " + dir.getName());
                    File[] children = dir.listFiles();
                    if (children != null) {
                        for (File file : children) {
                            if (file.isFile()) {
                                totalLineCount += writeFile(file, outputDirPath, outputFileName, exportSourceCodeSuffixes);
                                if (maxExportLines != null && totalLineCount > maxExportLines) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Export source code done. Total line count: " + totalLineCount);
        System.out.println("Output file path: " + outputDirPath + File.separator + outputFileName);
    }

    public static void exportCodeBreadthFirst(String sourceCodeDirPath,
                                              String outputDirPath,
                                              String outputFileName,
                                              List<String> exportSourceCodeSuffixes,
                                              Integer maxExportLines) throws IOException {
        System.out.println("To export source code using breadth first...");
        Deque<File> fileDeque = new ArrayDeque<>();
        File sourceCodeDir = new File(sourceCodeDirPath);
        File outputDir = new File(outputDirPath);
        if (!sourceCodeDir.exists() || !sourceCodeDir.isDirectory()) {
            System.out.println("Source code directory does not exist.");
            return;
        }
        if (!outputDir.exists() || !outputDir.isDirectory()) {
            outputDir.mkdirs();
        }
        fileDeque.add(sourceCodeDir);
        Integer totalLineCount = 0;
        while (!fileDeque.isEmpty()) {
            if (maxExportLines != null && totalLineCount > maxExportLines) {
                break;
            }
            File curr = fileDeque.remove();
            File[] files = curr.listFiles();
            if (files != null) {
                List<File> children = new ArrayList<>(Arrays.asList(files));
                Collections.sort(children, Comparator.comparing(File::getName));
                for (File file : children) {
                    if (file.isDirectory()) {
                        fileDeque.add(file);
                    } else {
                        totalLineCount += writeFile(file, outputDirPath, outputFileName, exportSourceCodeSuffixes);
                        if (maxExportLines != null && totalLineCount > maxExportLines) {
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("Export source code done. Total line count: " + totalLineCount);
        System.out.println("Output file path: " + outputDirPath + File.separator + outputFileName);
    }

    private static Integer writeFile(File curr,
                                     String outputDirPath,
                                     String outputFileName,
                                     List<String> exportSourceCodeSuffixes) throws IOException {
        if (curr == null || !curr.isFile()) {
            return 0;
        }
        Integer totalLineCount = 0;
        if (exportSourceCodeSuffixes.isEmpty() ||
                exportSourceCodeSuffixes.contains(
                        curr.getName().substring(curr.getName().lastIndexOf(".") + 1))) {
            System.out.println("Processing file: " + curr.getAbsolutePath());
            String outputFilePath = new StringBuilder()
                    .append(outputDirPath)
                    .append(File.separator)
                    .append(outputFileName)
                    .toString();
            try (BufferedReader fileReader = new BufferedReader(new FileReader(curr));
                 BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outputFilePath, true))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    String s = line.trim();
                    if (s.isEmpty() || s.startsWith("/") || s.startsWith("*") || s.startsWith("<!--")) {
                        continue;
                    }
                    fileWriter.write(line);
                    fileWriter.newLine();
                    totalLineCount++;
                }
            }
        }
        return totalLineCount;
    }
}
