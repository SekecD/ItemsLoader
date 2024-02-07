package ru.mastaa.qub.Packets;

public class PacketDecoder {
    public String decodeAtIndex(String s, int k) {
        long decodedLength = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                decodedLength *= (c - '0');
            } else {
                decodedLength++;
            }
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int repeatCount = c - '0';
                decodedLength /= repeatCount;
                k %= decodedLength;

                if (k == 0 && Character.isLetter(s.charAt(i - 1))) {
                    return String.valueOf(s.charAt(i - 1));
                }
            } else {
                if (k == decodedLength || k == 0) {
                    return String.valueOf(c);
                }
                decodedLength--;
            }
        }
            throw new IllegalArgumentException("invalid");
    }
}
