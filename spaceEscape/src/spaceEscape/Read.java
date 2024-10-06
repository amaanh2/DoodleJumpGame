package spaceEscape;

import java.io.FileReader;

public class Read{
    public static void main(String[] args){
        String name = "";
        try{
            FileReader reader = new FileReader("high.txt");
            int data = reader.read();
            while (data != -1){
                name = name + (char)data;
                data = reader.read();
            }
            reader.close();
        }
        catch (Exception e){
            System.out.println("Error reading the file.");
        }
        System.out.println(name);
    }
}