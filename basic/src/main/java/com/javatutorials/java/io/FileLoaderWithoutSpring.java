package com.javatutorials.java.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 1: 使用ClassLoader读取资源文件
 * -- ClassLoader classLoader = AAA.class.getClassLoader(); classLoader.getResourceAsStream("yourfile.txt");
 * 2: 使用Thread.currentThread().getContextClassLoader()读取资源文件.
 * -- 这种方法适用于在不同的上下文中，例如在单元测试或不同的线程中获取资源文件。最后还是用 classLoader.getResourceAsStream("yourfile.txt");
 * 3：使用Class.getResourceAsStream读取资源文件
 * -- 可以使用类的getResourceAsStream方法读取同包下或者以绝对路径指定的资源文件。
 * -- InputStream inputStream = AAA.class.getResourceAsStream("/yourfile.txt")
 *
 */
public class FileLoaderWithoutSpring {

    @Test
    public void testLoadFileFromResources() {
        String fileContent = loadFileFromResources("system_log.xml");
        System.out.println(fileContent);
    }

    public static String readFileFromResources(String path,String fileName) {
        ClassLoader classLoader = FileLoaderWithoutSpring.class.getClassLoader();
        try (InputStream inputStream1 = classLoader.getResourceAsStream(path+fileName)) {
            if (inputStream1 == null) {
                System.out.println("File not found!");
                return "";
            }
            // 读取文件内容
            String content = new String(inputStream1.readAllBytes());
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 使用ClassLoader加载资源文件
    // TODO: getResourceAsStream如果要读取package里头的文件，则不能以/作为开头字符，不然读取不到文件
    public static String loadFileFromResources(String fileName) {
        // 使用ClassLoader加载资源文件
        ClassLoader classLoader = FileLoaderWithoutSpring.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("com/javatutorials/xml/"+fileName);

        // 读取文件内容
        StringBuffer result = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //另外一种读取方式
        try (InputStream inputStream1 = classLoader.getResourceAsStream("com/javatutorials/xml/"+fileName)) {
            if (inputStream1 == null) {
                System.out.println("File not found!");
                return "";
            }
            // 读取文件内容
            String content = new String(inputStream1.readAllBytes());
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream2 = getResourceAsStream("com/javatutorials/xml/"+fileName)) {
            String content = new String(inputStream2.readAllBytes());
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public static InputStream getResourceAsStream(String resource) throws IOException {
        final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
        if (in == null) {
            throw new IOException("Resource not found: " + resource);
        }
        return in;
    }

}
