public class Bob {
    public String hey(String s) {
        if(s.matches("([A-Z]|\\s|[0-9]|,)+\\!?")) {
            return "Whoa, chill out!";
        }

        if(s.matches("([A-Z]|\\s)+\\??")) {
            return "Calm down, I know what I'm doing!";
        }

        if(s.endsWith("?")) {
            return "Sure.";
        }

        return "Whatever.";
    }
}
