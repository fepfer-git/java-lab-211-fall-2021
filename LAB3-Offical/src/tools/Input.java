package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.List;

public class Input {

    private static Scanner sc = new Scanner(System.in);

    public static int inputAnInteger(String inputMsg, String errorMsg) {
        int n;
        while (true) {
            try {
                System.out.println(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double inputADouble(String inputMsg, String errorMsg) {
        double n;
        while (true) {
            try {
                System.out.println(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String inputAStringCannotBeEmpty(String inputMsg, String errorMsg) {
        String str;
        while (true) {
            System.out.println(inputMsg);
            str = sc.nextLine().trim();
            if (str.length() == 0 || str.isEmpty()) {
                System.out.println(errorMsg);
            } else {
                return str;
            }
        }

    }

    public static String inputAString(String inputMsg) {
        String str;
        System.out.println(inputMsg);
        str = sc.nextLine().trim();
        return str;
    }

    public static Date inputDateCannotBeEmpty(String inputMsg, String errorMsg) {
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        while (true) {

            try {

                while (true) {
                    System.out.println(inputMsg);
                    sdf.setLenient(false);
                    date = sdf.parse(sc.nextLine());

                    return date;
                }

            } catch (ParseException ex) {
                System.out.println(errorMsg);
            }
        }

    }

    public static Date inputDate(String inputMsg, String errorMsg) {
        Date date;
        String inputDate;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        while (true) {

            try {

                while (true) {
                    System.out.println(inputMsg);
                    inputDate = sc.nextLine();
                    if (inputDate.length() == 0) {
                        return null;
                    }

                    sdf.setLenient(false);
                    date = sdf.parse(inputDate);

                    return date;

                }

            } catch (ParseException ex) {
                System.out.println(errorMsg);
            }
        }

    }

    public static Date getDate(String inputDate) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            sdf.setLenient(false);
            date = sdf.parse(inputDate);
        } catch (ParseException ex) {
            System.out.println("Wrong");
        }
        return date;
    }

    public static int compareDate(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            return -1;
        } else if (date1.getTime() == date2.getTime()) {
            return 0;
        } else {
            return 1;
        }
    }

    public static long dayBetweenTwoDate(Date date1, Date date2) {
        long difference = ((date2.getTime() - date1.getTime()) / 86400000);
        return Math.abs(difference);
    }

    public static int inputChoice() {

        while (true) {
            String choice = inputAString("");
            if (choice.equals("1")) {
                return 1;
            } else if (choice.equals("0")) {
                return 0;
            } else {
                System.out.println("Invalid choice, please try again");
            }
        }
    }
    
}
