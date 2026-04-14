package org.example;

// Q2_BitcoinRate.java

import java.io.*;
import java.net.*;

public class Q2_BitcoinRate {

    public static void main(String[] args) {
        try {
            // API URL
            String urlStr = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd,inr,eur";
            URL url = new URL(urlStr);

            // Open connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Example response:
            // {"bitcoin":{"usd":..., "inr":7823123, "eur":...}}

            String json = response.toString();

            // Extract INR value manually
            int inrIndex = json.indexOf("\"inr\":");
            int start = inrIndex + 6;
            int end = json.indexOf(",", start);

            if (end == -1) end = json.indexOf("}", start);

            int inrValue = Integer.parseInt(json.substring(start, end));

            System.out.println("Bitcoin price in INR: " + inrValue);
            System.out.println("In words: " + numberToWords(inrValue));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Convert number to words (Indian system)
    static String numberToWords(int num) {
        String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
                "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

        String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

        if (num == 0) return "Zero";

        if (num < 20) return units[num];

        if (num < 100)
            return tens[num / 10] + " " + units[num % 10];

        if (num < 1000)
            return units[num / 100] + " Hundred " + numberToWords(num % 100);

        if (num < 100000)
            return numberToWords(num / 1000) + " Thousand " + numberToWords(num % 1000);

        if (num < 10000000)
            return numberToWords(num / 100000) + " Lakh " + numberToWords(num % 100000);

        return numberToWords(num / 10000000) + " Crore " + numberToWords(num % 10000000);
    }
}
