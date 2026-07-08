package corejava.variablesdatatypes;

public class TypeCasting {
    public static void main(String[] args) {
        int number = 22;
        double dbNumber = number;
        char ch = 'A';
        int chInt = ch;
        System.out.println("int :"+ number + " ---> double :" + dbNumber);
        System.out.println("char :"+ ch + " ---> int :" + chInt);
    }
}
// output::
// int :22 ---> double :22.0
// char :A ---> int :65