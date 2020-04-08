package foundations;

import java.util.Scanner;

public class ReadingInput
{

    public static void main(String[] args) {

        int fn;
        int sn;

        Scanner newScannerObj = new Scanner(System.in);
        try {
            System.out.println("please enter the first no:");
            fn = newScannerObj.nextInt();
            System.out.println("please enter the Second no:");
            sn = newScannerObj.nextInt();
            double average = (sn + fn) / 2.0;
            System.out.println("the average of the 2 numbers is " + average);
        newScannerObj.nextLine();
            String fname;
            String sname;
            System.out.println("please eneter first name");
            fname = newScannerObj.nextLine();
            System.out.println("please eneter last name");
            sname = newScannerObj.nextLine();

            System.out.println("your Name is " + fname + " " + sname);


        } catch (Exception e) {
            System.out.println(e);
        }


    }


}
