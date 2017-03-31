package example.toong.firebaserecycleradaptermutipleviewtype;

import android.support.annotation.IntDef;

/**
 * Created by phanvanlinh on 30/03/2017.
 * phanvanlinh.94vn@gmail.com
 */

//{ "name":"John", "age":31, "type":"New York" };

public class Person {
    public String name;
    public int age;
    public int type;

    public Person() {
    }

    @IntDef({PersonType.TYPE_1, PersonType.TYPE_2 })
    public @interface PersonType {
        int TYPE_1 = 1;
        int TYPE_2 = 2;
    }

    public Person(String name, int age, int type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
