package io.polybius.phonevalidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MobilePhoneNumberValidator {
    public ValidationResultDto validate(List<String> phoneNumbers) {
        ValidationResultDto result = new ValidationResultDto();
        result.invalidPhones = new ArrayList<>();
        result.validPhonesByCountry = new HashMap<>();
        for (int i = 0; i < phoneNumbers.size(); i++) {
            String phoneNumber = phoneNumbers.get(i);
            boolean isValid;
            String country = null;
            long leftBraceCount = phoneNumber.chars().filter(ch -> ch == '(').count();
            long rightBraceCount = phoneNumber.chars().filter(ch -> ch == ')').count();
            String formatNumber = phoneNumber;
            isValid = leftBraceCount == rightBraceCount;
            if (isValid) {
                formatNumber = phoneNumber
                        .replaceAll("\\s", "")
                        .replaceAll("\\(", "")
                        .replaceAll("\\)", "")
                        .replaceAll("-", "")
                        .replaceFirst("^\\+", "");
            }
            if (isValid) {
                if (formatNumber.startsWith("370")) {
                    country = "LT";
                    formatNumber = formatNumber.replaceFirst("^(370)", "");
                    isValid = formatNumber.startsWith("6") && formatNumber.length() == 8;
                } else if (formatNumber.startsWith("371")) {
                    country = "LV";
                    formatNumber = formatNumber.replaceFirst("^(371)", "");
                    isValid = formatNumber.startsWith("2") && formatNumber.length() == 8;
                } else if (formatNumber.startsWith("372")) {
                    country = "EE";
                    formatNumber = formatNumber.replaceFirst("^(372)", "");
                    isValid = formatNumber.startsWith("5") && (formatNumber.length() == 7 || formatNumber.length() == 8);
                } else if (formatNumber.startsWith("32")) {
                    country = "BE";
                    formatNumber = formatNumber.replaceFirst("^(32)", "");
                    isValid = formatNumber.length() == 9 && (formatNumber.startsWith("456") ||
                            formatNumber.startsWith("47") ||
                            formatNumber.startsWith("48") ||
                            formatNumber.startsWith("49"));
                } else {
                    isValid = false;
                }
            }

            if (isValid) {
                if (!result.validPhonesByCountry.containsKey(country)) {
                    result.validPhonesByCountry.put(country, new ArrayList<>());
                }

                result.validPhonesByCountry.get(country).add(phoneNumbers.get(0));
            } else {
                result.invalidPhones.add(phoneNumbers.get(i));
            }
        }
        return result;
    }
}
