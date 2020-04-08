package foundations;

import javax.swing.*;

public class ReadJOprionPane

{
    public static void main(String[] args) {
        String name;
        String surname;
        name = JOptionPane.showInputDialog("please  enter your name");
        surname = JOptionPane.showInputDialog("please  enter your surname");

        JOptionPane.showMessageDialog(null,"your Name "+name+surname);

        int fn;
        int sn;
        String input;
        input = JOptionPane.showInputDialog("please  enter fn");
        fn =  Integer.parseInt(input);
        input = JOptionPane.showInputDialog("please  enter sn");
        sn =  Integer.parseInt(input);
        int sum = fn+sn;

        JOptionPane.showMessageDialog(null,sum);

    }

}
