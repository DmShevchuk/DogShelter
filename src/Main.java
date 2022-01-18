import java.util.ArrayList;

import Exceptions.SalaryNotSpecifiedException;
import network.SocialNetwork;
import shelter.*;
import human.*;
import dog.*;


public class Main {
    public static Shelter SHELTER = Shelter.getInstance();

    public static FamousPerson[] famousPersonList = new FamousPerson[6];
    public static Person[] personList = new Person[6];
    public static SocialNetwork[] SNList = new SocialNetwork[3];


    public static void main(String[] args) {
        createDogs();
        Dog[] dogs = SHELTER.getDogList();

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
        for (int i = 0; i < famousPersonList.length; i++) {
            famousPersonList[i].chooseDog(dogs[i]);
        }

        print("\nТакже необходимо позаботиться о собаках:");
        for (FamousPerson p : famousPersonList) {
            print(p.getDog().heal());
            print((String) p.playWithDog());
        }

        createPeople();
        createSocialNetworks();
        print("");

        for (int i = 0; i < famousPersonList.length; i++) {
            // Метод SocialNetwork.Publisher.publish выводит результат работы следующей строки в консоль!
            famousPersonList[i].createPost(SNList[i % 3]);
        }

        // Пробегаем по всем социальным сетям и по каждому посту в них, чтобы поставить лайки
        print("\nОбщество активно пользуется соцсетями!");
        for (SocialNetwork SN : SNList) {
            SocialNetwork.Post[] postList = SN.getPostStorage();

            for (int i = 0; i < personList.length; i++) {
                personList[i].likePost(postList[i % postList.length]);
            }

            for (SocialNetwork.Post post : postList) {
                print(post.getPostInfo());
            }
        }

        print("");

        for (int i = 0; i < personList.length; i++) {
            try {
                Dog dog = dogs[i];
                Person person = personList[i];
                if (SHELTER.applyForDog(dog, person)) {
                    person.chooseDog(dog);
                }
            } catch (IndexOutOfBoundsException e) {
                print("Что-то пошло не так! Либо все собаки обрели хозяев, либо больше нет желающих приютить питомца.");
            }
        }


        // Необходимо посчитать, сколько собак осталось в приюте
        int count = 0;
        for (int i = 0; i < SHELTER.getDogList().length; i++) {
            if (SHELTER.getDogList()[i] != null) {
                count++;
            }
        }

        if (count > 0) {
            print("\nК сожалению, в приюте осталось " + count + " собак.");
        } else {
            print("\nВсе собаки нашли хозяев!\nНовые хозяева сразу угостили питомцев вкусной едой:");
            for (Person p : personList) {
                print(p.feedDog());
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
        famousPersonList[0] = new FamousPerson("Владимир Ленин", 50, FamousProfessions.POLITICIAN);
        famousPersonList[1] = new FamousPerson("Юрий Дудь", 35, FamousProfessions.JOURNALIST);
        famousPersonList[2] = new FamousPerson("Алексей Шевцов", 36, FamousProfessions.BLOGGER);
        famousPersonList[3] = new FamousPerson("Павел Дуров", 37, FamousProfessions.BUSINESSMAN);
        famousPersonList[4] = new FamousPerson("Том Харди", 44, FamousProfessions.ACTOR);
        famousPersonList[5] = new FamousPerson("Алишер Моргенштерн", 30, FamousProfessions.MUSICIAN);
    }

    public static void createPeople() {
        personList[0] = new Person("Сергей Петров", 25, 50000);
        personList[1] = new Person("Анна Кожемякина", 35, 35000);
        personList[2] = new Person("Варвара Сверчкова", 19, 40000);
        personList[3] = new Person("Владислав Ежиков", 24, 70000);
        personList[4] = new Person("Александ Лосин", 23, 80000);

        // Попытка создать человека без указания его месячного дохода
        try {
            personList[5] = new Person("Святослав Рогов", 20);
        } catch (SalaryNotSpecifiedException e) {
            personList[5] = new Person("Святослав Рогов", 20, 65000);
        }
    }

    public static void createSocialNetworks() {
        SNList[0] = new SocialNetwork("Instagram");
        SNList[1] = new SocialNetwork("VK");
        SNList[2] = new SocialNetwork("Facebook");
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
