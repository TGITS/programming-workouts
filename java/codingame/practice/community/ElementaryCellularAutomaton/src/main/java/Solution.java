/* https://www.codingame.com/ide/puzzle/elementary-cellular-automaton */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        String[] neighborhood = new String[]{"111", "110", "101", "100", "011", "010", "001", "000"};
        Scanner in = new Scanner(System.in);
        int ruleInInt = in.nextInt();
        String rule = Integer.toBinaryString(ruleInInt);
        System.err.println("Given rule in decimal : " + ruleInInt);
        System.err.println("Rule converted in binary : " + rule);
        while (rule.length() < 8) {
            rule = "0" + rule;
        }
        System.err.println("Rule converted in binary with 0 padded in front : " + rule);
        int numberOfLinesToOutput = in.nextInt();
        System.err.println("Number of lines to output : " + numberOfLinesToOutput);
        String startPattern = in.next();
        System.err.println("Given start pattern : " + startPattern);
        String convertedStartPattern = startPattern.replaceAll("@", "1").replaceAll("\\.", "0");
        System.err.println("Converted start pattern : " + convertedStartPattern);
        Map<String, String> evolutionRule = createEvolutionRule(neighborhood, rule);
        String processedPattern = convertedStartPattern;
        System.out.println(processedPattern.replaceAll("1", "@").replaceAll("0", "\\."));
        for (int i = 0; i < numberOfLinesToOutput - 1; i++) {
            processedPattern = evolve(processedPattern, evolutionRule);
            System.out.println(processedPattern.replaceAll("1", "@").replaceAll("0", "\\."));
        }
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        //System.out.println(".....@.@.....");
    }

    public static Map<String, String> createEvolutionRule(String[] neighborhood, String rule) {
        Map<String, String> evolutionRule = new HashMap<>();

        for (int i = 0; i < rule.length(); i++) {
            //System.err.println("neighborhood[" + i + "] : " + neighborhood[i]);
            //System.err.println("Character.toString(rule.charAt(" + i + ")) : " + Character.toString(rule.charAt(i)));
            evolutionRule.put(neighborhood[i], Character.toString(rule.charAt(i)));
        }
        return evolutionRule;
    }

    public static String evolve(String startPattern, Map<String, String> evolutionRule) {
        StringBuilder finalPattern = new StringBuilder("");
        int patternSize = startPattern.length();
        int lastIndex = patternSize - 1;
        for (int i = 0; i < patternSize; i++) {
            String readPattern;
            if (i == 0) {
                readPattern = Character.toString(startPattern.charAt(lastIndex)) + Character.toString(startPattern.charAt(i)) + Character.toString(startPattern.charAt(i + 1));
            } else if (i == lastIndex) {
                readPattern = Character.toString(startPattern.charAt(i - 1)) + Character.toString(startPattern.charAt(i)) + Character.toString(startPattern.charAt(0));
            } else {
                readPattern = Character.toString(startPattern.charAt(i - 1)) + Character.toString(startPattern.charAt(i)) + Character.toString(startPattern.charAt(i + 1));
            }
            //System.err.println("Read Pattern : " + readPattern);
            finalPattern.append(evolutionRule.get(readPattern));
            //System.err.println("[WIP] Constructed Pattern : " + finalPattern.toString());
        }
        return finalPattern.toString();
    }
}