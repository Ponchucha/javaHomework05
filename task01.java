import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/*Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может иметь несколько телефонов.
Добавить функции 
1) Добавление номера
2) Вывод всего */
/**
 * task01
 */
public class task01 {

    public static void main(String[] args) {
        HashMap <String, LinkedList<String>> phonebook = new HashMap<>();
        String menuChoise = "";
        Scanner getCommand = new Scanner(System.in);
        while(true){
            System.out.println("Input menu command: \n1. Add new number \n2. Show all entries \n3. Exit");
            menuChoise = getCommand.next();
            switch(menuChoise){
                case "1": 
                    AddContact(phonebook);
                    continue;
                case "2":
                    PrintPhoneBook(phonebook);
                    continue;
                case "3":
                    getCommand.close();
                    break;
                default:
                    System.out.println("Wrong command. Input digit 1 or 2.");
                    continue;
            }
        }
    }
    static void AddContact(HashMap <String, LinkedList<String>> phB){
        Scanner scan = new Scanner(System.in);
        LinkedList<String> numbers = new LinkedList<>();
        System.out.println("Input name of new contact: ");
        String name = scan.next();
        String input = "";
        while (true){
            System.out.println("Input new phone number for " + name + "\n Or input 'done' to continue: ");
            input = scan.next();
            if(! input.equals("done")){
                numbers.add(input);
            }
            else{
                break;
            }
        }
        phB.put(name, numbers);

    }
    static void PrintPhoneBook(HashMap <String, LinkedList<String>> phB){
        for (var item : phB.entrySet()) {
            System.out.print(item.getKey() + ": ");
            for (String phone : item.getValue()) {
                System.out.print(phone + " | ");
            }
            System.out.println();
        }
    }

}