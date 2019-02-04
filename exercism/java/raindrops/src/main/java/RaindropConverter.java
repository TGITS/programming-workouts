class RaindropConverter {

    String convert(int number) {
        StringBuffer result = new StringBuffer();
        boolean notDivisibleBy35or7 = true;

        if (number % 3 == 0){
            result.append("Pling");
            notDivisibleBy35or7 = false;
        }

        if (number % 5 == 0) {
            result.append("Plang");
            notDivisibleBy35or7 = false;
        }

        if (number % 7 == 0) {
            result.append("Plong");
            notDivisibleBy35or7 = false;
        }

        if (notDivisibleBy35or7) {
            result.append(number);
        }

        return result.toString();
    }

}
