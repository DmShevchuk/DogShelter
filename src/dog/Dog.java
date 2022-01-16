package dog;

import human.Human;


public class Dog {
    private final Breed BREED;
    private final String name;
    private final double age;
    private final int monthlyCost;
    private int health;
    private int satiation;
    private HappinessLevel HPI;
    private Human owner;

    public Dog(String name, Breed BREED, double age, int monthlyCost) {
        this.name = name;
        this.BREED = BREED;
        this.age = age;
        this.monthlyCost = monthlyCost;
        this.owner = null;
        medicalCheckup();
    }

    public String getName() {
        return name;
    }

    public double getAge() {
        return age;
    }

    public int getMonthlyCost() {
        return monthlyCost;
    }

    public Human getOwner() {
        return owner;
    }

    public String getBREED() {
        return BREED.toString();
    }

    public int getHealth() {
        return health;
    }

    public int getSatiation() {
        return satiation;
    }

    public String getHPI() {
        return HPI.describe();
    }

    private void medicalCheckup() {
        /*
        При появлении новой собаки в приюте производится осмотр, на котором выствляются значения полей:
            health - от 20 до 90;
            satiation - от 10 до 90;
            HPI - от UPSET до HAPPY;
        */
        health = (int) (Math.random() * 70) + 20;
        satiation = (int) (Math.random() * 80) + 10;

        //Реализация лямбда-выражения на основе функционального интерфейса RandomHPI
        RandomHPI hpi = () -> HappinessLevel.values()[(int) (Math.random() * HappinessLevel.values().length)];

        //Получение случайного настроение для собаки
        HPI = hpi.getRandomHPI();
    }

    public String feed() {

        satiation = Math.min(satiation + 10, 100);
        health += Math.min(health + 5, 100);
        return "Собака " + name + " покормлена";
    }

    public String heal() {

        health = Math.min(health + 20, 100);
        return "Здоровье собаки " + name + " улучшилось, теперь оно составляет " + health + "/100";
    }

    public String playWith() {

        HappinessLevel oldHPI = HPI;
        HPI = HPI.next();
        return "Собака " + name + " поиграна, настроение изменилось с "
                + oldHPI + " на " + HPI + ", теперь " + HPI.describe();
    }

    public void setOwner(Human o) {
        owner = o;
    }

    @Override
    public String toString() {
        return "Собака " + name + ", порода - " + BREED + ", возраст - " + age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Dog) {
            return this.hashCode() == obj.hashCode();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 31;
    }

}
