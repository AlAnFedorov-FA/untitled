package home;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Абстрактный класс, определяющий интерфейс для всех операций вычисления
abstract class Operation {
    protected double operand1;
    protected double operand2;

    public Operation(double operand1, double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public abstract double calculate(); // абстрактный метод в абстрактном классе, все наследники класса должны его наследовать
}

// Основной класс калькулятора
public class Calculator2 {
    private Operation operation;

    // Метод для установки текущей операции, введённой пользователем
    public void setOperation(Operation operation) {
        this.operation = operation;
    }
// Метод для выполнения вычисления
    public double calculate() {
        return operation.calculate();
    }

    // Основной метод: запускает калькулятор, обрабатывает ввод пользователя, парсит выражение,
    // обращается к классам вычислений в зависимости от оператора, выводит результат
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
                        Calculator2 calculator = new Calculator2();
                        switch (result.operator) {
                            case OPERATOR_PLUS:
                                calculator.setOperation(new Addition(result.op1, result.op2));
                                break;
                            case OPERATOR_MINUS:
                                calculator.setOperation(new Subtraction(result.op1, result.op2));
                                break;
                            case OPERATOR_MULTIPLY:
                                calculator.setOperation(new Multiplication(result.op1, result.op2));
                                break;
                            case OPERATOR_DIVIDE:
                                    calculator.setOperation(new Division(result.op1, result.op2));
                                break;
                            case OPERATOR_DIVIDE_INT:
                                    calculator.setOperation(new IntegerDivision(result.op1, result.op2));
                                break;
                            case OPERATOR_POWER:
                                calculator.setOperation(new Power(result.op1, result.op2));
                                break;
                        }
                        System.out.println("Результат = " + calculator.calculate());
                    }
                }
            } catch (Exception e) {
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
        primer = primer.replace(" ", "");     // удаляем пробелы, если вдруг есть
        primer = primer.replace(",", ".");     // меняем запятую на точку в дробных числах
        return primer;
    }

// Хранит результат парсинга введенного пользователем выражения
    public static Result prov(String v) {
        // Проверяем соответствие введённого текста регулярному выражению для высилений в калькуляторе
        boolean result = v.matches("^(\\d+(\\.\\d+)?)+(\\W)+(\\d+(\\.\\d+)?)$"); //("^(\\d+)+(\\W)+(\\d+)$");
        if (result) {
            // Парсим введённый текст по частям регулярного выражения
            Pattern pattern = Pattern.compile("(\\d+(\\.\\d+)?)+(\\W)+(\\d+(\\.\\d+)?)");//"(\\d+)+(\\W)+(\\d+)");
            Matcher matcher = pattern.matcher(v);
            matcher.find();

            // Определяем оператор, как символ между двумя числами, и проверяем его соответствие enum
            String operatorStr = matcher.group(3); //1
            char operatorChar = operatorStr.charAt(0);
            Operator operator = Operator.getOperator(operatorChar);

            // Проверяем, что оператор идентифицирован по enum
            if (Objects.isNull(operator)) {
                System.out.println("Оператор не определён.Неверное выражение. Введите еще раз в формате: 10+3");
                return null;
            }

            double op1 = Double.valueOf(matcher.group(1));
            double op2 = Double.valueOf(matcher.group(4)); //3

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
}

// Далее наследники базового класса, производящие вычисления, наследовав метод calculate, возвращающие результат
// Класс операции сложения
class Addition extends Operation {
    public Addition(double operand1, double operand2) {
        super(operand1, operand2);
    } //

    @Override
    public double calculate() {
        return operand1 + operand2;
    }
}

// Класс операции вычитания
class Subtraction extends Operation {
    public Subtraction(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() {
        return operand1 - operand2;
    }
}

// Класс операции умножения
class Multiplication extends Operation {
    public Multiplication(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() {
        return operand1 * operand2;
    }
}

// Класс операции деления
class Division extends Operation {
    public Division(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() {
        if (operand2 != 0) { // Проверяем деление на 0
            return operand1 / operand2;
        } else {
            System.out.println("Ошибка: деление на 0.00 (ноль)");
        }
        return Double.NaN; // возвращаем "не число" в случае деления на 0
    };
}

// Класс операции целочисленного деления
class IntegerDivision extends Operation {
    public IntegerDivision(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() {
        if (operand2 != 0) { // Проверяем деление на 0
            return (int) (operand1 / operand2);
        } else {
            System.out.println("Ошибка: деление на 0.00 (ноль)");
        }
        return Double.NaN; // возвращаем "не число" в случае деления на 0
    };
}

// Класс операции возведения в степень
class Power extends Operation {
    public Power(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() {
        return Math.pow(operand1, operand2);
    }
}

