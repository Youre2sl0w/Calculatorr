import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическую операцию");
        String otvet = scanner.nextLine();
        System.out.println(reshenie(otvet));
    }

    public static String reshenie(String zadacha) throws Exception {
        String otvet;
        boolean proverka;
        String[] operandy;
        String operaziya;
        int n1;
        int n2;
        operandy = zadacha.split("[-+/*]");
        if (operandy.length < 2) {
            throw new Exception("Строка не является математической операцией");
        }
        if (operandy.length > 2) {
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (-, +, /, *)");
        }
        operaziya = kakoyZnakBudet(zadacha);
        if(operaziya == null) {
            throw new Exception("Формат математической операции не удовлетворяет заданию, доступные операторы: (-, +, /, *)");
        }
        if(proverka(operandy[0]) && proverka(operandy[1])){
            n1 = converter(operandy[0]);
            n2 = converter(operandy[1]);
            proverka = true;
        } else if(!proverka(operandy[0]) && !proverka(operandy[1])) {
            n1 = Integer.parseInt(operandy[0]);
            n2 = Integer.parseInt(operandy[1]);
            proverka = false;
        } else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        if(n1 > 10 || n2 > 10) {
            throw new Exception("Числа не должны превышать 10");
        }
        if(n1 < 0 || n2 < 0) {
            throw new Exception("Числа не должны быть отрицательными");
        }
        int arabskie = calculate(n1, n2, operaziya);
        if(!proverka) {
            otvet = String.valueOf(arabskie);
        } else {
            otvet = converterRimskie(arabskie);
        } return otvet;
    }

    static String converterRimskie(int x) throws Exception {
        if(x < 0){
            throw new Exception("В римской системе нет отрицательных чисел");
        }
        return rimskie[x];
    }

    static int converter(String r) {
        for(int i = 0; i < rimskie.length; i++) {
            if(r.equals(rimskie[i])) {
                return i;
            }
        }
        return -1;
    }

    static int calculate(int n1, int n2, String operaziya){
        return switch (operaziya) {
            case "-" -> n1 - n2;
            case "+" -> n1 + n2;
            case "/" -> n1 / n2;
            default -> n1 * n2;
        };
    }

    static String kakoyZnakBudet(String zadachka) {
        String znak;
        if (zadachka.contains("+")) {
            znak = "+";
        } else if(zadachka.contains("-")) {
            znak = "-";
        } else if (zadachka.contains("*")) {
            znak = "*";
        } else if (zadachka.contains("/")) {
            znak = "/";
        } else znak = null;
        return znak;
    }

    static String[] rimskie = new String[] {
            "0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };

    static boolean proverka(String x) {
        for (String s : rimskie) {
            if (x.equals(s)) {
                return true;
            }
        }
        return false;
    }
}