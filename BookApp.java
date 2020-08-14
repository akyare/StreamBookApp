package be.intecbrussel;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookApp {

    public static void main(String[] args) {
        Person auth1 = new Person("Rhiannon", "Frater", LocalDate.of(1980, 9, 25));
        Person auth2 = new Person("Mira", "Grant", LocalDate.of(1975, 7, 20));
        Person auth3 = new Person("Jonathan", "Maberry", LocalDate.of(1981, 8, 19));
        Person auth4 = new Person("Jesse", "Petersen", LocalDate.of(1970, 1, 23));
        Person auth5 = new Person("Alden", "Bell", LocalDate.of(1973, 5, 25));


        Book book1 = new Book("The First Days", auth1, LocalDate.of(2008, 8, 14), "Zombie");
        Book book2 = new Book("Feed", auth2, LocalDate.of(2010, 10, 4), "Horror");
        Book book3 = new Book("Rot & Ruin", auth3, LocalDate.of(2010, 9, 14), "Fiction");
        Book book4 = new Book("Married with Zombies", auth4, LocalDate.of(2010, 9, 1), "Humor");
        Book book5 = new Book("Fighting to Survive", auth1, LocalDate.of(2016, 2, 17), "Horror");

        Book[] books = new Book[]{book1, book2, book3, book4, book5};

//        Arrays.stream(books).sorted().forEach(System.out::println);

        System.out.println("-----getNewestBook----");
        System.out.println(getNewestBook(books));
        System.out.println("------printyoungestWriter------");
        printyoungestWriter(books);
        System.out.println("------printSortedByTitle------");
        printSortedByTitle(books);
        System.out.println("------countBooksPerAuthor------");
        countBooksPerAuthor(books);
        System.out.println("------printBooksReleasedIn2016------");
        printBooksReleasedIn2016(books);

    }

    public static Book getNewestBook(Book[] books) {
        return Stream.of(books)
                .reduce((first, second) -> second)
                .orElse(null);
    }

    public static void printyoungestWriter(Book[] books) {
        Book bookYoungestAuth = Stream.of(books)
                .reduce((book1, book2) -> {
                    if (book1.getAuthor().getDateOfBirth().isBefore(book2.getAuthor().getDateOfBirth())) {
                        return book1;
                    }
                    return book2;
                })
                .orElse(null);
        System.out.println(bookYoungestAuth.getAuthor());
    }

    public static void printSortedByTitle(Book[] books) {
        Stream.of(books)
                .sorted()
                .forEach(System.out::println);

    }

    public static void countBooksPerAuthor(Book[] books) {
        Map<String, Long> counted = Stream.of(books)
                .collect(Collectors.groupingBy(book -> book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName(), Collectors.counting()));

        System.out.println(counted);
    }

    public static void printBooksReleasedIn2016(Book[] books) {
        Stream.of(books)
                .filter(book -> book.getReleaseDate().getYear() == 2016)
                .forEach(System.out::println);
    }

}
