package t4;

/**
 * 猫狗问题提供的基础类
 *
 * @author VincentJ
 * @date 2019-05-05
 */
public class Pet {
    private String mType;

    public Pet(String pType) {
        mType = pType;
    }

    public String getType() {
        return mType;
    }
}

class Dog extends Pet {
    public static final String TYPE = "dog";

    public Dog() {
        super(TYPE);
    }
}

class Cat extends Pet {
    public static final String TYPE = "cat";

    public Cat() {
        super(TYPE);
    }
}
