package com.company;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InputOutput {
    File file = new File(Main.USERNAME + ".txt");// deklarace souboru

    ErrorPrinting ep = new ErrorPrinting();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    public void createFile(String name) {
        boolean rs;

        try {
            rs = file.createNewFile();//pokud se vytvori dosadi se true
            if (rs) {
                System.out.println("file created " + file.getCanonicalPath());
            } else {
                System.out.println("File already exist at location: " + file.getCanonicalPath());
            }
        } catch (IOException e) {
            ep.writeToFile("ERROR IOException:" + "\n Chyba v metode createFile: " + e.toString() + "\n V case: " + dtf.format(now));
        }

    }

    public void writeToFile(String name, String info) {
        try {
            FileOutputStream fos = new FileOutputStream(name, true);
            byte[] b = info.getBytes();
            fos.write(b);
            fos.close();

        } catch (IOException e) {
            ep.writeToFile("ERROR IOException:" + "\n Chyba v metode writeToFile: " + e.toString() + "\n V case: " + dtf.format(now));
        }
    }

    public void readFile(String name) {
        InputStream input = null;
        try {
            input = new BufferedInputStream(new FileInputStream(name));
            byte[] buffer = new byte[8192];
            for (int length = 0; (length = input.read(buffer)) != -1; ) {
                System.out.write(buffer, 0, length);
            }
        } catch (FileNotFoundException e) {
            ep.writeToFile("ERROR FileNotFoundException:" + "\n Chyba v metode readFile: " + e.toString() + "\n V case: " + dtf.format(now));
        } catch (IOException e) {
            ep.writeToFile("ERROR IOException:" + "\n Chyba v metode readFile: " + e.toString() + "\n V case: " + dtf.format(now));
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                ep.writeToFile("ERROR IOException:" + "\n Chyba v metode readFile ve final bloku: " + e.toString() + "\n V case: " + dtf.format(now));
            }
        }
    }

}
