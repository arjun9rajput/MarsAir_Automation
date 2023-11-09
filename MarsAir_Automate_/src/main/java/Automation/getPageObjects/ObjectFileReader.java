package Automation.getPageObjects;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ObjectFileReader {


    static String filepath = "src/test/resources/PageObjectRepository/Locators";

    public static String[] getELementFromFile(String pageName,
                                              String elementName) {
        try {
            FileReader specFile = new FileReader(filepath + ".spec");
            return getElement(specFile, elementName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String[] getElement(FileReader specFile, String elementName)
            throws Exception {

        ArrayList<String> elementLines = getSpecSection(specFile);
        for (String elementLine : elementLines) {
            if (elementLine.startsWith(elementName)) {
                return elementLine.split(" ", 3);
            }
        }
        throw new Exception();
    }

    private static ArrayList<String> getSpecSection(FileReader specfile) {
        String readBuff = null;
        ArrayList<String> elementLines = new ArrayList<>();

        try {
            BufferedReader buff = new BufferedReader(specfile);
            try {
                boolean flag = false;
                readBuff = buff.readLine();
                while ((readBuff = buff.readLine()) != null) {
                    if (readBuff.startsWith("========")) {
                        flag = !flag;
                    }
                    if (flag) {
                        elementLines.add(readBuff.trim().replaceAll("[ \t]+",
                                " "));
                    }
                    if (!elementLines.isEmpty() && !flag) {
                        break;
                    }
                }
            } finally {
                buff.close();
                if (elementLines.get(0).startsWith("========")) {
                    elementLines.remove(0);
                }
            }
        } catch (FileNotFoundException e) {
            System.out
                    .println("Spec File not found at location :- " + filepath);
        } catch (IOException e) {
            System.out.println("exceptional case");
        }
        return elementLines;
    }

}
