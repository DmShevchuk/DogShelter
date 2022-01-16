package human;

import Exceptions.NoDogException;
import dog.Dog;
import network.*;
import shelter.Shelter;

import java.util.Formatter;

public class FamousPerson extends Human implements Presenters {
    private FamousProfessions PROFESSION;
    private Dog dog;

    public FamousPerson(String name, int age, FamousProfessions p) {
        super(name, age);
        this.PROFESSION = p;
    }

    public FamousProfessions getPROFESSION() {
        return PROFESSION;
    }

    public Dog getDog() {
        return dog;
    }

    public String doWork() {
        return name + " " + PROFESSION.attractAttention();
    }

    public void createPost(SocialNetwork NW) {
        NW.new Post(dog.toString(), this.name, this.PROFESSION);
    }

    @Override
    public void chooseDog(Dog d) {
        dog = d;
        dog.setOwner(this);
    }

    @Override
    public String playWithDog() {
        try {
            if (dog == null) {
                throw new NoDogException(name);
            }
            return dog.playWith();
        } catch (NoDogException e) {
            return e.getMessage();
        }
    }

    @Override
    public String feedDog() {
        try {
            if (dog == null) {
                throw new NoDogException(name);
            }
            return dog.feed();
        } catch (NoDogException e) {
            return e.getMessage();
        }
    }

    @Override
    public void meet(Dog dog, Human person) {

        class DogChanger {
            private final Dog dog;
            public final Person receiver;
            private final FamousPerson giver;

            DogChanger(Dog d, Person p, FamousPerson fp) {
                this.dog = d;
                this.receiver = p;
                this.giver = fp;
            }

            private void passDog() {

                giver.giveDog();
                receiver.adoptDog(dog);
                dog.setOwner(receiver);
                Shelter.getInstance().removeDog(dog);

                System.out.println(dog.getName() + " перешла от попечителя " + giver.getName() + " к хозяину " + receiver.getName());
            }
        }

        DogChanger dogChanger = new DogChanger(dog, (Person) person, this);
        dogChanger.passDog();

    }

    public void giveDog() throws NoDogException {
        if (dog != null) {
            dog = null;
        } else {
            throw new NoDogException(name);
        }
    }
}
