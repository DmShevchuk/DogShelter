package shelter;

import dog.Dog;
import human.Person;

import java.util.ArrayList;

public final class Shelter {
    private final String name = "Домашний";
    private ArrayList<Dog> dogList = new ArrayList<Dog>();


    // Реализация синглтона
    private static Shelter instance;

    private Shelter() {
    }

    public static Shelter getInstance() {
        if (instance == null) {
            instance = new Shelter();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public int getDogQuantity() {
        int c = 0;
        for (Dog d : dogList) {
            if (d != null) {
                c++;
            }
        }
        return c;
    }

    public ArrayList<Dog> getDogList() {
        return (ArrayList<Dog>) dogList.clone();
    }

    public void addDog(Dog d) {

        dogList.add(d);
    }

    public boolean applyForDog(Dog dog, Person person) {
        // Если доход в три раза больше содержания собаки И у собаки ещё нет хозяина И человеку >= 18 лет
        return (person.getSalary() / 3 > dog.getMonthlyCost()) && (dog.getOwner().getClass() != Person.class) &&
                (person.getAge() >= 18);
    }


    public String removeDog(Dog d) {

        try {
            dogList.remove(d);
            return "Собака " + d.getName() + " отправляется домой к своим новым хозяевам!";
        } catch (Exception e) {
            return "А такой собаки в приюте нет!";
        }
    }


}
