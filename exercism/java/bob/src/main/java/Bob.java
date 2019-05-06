public class Bob {
    public String hey(String s) {

        if (s.isEmpty() || s.isBlank() || s.matches("\\s+")) {
            return "Fine. Be that way!";
        }

        if (s.matches("[A-Z]([a-z]|\\s|[0-9]|[,'])+\\!?")) {
            return "Whatever.";
        }

        if (s.trim().endsWith("!") || s.matches("([A-Z]|\\s|[0-9]|[%\\^\\*@#'\\$\\(\\)\\!])+\\!?")) {
            return "Whoa, chill out!";
        }

        if (s.matches("([A-Z]|\\s)+\\??")) {
            return "Calm down, I know what I'm doing!";
        }

        if (s.trim().endsWith("?")) {
            return "Sure.";
        }

        return "Whatever.";
    }
}
