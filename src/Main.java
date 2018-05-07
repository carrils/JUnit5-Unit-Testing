//Sam Carrillo
//1-16-18

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> passwords = new ArrayList<String>();//the password database
        System.out.println("Enter in passwords then type \"done\" to evaluate them");

        //collect input from keyboard until "done" entered
        while(scan.hasNextLine()){
            passwords.add(scan.next());
            if(scan.next().equals("done")){
                break;
            }
        }

        //table title bar
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%1s %8s %7s %10s %10s %12s %8s %8s %13s",
                "PASSWORD", "TRIMMED", "DIGIT?", "UPPERCASE?", "LOWERCASE?","# SP. CHARS", "LENGTH", "ENTROPY", "ENTROPY EVAL");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");

        //run through the passwords and print their data out
        for(String password: passwords){
            System.out.format("%1s %8s %7s %10s %10s %12d %8d %8d %13s",
                    password, truncate(password, 20), hasDigit(password), hasUpperCase(password), hasLowerCase(password),
                    countSpecialCharacters(password,"!@#$%^&*()_+-=,./[]\\{}|;':\"<>?"),password.length(),
                    calculateEntropy(password,"!@#$%^&*()_+-=,./[]\\{}|;':\"<>?"),
                    evaluateEntropy(calculateEntropy(password,"!@#$%^&*()_+-=,./[]\\{}|;':\"<>?")));
            System.out.println();
        }
    }//end of main method

    //*****countSpecialCharacters Function*****
    public static int countSpecialCharacters(String s, String allowedCharacters){
        int result = 0;
        for(int i = 0; i<s.length(); i++){
            for(int j = 0; j<allowedCharacters.length(); j++){
                if(s.charAt(i)==allowedCharacters.charAt(j)){
                    result++;
                }
            }
        }
        return result;
    }
    //*****End of countSpecialCharacters Function*****

    //*****hasDigit Function*****
    public static boolean hasDigit(String s){
        char c;
        for(int i = 0;i<s.length();i++){
            c=s.charAt(i);
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }
    //*****End of hasDigitFunction*****

    //*****hasUpperCase Function*****
    public static boolean hasUpperCase(String s){
        char c;
        for(int i = 0; i<s.length();i++){
            c = s.charAt(i);
            if(Character.isUpperCase(c)){
                return true;
            }
        }
        return false;
    }
    //*****End of hasUpperCaseFunction*****

    //*****hasLowerCase Function*****
    public static boolean hasLowerCase(String s){
        char c;
        for(int i = 0; i<s.length();i++){
            c = s.charAt(i);
            if(Character.isLowerCase(c)){
                return true;
            }
        }
        return false;
    }
    //*****End of hasLowerCaseFunction*****

    //*****trimmedLength Function*****
    public static int trimmedLength(String s){
        int result = 0;
        result = s.trim().length();
        return result;

    }
    //*****End of trimmedLength Function*****

    //*****calculateRange Function*****
    public static int calculateRange(String s, String allowedCharacters){
        int range = 0;
        if(hasUpperCase(s)){
            range+=26;
        }
        if(hasLowerCase(s)){
            range+=26;
        }
        if(hasDigit(s)){
            range+=10;
        }
        range+=countSpecialCharacters(s,allowedCharacters);
        return range;
    }
    //*****End of calculateRange Function*****

    //*****truncate Function******
    public static String truncate(String s, int n){
        if(s.trim().length()>n){
            s = s.substring(0, n) + "...";
        }
        if(s.trim().length()<=n){
            return s;
        }
        return s;
    }
    //*****End of truncate Function*****

    //*****log2 Function*****
    public static double log2 (double x){
        double result = 0;
        result = Math.log(x) / Math.log(2.0);
        return result;
    }
    //*****End of log2 Function*****

    //*****calculateEntropy Function*****
    public static int calculateEntropy(String s, String allowedCharacters){
    double entropy;
    entropy = log2(Math.pow(calculateRange(s, allowedCharacters), trimmedLength(s)));//E=log2(R^L)
    return (int)(entropy);//cast double -> int
    }
    //*****End of calculateEntropy Function*****

    //*****evaluateEntropy Function*****
    public static String evaluateEntropy(int entropy){
        //this function takes an int since that's what calculateEntropy returns
        String evaluation="";
        if(entropy>=0 && entropy<=64){
            evaluation = "Very Weak";
        }else if(entropy>=65 && entropy<=80){
            evaluation="Weak";
        }else if(entropy>=81 && entropy<=112){
            evaluation="Moderate";
        }else if(entropy>=113 && entropy<=128){
            evaluation="Strong";
        }else if(entropy>128){
            evaluation="Very Strong";
        }
        return evaluation;
    }
    //*****End of evaluateEntropy*****
}
