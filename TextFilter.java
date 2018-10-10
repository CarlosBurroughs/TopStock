import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CS 180 - Project #1
 * <p>
 * This project will operate as a word/text censoring tool
 *
 * @author Juan Carlos Burroughs, burrouj@purdue.edu
 * @version October 5, 2018
 */
public class TextFilter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Print hello message
        System.out.println("Welcome to TextFilter!");

        // Value to keep track of if the user wants to keep filtering text
        boolean keepFiltering = true;

        do {
            //region
            // Print options for the user to select
            System.out.println("Please select one of the following filtering options: \n");
            System.out.println("1. Filter Word\n" +
                    "2. Find-And-Replace\n" +
                    "3. Censor Information");

            // Save their choice
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                // PART 1 - Censoring Words


                String passage = "";  // The text to be filtered
                System.out.println("Please enter the passage you would like filtered: ");

                passage = scanner.nextLine();

                String word;  // The word to be censored from the text phrase
                System.out.println("Please enter the word you would like to censor: ");

                word = scanner.nextLine();


                System.out.println("Uncensored: ");
                System.out.println(passage);

                if (passage.contains(word)) {
                    if(word.equals(passage)){

                        String censored = "";
                        for(int index = 0; index<word.length(); index++){
                            censored += "X";
                        }
                        System.out.println("Censored: ");
                        System.out.println(censored);

                    }else{
                        String censored = "";

                        for (int index = 0; index < word.length(); index++) {
                            censored += "X";
                        }
                        String censoredPassage = passage.replaceAll("\\b" + word + "\\b", censored);
                        System.out.println("Censored: ");
                        System.out.println(censoredPassage);
                    }

                }


            } else if (choice == 2) {

                // PART 2 - Replacing Words


                String passage = "";  // The text to be filtered
                System.out.println("Please enter the passage you would like filtered: ");


                passage = scanner.nextLine();


                String replace;  // The word to be filtered from the text phrase
                System.out.println("Please enter the word you would like to replace: ");

                replace = scanner.nextLine();


                String insert;  // The word to be inserted in the text phrase
                System.out.println("Please enter word you would like to insert: ");

                insert = scanner.nextLine();


                System.out.println("Uncensored: ");
                System.out.println(passage);


                if (passage.contains(replace)) {

                    String censoredPassage = passage.replaceAll("\\b" + replace + "\\b", insert);
                    System.out.println("Censored: ");
                    System.out.println(censoredPassage);
                }


            } else if (choice == 3) {

                // PART 3 - Censoring Personal Information


                /*
                 * DO NOT ALTER THIS CODE! This formatting is imperative to the completion of this task.
                 *
                 * Keep asking for input until the user enters an empty line
                 * If they enter an empty line and the phrase is empty, keep waiting for input
                 */
                String passage = "";  // String for holding text to be filtered

                System.out.println("Please enter the phrase you would like to censor information from: ");

                while (true) {

                    // Obtain a line from the user
                    String temp = scanner.nextLine();

                    if (!passage.isEmpty() && temp.isEmpty()) {
                        break;
                    } else if (passage.isEmpty() && temp.isEmpty()) {
                        continue;
                    }


                    // Add the contents of temp into the phrase
                    passage += temp;


                    // Append a newline character to each line for parsing
                    // This will separate each line the user enters
                    // To understand how input is formatted in Part 3, please refer to the handout.
                    passage += '\n';
                }

                // Print the uncensored passage
                System.out.println("Uncensored: ");
                System.out.println(passage);

                //Here I will pass through the string with a for
                String[] split = passage.split("\n");

                String name = "";
                String nameCensoring;
                String email = "";
                String phone = "";


                for (int index = 0; index < split.length; index++) {

                    if (split[index].contains("Name: ")) {

                        name = split[index].substring(6);
                        if (name.length()>0) {
                            //int indexSpace = name.indexOf(" ");

                            nameCensoring = "Name: ";
                            nameCensoring += name.charAt(0) + "*";

                        /*for(int index2 = 1; index2<name.length();index2++){

                            censoring += "*";

                            if(name.substring(indexSpace) == " "){
                                censoring += " ";
                                continue;
                            }
                        }
                        */
                            String sub = name.substring(1);

                            String[] nameSplit = sub.split(" ");

                            for (int index3 = 0; index3 < nameSplit.length; index3++) {
                                for (int index4 = 0; index4 < nameSplit[index3].length() -1; index4++) {

                                    nameCensoring += "*";
                                }

                                if (index3 < nameSplit.length - 1) {
                                    nameCensoring += " ";
                                }
                            }


                            nameCensoring += name.charAt(name.length() - 1);


                            split[index] = nameCensoring;


                        } else {
                            System.out.println("Name: ");
                        }




                    } else if (split[index].contains("Email: ")) {
                        if(split[index].length()>0 && split[index].contains("@")){
                            email = split[index].substring(7);

                            int indexAt = email.indexOf("@");
                            //String sub = email.substring(indexAt);

                            int indexDot = email.indexOf(".");

                            String censoring = "Email: ";
                            censoring += email.charAt(0);

                            for (int index2 = 1; index2 < indexAt; index2++) {
                                censoring += "*";
                            }
                            censoring += "@";
                            censoring += email.charAt(indexAt + 1);

                            for (int i = indexAt + 1; i < indexDot; i++) {
                                censoring += "*";
                            }
                            censoring += email.substring(indexDot);



                            split[index] = censoring;
                        }else{
                            System.out.println("Email: ");
                        }



                    } else if (split[index].contains("Phone: ")) {
                        if(split[index].length()>0 && split[index].length() == 19){
                            phone = split[index].substring(7);

                            String censoring = "Phone: ";
                            for (int index2 = 0; index2 < 2; index2++) {

                                censoring += "***-";
                            }
                            censoring += phone.substring(8);


                            split[index] = censoring;

                        }



                    }

                }
                // Print the censored passage
                String newStringFinal = String.join("\n", split);

                System.out.println("Censored: ");

                System.out.println(newStringFinal);

            } else {

                // They entered a number that was not 1, 2 or 3
                System.out.println("The option you chose was invalid!");

            }


            System.out.println("Would you like to keep filtering? Yes/No");

            String userSelection = scanner.nextLine();

            if (userSelection.equals("Yes")) {
                keepFiltering = true;
            } else {
                keepFiltering = false;
            }


            //endregion

        } while (keepFiltering);

        System.out.println("Thank you for using TextFilter!");

    }
}   //END
