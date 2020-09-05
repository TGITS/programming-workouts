class ReverseString {
    static reverse(s: string): string {
        if (s.length == 0) {
            return s;
        } else {
            let indexLast = s.length - 1;
            return s.charAt(indexLast) + ReverseString.reverse(s.substring(0, indexLast));
        }
    }
}

export default ReverseString
