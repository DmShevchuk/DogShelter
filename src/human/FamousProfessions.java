package human;

public enum FamousProfessions {
    BLOGGER("блогер") {
        @Override
        public String attractAttention() {
            return "снял ролик про приют для бездомных собак.";
        }
    },
    JOURNALIST("журналист") {
        @Override
        public String attractAttention() {
            return "написал статью про семью, которая приютила собаку из приюта.";
        }
    },
    BUSINESSMAN("бизнесмен") {
        @Override
        public String attractAttention() {
            return "купил корм для бездомных собак.";
        }
    },
    MUSICIAN("музыкант") {
        @Override
        public String attractAttention() {
            return "сыграл собачий вальс на Красной площади.";
        }
    },
    POLITICIAN("политик") {
        @Override
        public String attractAttention() {
            return "увеличил зарплату сотрудникам московских приютов.";
        }
    },
    ACTOR("актер") {
        @Override
        public String attractAttention() {
            return "провел одиночный пикет для привлечения внимания людей к собакам из приютов.";
        }
    };

    private final String professionRus;

    FamousProfessions(String professionRus) {
        this.professionRus = professionRus;
    }

    public abstract String attractAttention();

    @Override
    public String toString() {
        return professionRus;
    }
}
