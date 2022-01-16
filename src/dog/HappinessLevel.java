package dog;

public enum HappinessLevel {
    UPSET {
        @Override
        public String describe() {
            return "собака очень расстроена.";
        }
    },
    DISAPPOINTED {
        @Override
        public String describe() {
            return "собака разочарована.";
        }
    },
    NEUTRAL {
        @Override
        public String describe() {
            return "собаке всё безразлично.";
        }
    },
    PLAYFUL {
        @Override
        public String describe() {
            return "у собаки игривое настроение.";
        }
    },
    HAPPY {
        @Override
        public String describe() {
            return "собака очень счастлива.";
        }
    };

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

    abstract String describe();
}
