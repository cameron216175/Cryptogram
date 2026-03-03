package src.main.java;

public class test {
    public static void main(String[] args) {
        System.out.println("\u001b[38;5;214m╭──────────────────────────────────────────────────────────────────────────────────────────────────────────────╮");
        System.out.println("\u001b[34m    █████████                                  █████                                                         ");
        System.out.println("   ███░░░░░███                                ░░███                                                          ");
        System.out.println("  ███     ░░░  ████████  █████ ████ ████████  ███████    ██████   ███████ ████████   ██████   █████████████  ");
        System.out.println(" ░███         ░░███░░███░░███ ░███ ░░███░░███░░░███░    ███░░███ ███░░███░░███░░███ ░░░░░███ ░░███░░███░░███ ");
        System.out.println(" ░███          ░███ ░░░  ░███ ░███  ░███ ░███  ░███    ░███ ░███░███ ░███ ░███ ░░░   ███████  ░███ ░███ ░███ ");
        System.out.println(" ░░███     ███ ░███      ░███ ░███  ░███ ░███  ░███ ███░███ ░███░███ ░███ ░███      ███░░███  ░███ ░███ ░███ ");
        System.out.println("  ░░█████████  █████     ░░███████  ░███████   ░░█████ ░░██████ ░░███████ █████    ░░████████ █████░███ █████");
        System.out.println("   ░░░░░░░░░  ░░░░░       ░░░░░███  ░███░░░     ░░░░░   ░░░░░░   ░░░░░███░░░░░      ░░░░░░░░ ░░░░░ ░░░ ░░░░░");
        System.out.println("                          ███ ░███  ░███                         ███ ░███                                    ");
        System.out.println("                         ░░██████   █████                       ░░██████                                     ");
        System.out.println("                          ░░░░░░   ░░░░░                         ░░░░░░                                      ");
        System.out.println("\u001b[38;5;214m╰──────────────────────────────────────────────────────────────────────────────────────────────────────────────╯\u001b[0m");

        System.out.print("\u001b[38;5;214m╭─────╥");
        for (int i = 1; i <= 9; i++) {
            System.out.print("\u001b[38;5;214m─────╥");
        }
        System.out.println("\u001b[38;5;214m─────╮");
        box("┄");
        System.out.print("\u001b[38;5;214m╰─────╫");
        for (int i = 1; i <= 9; i++) {
            System.out.print("\u001b[38;5;214m─────╫");
        }
        System.out.println("\u001b[38;5;214m─────╯");
        box("a");
        box("3");

    }



    public test () {

    }

    public static void box(String letter) {


        for (int i = 1; i <= 11; i++) {
            System.out.print("\u001b[34m   "+ letter +"  \u001b[0m");
        }
        System.out.println();
    }
    
}
