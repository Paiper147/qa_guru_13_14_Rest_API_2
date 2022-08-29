package utils;

import home.testDatas.TestDataDemowebshopHome;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomUtils {

    public static String getRandomString(int length) {
        // create a string of all characters
        String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        // create random string builder
        StringBuilder result = new StringBuilder();

        // create an object of Random class
        Random rnd = new Random();
        while (result.length() < length) {
            int index = rnd.nextInt(charset.length());
            result.append(charset.charAt(index));
        }
        return result.toString();
    }

    public static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static Long getRandomLong(Long min, Long max) {
        return min + (long) (Math.random() * (max - min));
//        return ThreadLocalRandom.current().nextLong(min, max);
    }

    public static String getRandomMessage(int min, int max) {
        String[] words = {"and", "or", "but", "because",
                "red", "white", "Jane", "John", "Bobby",
                "man", "woman", "fish", "elephant", "unicorn",
                "a", "the", "every", "some", "any", "all",
                "big", "tiny", "pretty", "bald", "small",
                "runs", "jumps", "talks", "sleeps", "walks",
                "loves", "hates", "sees", "knows", "looks for", "finds",
                ", ", ", ", ", ", ". ", ". "};

        StringBuilder message = new StringBuilder();
        int messageLength = getRandomInt(min, max);
        while (message.length() < messageLength) {
            int wordIndex = getRandomInt(0, words.length - 1);
            message.append(words[wordIndex] + " ");
        }

        return StringUtils.capitalize(message.toString())
                .replace("  ", " ")
                .replace(" ,", ",")
                .replace(" .", ".")
                .trim();
    }

    public static String getRandomNumberDesiredSize(int desiredSize) {
        long min = (long) Math.pow(10, desiredSize - 1);
        long max = 9 * (long) Math.pow(10, desiredSize - 1);
        return String.valueOf(getRandomLong(min, max));
    }

    public static String getRandomEmail() {
        long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        return getRandomString(5) + timestamp + TestDataDemowebshopHome.EMAIL_DOMAIN;
    }

    public static LocalDate getRandomBirthday() {
        int minDay = (int) LocalDate.of(1970, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
        Random random = new Random();

        long randomDay = minDay + random.nextInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static List<String> getRandomListStringsDesiredSize(String[] inputArray, int desiredOutputSize) {
        List<String> outputList = new ArrayList<>();

        List<Integer> listNumbers = new ArrayList<>();
        for (int i = 0; i < (inputArray.length); i++) {
            listNumbers.add(i);
        }
        Collections.shuffle(listNumbers);

        for (int i = 0; i < desiredOutputSize; i++) {
            outputList.add(inputArray[listNumbers.get(i)]);
        }
        return outputList;
    }

    public static String getRandomFromInputArray(String[] inputArray) {
        Random random = new Random();
        return inputArray[random.nextInt(inputArray.length)];
    }
}
