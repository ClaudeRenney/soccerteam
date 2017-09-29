package com.company;


import java.io.*;
import java.util.*;

import static java.lang.System.in;

public class Team {

    HashMap<String, Integer> results = new HashMap<String, Integer>();



    public static void main(String[] args) throws FileNotFoundException {




//        new Team().go();


         {

            new Team().sorted();
        }


    }


//    public void go() {
//
//        readScores(); // reading them in and create the results board
//
//        System.out.println(results);
//
//
//    }

    public void sorted() throws FileNotFoundException {
        readScores();

            System.out.println(sortTableRows(results));
        }








void readScores() throws FileNotFoundException {

    // Scanner for user input
    Scanner user = new Scanner( in );
    String  inputFileName;

    // prepare the input file
    try {
    System.out.print("Input File Name: ");
    inputFileName = user.nextLine().trim();
    File file = new File( inputFileName );
    Scanner scan = new Scanner( file );
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line;
    while ((line = reader.readLine()) != null) {
                addScoresAndCalculateResults(line);

}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//    void readScores() {
//        try {
//
//            File file = new File("TeamScores.txt");
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                addScoresAndCalculateResults(line);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    void addScoresAndCalculateResults(String lineToParse) {
        // add a club and score to arraylist rawscores
        int totalscore;

        String[] match = lineToParse.split(","); // split the 2 clubs that played into match[0] and match[1]
        String[] clubscore1 = match[0].split(" "); // now split the first club name, clubscore1[0] and score, clubscore1[1]
        if (!results.containsKey(clubscore1[0])) { // only the first time will it be initialised with 0
            results.put(clubscore1[0], 0);
        }

        String[] clubscore2 = match[1].split(" "); // now split the second club name, clubscore2[0] and score clubscore2[1]
        if (!results.containsKey(clubscore2[0])) { // only the first time will it be initialised with 0
            results.put(clubscore2[0], 0);
        }

        if (Integer.parseInt(clubscore1[1]) == Integer.parseInt(clubscore2[1])) { // a draw is 1 point each
            totalscore = results.get(clubscore1[0]) + 1;
            results.put(clubscore1[0], totalscore);

            totalscore = results.get(clubscore2[0]) + 1;
            results.put(clubscore2[0], totalscore);

        } else if (Integer.parseInt(clubscore1[1]) > Integer.parseInt(clubscore2[1])) { // a winner gets 3 points
            totalscore = results.get(clubscore1[0]) + 3;
            results.put(clubscore1[0], totalscore);

        } else if (Integer.parseInt(clubscore1[1]) < Integer.parseInt(clubscore2[1])) { // a winner gets 3 points
            totalscore = results.get(clubscore2[0]) + 3;
            results.put(clubscore2[0], totalscore);



        }


    }

    public TreeMap<String, Integer> sortTableRows(Map<String, Integer> results) {

        CompareValues compareValues = new CompareValues(results);

        TreeMap<String, Integer> sortedMap = new TreeMap<>(compareValues);
        sortedMap.putAll(results);



            sortedMap.forEach((k, v) -> System.out.println(k + "=" + v + " "));


            return sortedMap;

        }
}



    class CompareValues implements Comparator<String> {

        Map<String, Integer> results;


        public CompareValues(Map<String, Integer> base) {
            this.results = base;
        }

        public int compare(String a, String b) {
            if (results.get(a) > results.get(b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }



