import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/*Пусть дан список сотрудников:
Иван Иванов
Светлана Петрова
Кристина Белова
Анна Мусина
Анна Крутова
Иван Юрин
Петр Лыков
Павел Чернов
Петр Чернышов
Мария Федорова
Марина Светлова
Мария Савина
Мария Рыкова
Марина Лугова
Анна Владимирова
Иван Мечников
Петр Петин
Иван Ежов

Написать программу, которая найдет и выведет повторяющиеся имена с количеством повторений.
Отсортировать по убыванию популярности Имени. */
public class task02 {
    public static void main(String[] args) {
        String staff = "";
        File file = new File("staff.txt");
        Map <String, String> surnames = new HashMap<>(); 
        Map <String, Integer> counter  = new HashMap<>();
        String firstName = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while((staff = reader.readLine()) != null){
                surnames.put(staff.split(" ")[1], staff.split(" ")[0]);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("error");
        }
        
        for (var entry : surnames.entrySet()) {
            firstName = entry.getValue();
            counter.putIfAbsent(firstName, 0);             // кладём в словарь Имя, если его нет
            counter.put(firstName, counter.get(firstName)+1);    // считаем, сколько раз оно встречается
        }
        Map <Integer, ArrayList<String>> sortednames = new TreeMap<>(Comparator.reverseOrder());
        for (var item : counter.entrySet()) {                             // меняем местами ключ и значение. Т.к. значения не уникальны, будем их класть в массив
            sortednames.putIfAbsent(item.getValue(), new ArrayList<>()); // если такого числа не было, то добавляем его в словарь, в значении - пустой массив
            sortednames.get(item.getValue()).add(item.getKey());  // а когда оно уже есть, добавляем к значению имя
        }
        Map <String, String> result = new LinkedHashMap<>(); // можно было просто вывести результат на экран, но на случай, если надо таки его куда-то положить...
        for (var item : sortednames.entrySet()) {
            for (String name : item.getValue()) {
                for (var person : surnames.entrySet()) {
                    if(person.getValue().equals(name)){
                        result.put(person.getKey(), person.getValue());
                    }
                }
            }
        }
        for (var employee : result.entrySet()) {
            System.out.println(employee.getKey() + " " + employee.getValue() + ": " + counter.get(employee.getValue()));
        }
        
    }
}
