package home;
//import javax.xml.transform.Result;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator_рабочийВар {
    String primer;

    public static void main(String[] args) {
        start(true);
        int flg = 0;
        Scanner in = new Scanner(System.in);  // Создаем один сканер на все время работы
        do {
            try {
            System.out.println("Введите выражение в формате: 10+3");
            //   Scanner in = new Scanner(System.in);

            String primer = PrimerIn(in);
            //  in.close();

            if (primer.equals("exit")) {
                flg = 1;
                System.out.println("Выход из программы.");
            }


            System.out.println(primer);
            Result result = prov(primer);
            //prov(primer);

            if (result != null) {
                System.out.println("Оператор: " + result.operator);
                System.out.println("Операнд 1: " + result.op1);
                System.out.println("Операнд 2: " + result.op2);
                //  System.out.println("Результат: " + calculate(result.op1, result.op2, result.operator));

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
                        if (result.op2 != 0) {
                            System.out.println("Результат = " + divide(result));
                        } else {
                            System.out.println("Ошибка: деление на 0.00 (ноль)");
                        }
                        break;
                    case OPERATOR_DIVIDE_INT:
                        if (result.op2 != 0) {
                            //  double d=integerDivide(result);
                            // int n=d.intValue();
                            System.out.println("Результат = " + integerDivide(result));
                        } else {
                            System.out.println("Ошибка: деление на 0.00 (ноль)");
                        }
                        break;
                    case OPERATOR_POWER:
                        System.out.println("Результат = " + power(result));
                        break;

                    //      if (result.operator==OPERATOR_PLUS) {System.out.println("Результат = " + sum(result));}
                }
            }
            } catch (Exception e){
                System.out.println("Произошла ошибка: " + e.getMessage());
                System.out.println("Переход к следующему вводу...");

            }
            //}
           // System.out.println(result);
           // System.out.println(result.op2);
         //   System.out.println(operator);

        } while (flg == 0);

        in.close();  // Закрываем сканер

        //   private String primer;


        //     public void setName(String vv){
        //         Scanner in = new Scanner(System.in);
        //         vv = in.nextLine();
        //        in.close();
        //       primer=vv;
        //  }

    }

    public static void start(boolean welcome) {
        if (welcome == true) {
            // Выводим информацию о поддерживаемых операциях
            System.out.println("Консольный калькулятор");
            System.out.println("Поддерживаемые операции: +, -, *, /, ^");
            System.out.println("Введите выражение в формате: число операция число (10+3)");
            System.out.println("Для выхода введите 'exit'");
        }
    }

    public static String PrimerIn(Scanner in) {
       // Scanner in = new Scanner(System.in);
        String primer = in.nextLine();
        primer=primer.replace(" ","");     // удаляем пробелы, если вдруг есть
        // in.close();
        return primer;
    }

    public static Result prov(String v) {
 //   v=v.replace(" ","");     // удаляем пробелы, если вдруг есть
        boolean result = v.matches("^(\\d+)+(\\W)+(\\d+)$");
        int er=0;
        if (result) {
            System.out.println("ДА");
//v=10+3
            Pattern pattern = Pattern.compile("(\\d+)+(\\W)+(\\d+)");
            Matcher matcher = pattern.matcher(v);

            System.out.println(v+"= что ввели");
            matcher.find();
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));

          //  char operatorChar = scanner.next().charAt(0);
            String operatorStr =matcher.group((2));
            char operatorChar = operatorStr.charAt(0);
            Operator operator = Operator.getOperator(operatorChar);
            double op1=Double.valueOf(matcher.group(1));
            double op2=Double.valueOf(matcher.group(3));
            System.out.println(operator);
        //    System.out.println(Result.op1);
            if (Objects.isNull(operator)){
                System.out.println("Оператор не определён.Неверное выражение. Введите еще раз в формате: 10+3");
            return null;
            }
         //   System.out.println(op1 + operator + op2);
         //   if (matcher.find()) {
         //   System.out.println(matcher.group(1));
         //   } else {
         //       System.out.println("Совпадений не найдено");}

        //    System.out.println(matcher.group(2));
         //   System.out.println(matcher.group(3));
            return new Result(op1, op2, operator);
           // return op1, op2,operator
        } else {
            if (!v.equals("exit")) {
                    System.out.println("Неверное выражение.");
            }
        }
        return null;
            }
       // }

        //Pattern pattern = Pattern.compile("А.+а");

       // return result;
  //  }

    public enum Operator {
        OPERATOR_PLUS('+'),
        OPERATOR_MINUS('-'),
        OPERATOR_MULTIPLY('*'),
        OPERATOR_DIVIDE('/'),
        OPERATOR_POWER('^'),
        OPERATOR_DIVIDE_INT('%');

        private char symbol;

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
 //   public static boolean isNull(Object operator)
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
    //    } else {System.out.println("Ошибка: деление на 0.00 (ноль)");
     //       return null;}
    }

    public static int integerDivide(Result result) {
        return (int) (result.op1 % result.op2);
    }

    public static double power(Result result) {
        return Math.pow(result.op1, result.op2);
    }
}

