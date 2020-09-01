package co.edu.javeriana2.cognitive.utilities;

public class SanitizeString {

    private SanitizeString() {

    }

    public static String sanitize(String param) {
        return param.replaceAll("[\n|\r|\t]", "_"); // NO SONAR
    }

}
