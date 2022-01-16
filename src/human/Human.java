package human;
import dog.Dog;


public abstract class Human {
    protected final String name;
    protected final int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract void meet(Dog dog, Human human);

    public abstract void chooseDog(Dog dog);

    public abstract String playWithDog();

    public abstract String feedDog();

}
