package spaceEscape;

import java.io.FileWriter;

public class Writer {
    public static void main(String[] args){
        try{
            FileWriter writer = new FileWriter("high.txt");
            writer.write("98765");
            writer.close();
        }
        catch(Exception e){
            System.out.println("Error occurred while writing to file.");
        }
    }
}