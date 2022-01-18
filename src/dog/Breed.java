package dog;

public enum Breed {
    CHIHUAHUA("чихуахуа"),
    CORGI("корги"),
    DACHSHUND("такса"),
    BOXER("боксер"),
    POODLE("пудель"),
    SHEEPDOG("овчарка");

    private final String breedRu;

    Breed(String breedRu) {
        this.breedRu = breedRu;
    }

    @Override
    public String toString(){
        return breedRu;
    }
}
