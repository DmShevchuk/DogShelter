package human;

import Exceptions.SalaryNotSpecifiedException;
import Exceptions.NoDogException;
import network.SocialNetwork;
import dog.Dog;


public class Person extends Human implements Recipients {
    private final int salary;
    private Dog pet;

    public Person(String name, int age, int salary) {
        super(name, age);
        this.salary = salary;
    }

    public Person(String name, int age) throws SalaryNotSpecifiedException {
        super(name, age);
        throw new SalaryNotSpecifiedException(name);
    }

    public int getSalary() {
        return salary;
    }

    public void likePost(SocialNetwork.Post post) {
        post.like();
    }

    public Dog getPet() {
        return pet;
    }

    public String getPetInfo() {
        try {
            if (pet == null) {
                throw new NoDogException(name);
            }
            return "У " + name + " есть " + pet;
        } catch (NoDogException e) {
            return e.getMessage();
        }

    }

    @Override
    public void adoptDog(Dog d) {
        pet = d;
    }

    @Override
    public void chooseDog(Dog dog) {
        meet(dog, dog.getOwner());
    }

    @Override
    public String playWithDog() {
        try {
            if (pet == null) {
                throw new NoDogException(name);
            }
            return pet.playWith();
        } catch (NoDogException e) {
            return e.getMessage();
        }
    }

    @Override
    public String feedDog() {
        try {
            if (pet == null) {
                throw new NoDogException(name);
            }
            return pet.feed();
        } catch (NoDogException e) {
            return e.getMessage();
        }
    }

    @Override
    public void meet(Dog dog, Human famousPerson) {
        famousPerson.meet(dog, this);
    }
}
