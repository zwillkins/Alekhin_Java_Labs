package ru.LAB1;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Демонстрация основных конструкций Java ===");

        demonstrateConditions();
        demonstrateLoops();
        demonstrateBoxing();
        demonstrateStrings();
        demonstrateStringBuilder();
    }


    public static void demonstrateConditions() {
        int age = 25;
        boolean hasLicense = true;
        System.out.println("Пример с 2 операндами (age > 18 && hasLicense):");
        if (age > 18 && hasLicense) {
            System.out.println("  Доступ разрешен.");
        } else {
            System.out.println("  Доступ запрещен.");
        }

        // Пример с 3 
        int score = 85;
        String category = "A";
        System.out.println("Пример с 3 операндами (age > 20 && score > 80 && category == 'A'):");
        if (age > 20 && score > 80 && category.equals("A")) {
            System.out.println("  Кандидат подходит по всем критериям.");
        } else if (score > 80) {
            System.out.println("  Кандидат подходит только по баллам.");
        } else {
            System.out.println("  Кандидат не подходит.");
        }
        
        // Пример с 4 
        int experience = 3;
        boolean isRecommended = true;
        System.out.println("Пример с 4 операндами и ||:");
        if ((age > 30 && experience > 5) || (score > 90 && isRecommended)) {
            System.out.println("  Кандидат принят по одному из двух основных критериев.");
        } else {
            System.out.println("  Кандидат не прошел по основным критериям.");
        }

        System.out.println("\n1.2. Демонстрация switch/case:");
        int dayOfWeek = 4;
        String dayName;
        switch (dayOfWeek) {
            case 1:
                dayName = "Понедельник";
                break; 
            case 2:
                dayName = "Вторник";
                break;
            case 3:
                dayName = "Среда";
                break;
            case 4:
                dayName = "Четверг";
                break;
            case 5:
                dayName = "Пятница";
                break;
            case 6:
            case 7:
                dayName = "Выходной";
                break;
            default: 
                dayName = "Некорректный день";
                break;
        }
        System.out.println("  Сегодня " + dayName);

        // 1.3. 
        int userPoints = 150;
        String status = (userPoints > 100) ? "VIP-клиент" : "Стандартный клиент";
        System.out.println("  Статус клиента: " + status);
    }

    public static void demonstrateLoops() {
        
        System.out.println("\n2.1. Циклы for, while, do...while:");
        System.out.print("  Цикл for (от 5 до 1): ");
        for (int i = 5; i > 0; i--) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.print("  Цикл while (от 0 до 2): ");
        int j = 0;
        while (j < 3) {
            System.out.print(j + " ");
            j++;
        }
        System.out.println();

        System.out.print("  Цикл do...while (от 0 до 2): ");
        int k = 0;
        do {
            System.out.print(k + " ");
            k++;
        } while (k < 3);
        System.out.println();

        // 2.2.
        System.out.println("\n2.2. Операторы continue и break в цикле от 1 до 10:");
        System.out.print("  ");
        for (int i = 1; i <= 10; i++) {
            if (i == 3) {
                System.out.print("(пропуск " + i + ") ");
                continue;
            }
            if (i == 8) {
                System.out.print("(прерывание на " + i + ")");
                break; 
            }
            System.out.print(i + " ");
        }
        System.out.println();

        // 2.3. 
        System.out.println("\n2.3.матрица 3x3:");
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        for (int row = 0; row < matrix.length; row++) {
            System.out.print("  Строка " + row + ": ");
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }


    public static void demonstrateBoxing() {

        // int -> Integer 
        int primitiveInt = 10;
        Integer wrappedInt = primitiveInt; 
        System.out.println("  int -> Integer: " + primitiveInt + " -> " + wrappedInt);

        // Integer -> int 
        int unwrappedInt = wrappedInt;
        System.out.println("  Integer -> int: " + wrappedInt + " -> " + unwrappedInt);
        
        // boolean/Boolean
        Boolean wrappedBool = true;
        boolean unwrappedBool = wrappedBool;
        System.out.println("  Boolean -> boolean: " + wrappedBool + " -> " + unwrappedBool);
        
        // long/Long
        Long wrappedLong = 12345L;
        long unwrappedLong = wrappedLong;
        System.out.println("  Long -> long: " + wrappedLong + " -> " + unwrappedLong);

        System.out.println("\n  Симуляция NullPointerException:");
        Integer nullableInteger = null;
        try {
            int errorInt = nullableInteger;
        } catch (NullPointerException e) {
            System.out.println("    Успешно поймали ошибку: " + e.getClass().getName());
            System.out.println("    Нельзя присвоить null примитивному типу int!");
        }
    }



    public static void demonstrateStrings() {
        System.out.println("\n--- 4. Работа со строками ---");
        String text = "  Java-программирование - это интересно!  ";

        // 4.1. 
        System.out.println("  Оригинал: '" + text + "'");
        
        String replaced = text.replace("интересно", "увлекательно");
        System.out.println("  Замена: '" + replaced + "'");
        
        String trimmed = text.trim(); 
        System.out.println("  Обрезка пробелов: '" + trimmed + "'");

        String substring = trimmed.substring(0, 4);
        System.out.println("  Подстрока (первые 4 символа): '" + substring + "'");

        System.out.println("  Разбиение по разделителю (пробел):");
        String[] words = trimmed.split(" ");
        for (String word : words) {
            System.out.println("    Слово: " + word);
        }
        
        // 4.2. 
        System.out.println("\n4.2. Сравнение строк:");
        String s1 = "Test";
        String s2 = "Test";
        String s3 = new String("Test");
        String s4 = "test";

        System.out.println("  s1 = \"Test\"; s2 = \"Test\"; s3 = new String(\"Test\"); s4 = \"test\";");
        System.out.println("  s1 == s2: " + (s1 == s2));
        System.out.println("  s1 == s3: " + (s1 == s3));
        
        System.out.println("  s1.equals(s3): " + s1.equals(s3)); 
        System.out.println("  s1.equals(s4): " + s1.equals(s4)); 
        System.out.println("  s1.equalsIgnoreCase(s4): " + s1.equalsIgnoreCase(s4)); 
    }



    public static void demonstrateStringBuilder() {
        System.out.println("\n--- 5. Работа с StringBuilder ---");

        StringBuilder sb = new StringBuilder("Отчет: ");
        
        System.out.println("  Начальное значение: " + sb.toString());
        sb.append("Продажи за ");
        sb.append(2024);
        sb.append(" год.");
        System.out.println("  После конкатенации (append): " + sb.toString());

        sb.delete(0, 7);
        System.out.println("  После удаления (delete): " + sb.toString());
        
        sb.insert(0, "[СЕКРЕТНО] ");
        System.out.println("  После вставки (insert): " + sb.toString());
    }
}