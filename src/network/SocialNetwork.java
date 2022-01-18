package network;

import human.FamousProfessions;
import java.util.Formatter;

public class SocialNetwork {
    private final String name;
    private Post[] postStorage = new Post[1];

    public SocialNetwork(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Post[] getPostStorage() {
        return  postStorage.clone();
    }

    public void addPost(Post post) {
        // Если в массиве нет места, то создаётся новый с длиной = длинаТекущегоМассива + 1
        if(postStorage[postStorage.length - 1] != null){
            increasePostStorage();
        }

        for(int i = 0; i < postStorage.length; i++){
            if(postStorage[i] == null){
                postStorage[i] = post;
            }
        }

        Publisher.publish(name, post);
    }

    public void increasePostStorage(){
        Post[] tmpStorage = getPostStorage();
        postStorage = new Post[tmpStorage.length + 1];
        System.arraycopy(tmpStorage, 0, postStorage, 0, tmpStorage.length);
    }

    public class Post {
        private final String dogInfo;
        private final String famousPersonName;
        private final FamousProfessions profession;
        private int likes = 0;

        public Post(String dogInfo, String famousPersonName, FamousProfessions profession) {
            this.dogInfo = dogInfo;
            this.famousPersonName = famousPersonName;
            this.profession = profession;
            addPost(this);
        }

        public String getDogInfo() {
            return dogInfo;
        }

        public String getFamousPersonName() {
            return famousPersonName;
        }

        public FamousProfessions getProfession() {
            return profession;
        }

        public int getLikes() {
            return likes;
        }

        public void like() {
            likes++;
        }

        public String getPostInfo() {
            return "Пост от " + famousPersonName + " набрал " + likes + " лайка";
        }

        @Override
        public int hashCode() {
            return super.hashCode() + 31;
        }
    }

    public static class Publisher {
        public static void publish(String SNname, Post post) {
            Formatter f = new Formatter();

            f.format("Публикация в %s!\nИзвестный %s %s посетил приют и теперь" +
                            " заботиться о новом подопечном: %s.", SNname, post.getProfession(), post.getFamousPersonName(),
                    post.getDogInfo());

            System.out.println(f);
        }
    }
}
