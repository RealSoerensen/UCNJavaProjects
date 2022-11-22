package Exercises.InterfaceImplentation;

public class TryMe {
    public static void main(String[] args) {
        OrderedPair<String> pair = new OrderedPair<String>("Hello", "World");
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
        pair.changeOrder();
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
    }
}
