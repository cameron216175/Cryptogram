public class Caesar {

    public static String encrypt(String text, int shift) {

        //Using StringBuilder for malleable string
        StringBuilder result = new StringBuilder();

        //Modulus so shifting can wrap around the alphabet
        shift = shift % 26;

        //Loop through each character in the string
        for (char c : text.toCharArray()) {

            //Uppercase encryption
            if (Character.isUpperCase(c)) {

                //Unsetting characters from ASCII assignment, shifting, resetting, then casting
                char encrypted = (char) ((c - 'A' + shift) % 26 + 'A');
                result.append(encrypted);
            }

            //Lowercase encryption
            else if (Character.isLowerCase(c)) {

                //Unsetting characters from ASCII assignment, shifting, resetting, then casting
                char encrypted = (char) ((c - 'a' + shift) % 26 + 'a');
                result.append(encrypted);

            }

            //Otherwise
            else {
                //Append anyway
                result.append(c);

            }

        }

        //Return
        return result.toString();

    }

}