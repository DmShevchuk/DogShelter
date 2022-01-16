package Exceptions;

public class NoDogException extends NullPointerException {
    private final String name;

    public NoDogException(String name) {
        super("Попытка взаимодействия с несуществующей собакой!");
        this.name = name;
    }

    @Override
    public String getMessage() {
        return "У " + name + " нет собаки.";
    }
}
