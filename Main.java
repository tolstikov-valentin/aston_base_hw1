public class Main {

    public static void main(String[] args) {
        exampleMyArrayList();
        exampleMyHashMap();
    }
    
    static void exampleMyArrayList() {
        System.out.println("Пример работы класса MyArrayList:\n");
        
        System.out.println("Создание пустого массива:");
        var arrayTest = new MyArrayList<String>();
        arrayTest.print();

        System.out.println("Добавление элементов:");
        for (char c = 'A'; c <= 'Z'; c++) {
            arrayTest.add(Character.toString(c));
        }
        arrayTest.print();
        
        int index = 1;
        System.out.printf("Удаление элемента с индексом %d:\n", index);
        arrayTest.remove(index);
        arrayTest.print();
        
        index = 3;
        System.out.printf("Вставка элемента с индексом %d:\n", index);
        arrayTest.add(index, "Hello!");
        arrayTest.print();
        
        index = 17;
        System.out.printf("Изменение элемента с индексом %d:\n", index);
        arrayTest.set(index, "Hi!");
        arrayTest.print();
        
        index = 2;
        System.out.printf("Получение элемента с индексом %d:\n", index);
        System.out.println(arrayTest.get(index) + "\n");        
        
        System.out.println("Уменьшение размера массива до фактического количества объектов:");
        arrayTest.restrictCapacity();
        arrayTest.print();
        
        index = 10;
        System.out.println("Вставка значения null:");
        arrayTest.add(index, null);
        arrayTest.print();
        
        String str = "A";
        System.out.println("Добавление элементов для проверки поиска:");
        arrayTest.add(10, str);
        arrayTest.add(20, str);
        arrayTest.add(str);
        arrayTest.print();
        System.out.printf("Поиск всех вхождений элемента %s:\n", str);
        System.out.println(arrayTest.findAll(str).toString());
        
        System.out.printf("\n%s\n\n", "*".repeat(80));
    }

    static void exampleMyHashMap() {
        System.out.println("Пример работы класса MyHashMap:\n");
        
        var hashTableTest = new MyHashMap<String, Integer>();

        System.out.println("Добавление элементов:");
        for (int i = 0; i < 10; i++) {
            hashTableTest.put(Character.toString('A' + i), i);
        }
        
        hashTableTest.put(null, 77);
        hashTableTest.put("P", 19);
        hashTableTest.put("G", 16);
        hashTableTest.put("G", 16);
        hashTableTest.put("G", 16);
        
        hashTableTest.print();

        System.out.println("Получение значения по ключу:");
        for (String key : new String[]{"H", null, "J"}) {
            System.out.printf("Ключ: %s, значение: %d\n", key, hashTableTest.get(key));
        }

        System.out.println("\nУдаление элементов по ключу:");
        for (String key : new String[]{"H", "E", "J"}) {
            System.out.printf("Ключ: %s, удаляемое значение: %d\n", key, hashTableTest.remove(key));
        }
        
        System.out.println();
        hashTableTest.print();
        
        System.out.println("Добавление элементов для увеличения размера хеш-таблицы:");
        for (int i = 10; i < 30; i++) {
            hashTableTest.put(Character.toString('A' + i), i);
        }
        hashTableTest.print();
        
        System.out.println("Проверка вхождения по ключу:");
        
        for (String key : new String[]{"Y", null, "a"}) {
            System.out.printf("Ключ: %s, результат: %b\n", key, hashTableTest.containsKey(key));
        }
        
        System.out.println("\nПроверка вхождения по значению:");
        for (int i : new int[]{0, 23, 57}) {
            System.out.printf("Значение: %s, результат: %b\n", i, hashTableTest.containsValue(i));
        }
    }

}
