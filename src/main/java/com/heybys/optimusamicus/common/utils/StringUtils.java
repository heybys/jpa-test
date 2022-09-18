package com.heybys.optimusamicus.common.utils;

import java.util.Random;

public class StringUtils {

  public static String generateRandomNumbers(Integer length) {
    Random random = new Random();

    return random
        .ints(48, 58)
        .limit(length)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }

  public static String generateRandomAlphabets(Integer length) {
    int leftLimit = 65;
    int rightLimit = 122;
    Random random = new Random();

    return random
        .ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 90 || i >= 97))
        .limit(length)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}
