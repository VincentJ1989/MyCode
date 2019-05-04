package t4;

/**
 * 猫狗问题辅助类
 *
 * @author VincentJ
 * @date 2019-05-05
 */
public class PetEnterQueue {
    private Pet mPet;
    private long count;

    public PetEnterQueue(Pet pPet, long pCount) {
        mPet = pPet;
        count = pCount;
    }

    public Pet getPet() {
        return mPet;
    }

    public long getCount() {
        return count;
    }

    public String getType() {
        return mPet.getType();
    }
}
