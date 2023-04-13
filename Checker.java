package numbers;

public class Checkers {
    public static boolean checkIfEven(Long num) {
        return num % 2 == 0;
    }

    public static boolean checkIfBuzz(Long num) {
        boolean isDiv7 = false;
        boolean isEnd7 = false;

        if (num % 7 == 0) {
            isDiv7 = true;
        }
        if (num % 10 == 7) {
            isEnd7 = true;
        }
        return isDiv7 || isEnd7;
    }

    public static boolean checkIfDuck(Long num) {
        while (num != 0) {
            if (num % 10 == 0)
                return true;
            num = num / 10;
        }

        return false;
    }

    public static boolean checkIfPalindromic(Long num) {
        String number = Long.toString(num);

        if (number.length() == 1)
            return true;

        for (int i = 0; i < number.length() / 2; i++) {
            if (number.charAt(i) != number.charAt(number.length() - 1 - i))
                return false;
        }

        return true;
    }

    public static boolean checkIfGapful(Long num) {
        Long temp = num;
        Long ex = 1L;
        int size = 1;

        while (temp / 10 > 0) {
            temp /= 10;
            ex *= 10;
            size++;
        }

        if (size < 3) {
            return false;
        }

        Long gap = (num % 10) + (num / ex) * 10;

        return num % gap == 0;
    }

    public static boolean checkIfPerfectsquare(Long num) {
        double square;

        square = Math.sqrt(num);

        return square % 1 == 0;
    }

    public static boolean checkIfSunny(Long num) {
        return checkIfPerfectsquare(num + 1);
    }

    public static boolean checkIfSpy(Long num) {
        Long sum = 0L;
        Long exp = 1L;
        Long digit;

        if (num < 10) {
            return true;
        }

        while (num / 10 > 0) {
            digit = num % 10;

            sum += digit;
            exp *= digit;

            num /= 10;
        }

        sum += num;
        exp *= num;

        return sum == exp;
    }

    public static boolean checkIfJumping(Long num) {
        String number = Long.toString(num);

        for (int i = 0; i < number.length() - 1; i++) {
            if(number.charAt(i) != number.charAt(i + 1) + 1 && number.charAt(i) != number.charAt(i + 1) - 1) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkIfHappy(Long num) {
        Long sum;
        String number = Long.toString(num);
        int digit;

        if (num == 1)
            return true;

        do{
            sum = 0L;
            for(int i = 0; i < number.length(); i++) {
                digit = Character.getNumericValue(number.charAt(i));
                sum += digit * digit;
            }
            number = Long.toString(sum);
        } while (sum != 4 && sum != 1);

        return sum != 4;
    }
}
