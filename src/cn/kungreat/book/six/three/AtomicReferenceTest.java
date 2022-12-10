package cn.kungreat.book.six.three;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

    private static final AtomicReference<User> ATOMIC_REFERENCE = new AtomicReference<>();

    public static void main(String[] args) {
        User user1 = new User("kungreat",18);
        User user2 = new User("xiangyangit",28);
        User user3 = new User("java",29);
        System.out.println(ATOMIC_REFERENCE.compareAndSet(null, user1));
        System.out.println(ATOMIC_REFERENCE.get());
        System.out.println(ATOMIC_REFERENCE.compareAndSet(null, user2));
    }

    static final class User{
        private String name;
        private Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
