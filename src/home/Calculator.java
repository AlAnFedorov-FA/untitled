package home;
//import javax.xml.transform.Result;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    String primer;

    public static void main(String[] args) {
        start(true); // Приветствие при запуске можно отключить
        String flg = "work";
        Scanner in = new Scanner(System.in);  // Создаем один сканер на все время работы
        do {
            try { // При любой ошибке запускаем следующую итерацию цикла (новый ввод)
                System.out.println("Введите выражение в формате: 10+3");

                // Считываем сканером введённое в консоли выражение
                String primer = PrimerIn(in);

// Если пользователь ввёл exit цикл дальше не пойдёт
                if (primer.equals("exit")) {
                    flg = "stop";
                    System.out.println("Выход из программы.");
                } else {

                    // Парсим и проверяем введённое выражение
                    Result result = prov(primer);

                    if (result != null) {
                        switch (result.operator) {
                            case OPERATOR_PLUS:
                                System.out.println("Результат = " + sum(result));
                                break;
                            case OPERATOR_MINUS:
                                System.out.println("Результат = " + subtract(result));
                                break;
                            case OPERATOR_MULTIPLY:
                                System.out.println("Результат = " + multiply(result));
                                break;
                            case OPERATOR_DIVIDE:
                                if (result.op2 != 0) { // Проверяем деление на 0
                                    System.out.println("Результат = " + divide(result));
                                } else {
                                    System.out.println("Ошибка: деление на 0.00 (ноль)");
                                }
                                break;
                            case OPERATOR_DIVIDE_INT:
                                if (result.op2 != 0) { // Проверяем деление на 0
                                    System.out.println("Результат = " + integerDivide(result));
                                } else {
                                    System.out.println("Ошибка: деление на 0.00 (ноль)");
                                }
                                break;
                            case OPERATOR_POWER:
                                System.out.println("Результат = " + power(result));
                                break;
                        }
                    }
                }
                } catch(Exception e){
                    System.out.println("Произошла ошибка: " + e.getMessage());
                    System.out.println("Переход к следующему вводу...");

                }
        } while (flg == "work");

        in.close();  // Закрываем сканер

    }

    // Окно приветствия, можно выключить
    public static void start(boolean welcome) {
        if (welcome == true) {
            // Выводим информацию о поддерживаемых операциях
            System.out.println("Консольный калькулятор");
            System.out.println("Поддерживаемые операции: +, -, *, /, %, ^");
            System.out.println("Введите выражение в формате: число операция число (10+3)");
            System.out.println("Для выхода введите 'exit'");
        }
    }

    public static String PrimerIn(Scanner in) {
        String primer = in.nextLine();
        primer=primer.replace(" ","");     // удаляем пробелы, если вдруг есть
        primer=primer.replace(",",".");     // меняем запятую на точку в дробных числах
        return primer;
    }

    public static Result prov(String v) {
        // Проверяем соответствие введённого текста регулярному выражению для высилений в калькуляторе
        boolean result = v.matches("^(\\d+(\\.\\d+)?)+(\\W)+(\\d+(\\.\\d+)?)$"); //("^(\\d+)+(\\W)+(\\d+)$");
        if (result) {
            // Парсим введённый текст по частям регулярного выражения
            Pattern pattern = Pattern.compile("(\\d+(\\.\\d+)?)+(\\W)+(\\d+(\\.\\d+)?)");//"(\\d+)+(\\W)+(\\d+)");
            Matcher matcher = pattern.matcher(v);
            matcher.find();

            // Определяем оператор, как символ между двумя числами, и проверяем его соответствие enum
            String operatorStr =matcher.group(3); //1
            char operatorChar = operatorStr.charAt(0);
            Operator operator = Operator.getOperator(operatorChar);

            // Проверяем, что оператор идентифицирован по enum
            if (Objects.isNull(operator)){
                System.out.println("Оператор не определён.Неверное выражение. Введите еще раз в формате: 10+3");
                return null;
            }

            double op1=Double.valueOf(matcher.group(1));
            double op2=Double.valueOf(matcher.group(4)); //3

            return new Result(op1, op2, operator); // Возвращаем 2 числа и оператор, если всё распарсили верно

        } else {
            // Введенное выражение не соответствует регулярному выражению
            if (!v.equals("exit")) {
                    System.out.println("Неверное выражение.");
            }
        }
        return null; // Возвращаем null, если введённый текст не соответствует регулярному выражению
            }

            // Задаём список операторов, с которыми может работать калькулятор
    public enum Operator {
        OPERATOR_PLUS('+'),
        OPERATOR_MINUS('-'),
        OPERATOR_MULTIPLY('*'),
        OPERATOR_DIVIDE('/'),
        OPERATOR_POWER('^'),
        OPERATOR_DIVIDE_INT('%');

        private char symbol;

        // Получаем оператор, который должен соответствовать нашему списку
        Operator(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }
        // Метод для поиска оператора по символу
        public static Operator getOperator(char symbol) {
            for (Operator op : Operator.values()) {
                if (op.symbol == symbol) {
                    return op;
                }
            }
            return null;
        }
    }
// Класс, в который будем записывать 2 числа и оператор из введённого выражения для дальнейших вычислений
 public static class Result {
     double op1;
     double op2;
     Operator operator;

     public Result(double op1, double op2, Operator operator) {
         this.op1 = op1;
         this.op2 = op2;
         this.operator = operator;
     }
 }

    // Функции для выполнения операций
    public static double sum(Result result) {
        return result.op1 + result.op2;
    }

    public static double subtract(Result result) {
        return result.op1 - result.op2;
    }

    public static double multiply(Result result) {
        return result.op1 * result.op2;
    }

    public static double divide(Result result) {
            return result.op1 / result.op2;
    }

    public static int integerDivide(Result result) {
        return (int) (result.op1 % result.op2);
    }

    public static double power(Result result) {
        return Math.pow(result.op1, result.op2);
    }
}

