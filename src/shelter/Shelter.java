package shelter;

import dog.Dog;
import human.Person;

public final class Shelter {
    private final String name = "Домашний";
    private Dog[] dogList = new Dog[1];


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

    public Dog[] getDogList() {
        return dogList.clone();
    }

    public void addDog(Dog dog) {
        // Если место в массиве собак закончилось, создаётся новый массив с длиной = длинаТекущегоМассива + 1
        if(dogList[dogList.length - 1] != null){
            addPlaceForDog();
        }
        for(int i = 0; i < dogList.length; i++){
            if(dogList[i] == null){
                dogList[i] = dog;
            }
        };
    }

    public void addPlaceForDog(){
        Dog[] tmpArr = getDogList();
        dogList = new Dog[tmpArr.length + 1];
        System.arraycopy(tmpArr, 0, dogList, 0, tmpArr.length);
    }

    public boolean applyForDog(Dog dog, Person person) {
        // Если доход в три раза больше содержания собаки И человеку >= 18 лет
        return (person.getSalary() / 3 > dog.getMonthlyCost()) && (person.getAge() >= 18);
    }


    public void removeDog(Dog dog) {
        for(int i = 0; i < dogList.length; i++){
            if(dogList[i] != null && dogList[i].equals(dog)){
                dogList[i] = null;
            }
        }
    }


}
