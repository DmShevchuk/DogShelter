import java.util.ArrayList;

import Exceptions.SalaryNotSpecifiedException;
import network.SocialNetwork;
import shelter.*;
import human.*;
import dog.*;


public class Main {
    public static Shelter SHELTER = Shelter.getInstance();
    /*
    ArrayList используется для удобства, по принципу работы похож на обычный массив: в угловых скобочках
    необходимо указать класс, объекты которого будут лежать в списке.

    - get(int idx) - получить элемент списка по индексу
    - add(Object obj) - добавить элемент в список
    - remove(Object obj / int idx) - удалить элемент из списка ИЛИ удалить элемент списка по индексу
    - size() - узнать длину списка
     */

    public static ArrayList<FamousPerson> famousPersonList = new ArrayList<FamousPerson>();
    public static ArrayList<Person> personList = new ArrayList<Person>();
    public static ArrayList<SocialNetwork> SNList = new ArrayList<SocialNetwork>();


    public static void main(String[] args) {
        createDogs();
        ArrayList<Dog> dogs = SHELTER.getDogList();

        print("В приюте 'Домашний' живут:");
        for (Dog dog : SHELTER.getDogList()) {
            print(dog.toString());
        }

        createFamousPeople();
        print("\nРаботать с собаками будут:");
        for (FamousPerson p : famousPersonList) {
            print(p.getName() + ", " + p.getPROFESSION().toString());
        }

        print("\nСамое время привлечь внимание общественности к приютам и бездомным собакам!");
        for (FamousPerson p : famousPersonList) {
            print(p.doWork());
        }

        // Каждая известная личность выбирает себе собаку
        for (int i = 0; i < famousPersonList.size(); i++) {
            famousPersonList.get(i).chooseDog(dogs.get(i));
        }

        print("\nТакже необходимо позаботиться о собаках:");
        for (FamousPerson p : famousPersonList) {
            print(p.getDog().heal());
            print((String) p.playWithDog());
        }

        createPeople();
        createSocialNetworks();
        print("");

        for (int i = 0; i < famousPersonList.size(); i++) {
            // Метод SocialNetwork.Publisher.publish выводит результат работы следующей строки в консоль!
            famousPersonList.get(i).createPost(SNList.get(i % 3));
        }

        // Пробегаем по всем социальным сетям и по каждому посту в них, чтобы поставить лайки
        print("\nОбщество активно пользуется соцсетями!");
        for (SocialNetwork SN : SNList) {
            SocialNetwork.Post[] postList = SN.getPostStorage();

            for (int i = 0; i < personList.size(); i++) {
                personList.get(i).likePost(postList[i % postList.length]);
            }

            for (SocialNetwork.Post post : postList) {
                print(post.getPostInfo());
            }
        }

        print("");

        for (int i = 0; i < personList.size(); i++) {
            try {
                Dog dog = dogs.get(i);
                Person person = personList.get(i);
                if (SHELTER.applyForDog(dog, person)) {
                    person.chooseDog(dog);
                }
            } catch (IndexOutOfBoundsException e) {
                print("Что-то пошло не так! Либо все собаки обрели хозяев, либо больше нет желающих приютить питомца.");
            }
        }

        if (SHELTER.getDogList().size() > 0) {
            print("\nК сожалению, в приюте осталось " + SHELTER.getDogList().size() + "собак.");
        } else {
            print("\nВсе собаки нашли хозяев!\nНовые хозяева сразу угостили питомцев вкусной едой:");
            for (Person p : personList) {
                print((String) p.feedDog());
            }
        }

    }


    public static void createDogs() {
        SHELTER.addDog(new Dog("Арчи", Breed.CHIHUAHUA, 0.5, 6000));
        SHELTER.addDog(new Dog("Монро", Breed.BOXER, 1, 7500));
        SHELTER.addDog(new Dog("Локи", Breed.CORGI, 2.5, 10000));
        SHELTER.addDog(new Dog("Мики", Breed.SHEEPDOG, 3, 7000));
        SHELTER.addDog(new Dog("Оскар", Breed.DACHSHUND, 1.5, 4000));
        SHELTER.addDog(new Dog("Отис", Breed.POODLE, 5, 9500));
    }

    public static void createFamousPeople() {
        famousPersonList.add(new FamousPerson("Владимир Ленин", 50, FamousProfessions.POLITICIAN));
        famousPersonList.add(new FamousPerson("Юрий Дудь", 35, FamousProfessions.JOURNALIST));
        famousPersonList.add(new FamousPerson("Алексей Шевцов", 36, FamousProfessions.BLOGGER));
        famousPersonList.add(new FamousPerson("Павел Дуров", 37, FamousProfessions.BUSINESSMAN));
        famousPersonList.add(new FamousPerson("Том Харди", 44, FamousProfessions.ACTOR));
        famousPersonList.add(new FamousPerson("Алишер Моргенштерн", 30, FamousProfessions.MUSICIAN));
    }

    public static void createPeople() {
        personList.add(new Person("Сергей Петров", 25, 50000));
        personList.add(new Person("Анна Кожемякина", 35, 35000));
        personList.add(new Person("Варвара Сверчкова", 19, 40000));
        personList.add(new Person("Владислав Ежиков", 24, 70000));
        personList.add(new Person("Александ Лосин", 23, 80000));

        // Попытка создать человека без указания его месячного дохода
        try {
            personList.add(new Person("Святослав Рогов", 20));
        } catch (SalaryNotSpecifiedException e) {
            personList.add(new Person("Святослав Рогов", 20, 65000));
        }
    }

    public static void createSocialNetworks() {
        SNList.add(new SocialNetwork("Instagram"));
        SNList.add(new SocialNetwork("VK"));
        SNList.add(new SocialNetwork("Facebook"));
    }


    public static void print(String text) {
        Output toPrint = new Output() {
            @Override
            public void makeOutput(String text) {
                System.out.println(text);
            }

            @Override
            public void makeOutput(int number) {
            }
        };
        toPrint.makeOutput(text);
    }

    public static void print(int number) {
        Output toPrint = new Output() {
            @Override
            public void makeOutput(String text) {
            }

            @Override
            public void makeOutput(int number) {
                System.out.println(number);
            }
        };
        toPrint.makeOutput(number);
    }
}
