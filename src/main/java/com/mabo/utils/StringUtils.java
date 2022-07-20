package com.mabo.utils;

public class StringUtils {
    public static String encrypt(String mnw) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (index = 1; index < mnw.length(); index += 2) {
            char c1 = mnw.charAt(index - 1);
            char c2 = mnw.charAt(index);
            sb.append((char) (((int) c1 << 8) + (int) c2));
        }
        if (index == mnw.length()) {
            sb.append(mnw.charAt(index - 1));
        }
        return sb.toString();
    }

    public static String decrypt(String miw) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < miw.length(); i++) {
            int c = miw.charAt(i);
            String binaryString = Integer.toBinaryString(c);
            if (binaryString.length() < 8) {
                sb.append((char) c);
                continue;
            }
            int mnwi1 = Integer.parseInt(binaryString.substring(0, binaryString.length() - 8), 2);
            int mnwi2 = Integer.parseInt(binaryString.substring(binaryString.length() - 8), 2);
            sb.append((char) mnwi1);
            sb.append((char) mnwi2);
        }
        return sb.toString();
    }
}
