package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class Main extends Checkers {

    public static final String[]PROPERTIES  = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD", "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD",
                                                "-BUZZ", "-DUCK", "-PALINDROMIC", "-GAPFUL", "-SPY", "-EVEN", "-ODD", "-SQUARE", "-SUNNY", "-JUMPING", "-HAPPY", "-SAD"};
    public static final String[][]EXCLUSIVE_PROPERTIES = {{"even", "odd"}, {"-even", "-odd"}, {"duck", "spy"}, {"sunny", "square"}, {"sad", "happy"}, {"-sad", "-happy"},
                                                          {"even", "-even"}, {"odd", "-odd"}, {"buzz", "-buzz"}, {"duck", "-duck"}, {"palindromic", "-palindromic"}, {"gapful", "-gapful"},
                                                          {"spy", "-spy"}, {"sunny", "-sunny"}, {"square", "-square"}, {"jumping", "-jumping"}, {"sad", "-sad"}, {"happy", "-happy"},};

    public static void main(String[] args) {
//        write your code here
        Scanner in = new Scanner(System.in);
        String stringNum;
        ArrayList<String> propList = new ArrayList<String>();
        ArrayList<String> propListIncorect = new ArrayList<String>();
        String parameter = "";
        Long num;
        Integer numToGo = null;

        System.out.println("Welcome to Amazing Numbers!");
        System.out.println("""
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.""");

        System.out.print("Enter a request:");
        stringNum =  in.nextLine();
        System.out.println();

        while (!stringNum.matches("exit") && !stringNum.matches("0")) {
            if(stringNum.matches("")) {
                System.out.println("""
                        Supported requests:
                        - enter a natural number to know its properties;
                        - enter two natural numbers to obtain the properties of the list:
                          * the first parameter represents a starting number;
                          * the second parameter shows how many consecutive numbers are to be printed;
                        - two natural numbers and properties to search for;
                        - a property preceded by minus must not be present in numbers;
                        - separate the parameters with one space;
                        - enter 0 to exit.""");
            } else {
                Scanner line = new Scanner(stringNum);
                num = line.nextLong();

                if(line.hasNext()) {
                    numToGo = line.nextInt();

                    while (line.hasNext()) {
                        parameter = line.next();
                        parameter = parameter.toLowerCase();
                        if (checkIfPropExist(new String[]{parameter.toLowerCase()} , PROPERTIES, true)) {
                            if (!isProbDuplicated(propList, parameter))
                                propList.add(parameter);
                        } else {
                            if (!isProbDuplicated(propListIncorect, parameter))
                                propListIncorect.add(parameter);
                        }
                    }
                }
                if (num > 0 && (numToGo == null ? 1 : numToGo) > 0 && propListIncorect.size() == 0 && !isExclusiveProp(propList.toArray(new String[propList.size()]))) {
                    check(num, numToGo == null ? 0 : numToGo, propList.toArray(new String[propList.size()]));
                } else {
                    if (propListIncorect.size() > 0) {
                        String incorecPropText = "";
                        incorecPropText += "The " + (propListIncorect.size() == 1 ? "property" : "properties") + " [";
                        for (String s : propListIncorect) {
                            incorecPropText += s.toUpperCase() + ", ";
                        }
                        incorecPropText = incorecPropText.substring(0, incorecPropText.length() - 2);
                        incorecPropText += "] " + (propListIncorect.size() == 1 ? "is" : "are") + " wrong.";

                        System.out.println(incorecPropText);

                        System.out.print("Available properties: [");
                        for (int i = 0; i < (PROPERTIES.length - 1 )/ 2; i++) {//print only half arr
                            System.out.print(PROPERTIES[i] + ", ");
                        }
                        System.out.print(PROPERTIES[(PROPERTIES.length - 1) / 2] + "]\n");
                    }

                    if(isExclusiveProp(propList.toArray(new String[propList.size()]))) {
                        System.out.println(errorExclusiveProp(propList.toArray(new String[propList.size()])));
                    }

                    if (num <= 0 && (numToGo == null ? 1 : numToGo) <= 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                        System.out.println("The second parameter should be a natural number.");
                    } else if (num <= 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                    } else if ((numToGo == null ? 1 : numToGo) <= 0) {
                        System.out.println("The second parameter should be a natural number.");
                    }
                }
            }
            System.out.print("Enter a request:");
            stringNum =  in.nextLine();
            System.out.println();

            //reset
            propList = new ArrayList<String>();;
            propListIncorect = new ArrayList<String>();
            numToGo = null;
        }

    }

    @Deprecated
    public static void check(Long num) {
        System.out.println("Properties of " + num);
        System.out.println("even: " + checkIfEven(num));
        System.out.println("odd: " + !checkIfEven(num));
        System.out.println("buzz: " + checkIfBuzz(num));
        System.out.println("duck: " + checkIfDuck(num));
        System.out.println("palindromic: " + checkIfPalindromic(num));
        System.out.println("gapful: " + checkIfGapful(num));
        System.out.println("spy: " + checkIfSpy(num));
    }

    @Deprecated
    public static void check(Long num, int numToGo) {
        String prop;
        for (int i = 0; i < numToGo; i++) {
            prop = num + " is" + prop(num);
            System.out.println(prop);
            num++;
        }
    }

    @Deprecated
    public static void check(Long start, int counter, String parameter) {
        String prop;
        for (int i = 0; i < counter;) {
            if (checkIfPropExist(new String[]{parameter}, propStringList(start), false)) {
                prop = start + " is" + prop(start);
                System.out.println(prop);
                i++;
            }
            start++;
        }
    }

    public static void check(Long num, int numToGo, String[]propList) {
        String prop;
        if (numToGo != 0 && propList.length > 0) {
            for (int j = 0; j < numToGo; ) {
                if (checkIfPropExist(propList, propStringList(num))) {
                    prop = num + " is" + prop(num);
                    System.out.println(prop);
                    j++;
                }
                num++;
            }
        } else if (numToGo != 0) { //The list of parameters don't exist
            for (int i = 0; i < numToGo; i++) {
                prop = num + " is" + prop(num);
                System.out.println(prop);
                num++;
            }
        } else { //The number to go don't exist
            System.out.println("Properties of " + num);
            System.out.println("even: " + checkIfEven(num));
            System.out.println("odd: " + !checkIfEven(num));
            System.out.println("buzz: " + checkIfBuzz(num));
            System.out.println("duck: " + checkIfDuck(num));
            System.out.println("palindromic: " + checkIfPalindromic(num));
            System.out.println("gapful: " + checkIfGapful(num));
            System.out.println("spy: " + checkIfSpy(num));
            System.out.println("sunny: " + checkIfSunny(num));
            System.out.println("square: " + checkIfPerfectsquare(num));
            System.out.println("jumping: " + checkIfJumping(num));
            System.out.println("happy: " + checkIfHappy(num));
            System.out.println("sad: " + !checkIfHappy(num));
        }
    }

    public static String prop(Long num) {
        String prop = "";

        if(checkIfEven(num)) {
            prop += " even,";
        } else {
            prop += " odd,";
        }

        if(checkIfBuzz(num)) {
            prop += " buzz,";
        }
        if(checkIfGapful(num)) {
            prop += " gapful,";
        }
        if(checkIfPalindromic(num)) {
            prop += " palindromic,";
        }
        if(checkIfDuck(num)) {
            prop += " duck,";
        }
        if(checkIfSpy(num)) {
            prop += " spy,";
        }
        if(checkIfSunny(num)) {
            prop += " sunny,";
        }
        if(checkIfPerfectsquare(num)) {
            prop += " square,";
        }
        if(checkIfJumping(num)) {
            prop += " jumping,";
        }

        if(checkIfHappy(num)) {
            prop += " happy,";
        } else {
            prop += " sad,";
        }
        return prop.substring(0, prop.length() - 1);
    }

    public static boolean checkIfPropExist(String[]filterProp, String[]propOfNum, boolean isCheckIfCorrect) {
        boolean isPropCorrect = false;
        for (String s : filterProp) {
            for (String value : propOfNum) {
                if (s.charAt(0) != '-') {
                    if (s.toLowerCase().matches(value.toLowerCase())) {
                        isPropCorrect = true;
                        break;
                    }
                } else {
                    if (s.toLowerCase().matches("-" + value.toLowerCase()) && !isCheckIfCorrect) {
                        return false;
                    } else if(s.toLowerCase().matches("-" + value.toLowerCase()) && isCheckIfCorrect) {
                        isPropCorrect = true;
                        break;
                    }
                }
            }

            if (isPropCorrect) {
                isPropCorrect = false;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIfPropExist(String[]filterProp, String[]propOfNum) {
        boolean isPropCorrect = false;
        for (String s : filterProp) {
            for (String value : propOfNum) {
                if (s.charAt(0) != '-') {
                    if (s.toLowerCase().matches(value.toLowerCase())) {
                        isPropCorrect = true;
                        break;
                    }
                } else {
                    if (s.toLowerCase().matches("-" + value.toLowerCase())) {
                        return false;
                    } else {
                        isPropCorrect = true;
                    }
                }
            }

            if (isPropCorrect) {
                isPropCorrect = false;
            } else {
                return false;
            }
        }
        return true;
    }

    public static String[] propStringList(Long num) {
        ArrayList<String> propList = new ArrayList<String>();

        if(checkIfEven(num)) {
            propList.add("even");
        } else {
            propList.add("odd");
        }

        if(checkIfBuzz(num)) {
            propList.add("buzz");
        }

        if(checkIfGapful(num)) {
            propList.add("gapful");
        }

        if(checkIfPalindromic(num)) {
            propList.add("palindromic");
        }

        if(checkIfDuck(num)) {
            propList.add("duck");
        }

        if(checkIfSpy(num)) {
            propList.add("spy");
        }

        if(checkIfSunny(num)) {
            propList.add("sunny");
        }

        if(checkIfPerfectsquare(num)) {
            propList.add("square");
        }

        if(checkIfJumping(num)) {
            propList.add("jumping");
        }

        if(checkIfHappy(num)) {
            propList.add("happy");
        } else {
            propList.add("sad");
        }

        return propList.toArray(new String[propList.size()]);
    }

    public static boolean isProbDuplicated(ArrayList<String> propList, String prop) {
        for (String s : propList) {
            if (prop.matches(s))
                return true;
        }
        return false;
    }

    public static boolean isExclusiveProp(String[]propList) {
        boolean isExclusice = false;
        if (propList != null) {
            for (String[]s : EXCLUSIVE_PROPERTIES) {
                isExclusice = checkIfPropExistEx(s, propList);

                if (isExclusice)
                    return true;
            }

            return false;
        } else {
            return false;
        }
    }


    public static String errorExclusiveProp(String[]propList) {
        String exclusive = "The request contains mutually exclusive properties: [";
        for (String[]s : EXCLUSIVE_PROPERTIES) {
            if(checkIfPropExistEx(s, propList)) {
                for (String t : s){
                    exclusive += t.toUpperCase() + ", ";
                }
            }
        }
        exclusive = exclusive.substring(0, exclusive.length() - 2) + "]\nThere are no numbers with these properties.";
        return exclusive;
    }

    public static boolean checkIfPropExistEx(String[]filter, String[]propList) {
        int i =0;
        boolean flag = false;
        for (String f : filter){
            for (String p : propList) {
                if(f.matches(p))
                    flag = true;
            }
            if (flag) {
                flag = false;
            } else {
                return false;
            }
        }
        return true;
    }
}
