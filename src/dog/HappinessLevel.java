package dog;

public enum HappinessLevel {
    UPSET("собака очень расстроена."),
    DISAPPOINTED("собака разочарована."),
    NEUTRAL("собаке всё безразлично."),
    PLAYFUL("у собаки игривое настроение."),
    HAPPY("собака очень счастлива.");

    private final String describing;

    HappinessLevel(String d){
        describing = d;
    }

    public HappinessLevel next() {
        switch (this) {
            case UPSET -> {
                return DISAPPOINTED;
            }
            case DISAPPOINTED -> {
                return NEUTRAL;
            }
            case NEUTRAL -> {
                return PLAYFUL;
            }
        }
        return HAPPY;
    }

    public String describe(){
        return describing;
    };
}
