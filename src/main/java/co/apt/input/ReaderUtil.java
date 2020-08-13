package co.apt.input;

import java.util.Scanner;

public class ReaderUtil {

    Scanner scanner = null;

    public  ReaderUtil(){
        scanner = new Scanner(System.in);
    }

    public int readInt(){
        return  scanner.nextInt();
    }


    public String readString() {
        return scanner.nextLine();
    }

    public String[] readCustom(){
        return scanner.nextLine().split(",");
    }
}
