package network;

import human.FamousProfessions;

import java.util.ArrayList;
import java.util.Formatter;

public class SocialNetwork {
    private final String name;
    private ArrayList<Post> postStorage = new ArrayList<>();

    public SocialNetwork(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Post> getPostStorage() {
        return (ArrayList<Post>) postStorage.clone();
    }

    public void addPost(Post post) {
        postStorage.add(post);
        Publisher.publish(name, post);
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
