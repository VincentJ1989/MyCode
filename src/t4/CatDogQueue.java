package t4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：猫狗问题
 * 思路：内部维护2队列，分别存储猫和狗。单独弹某一个类型则直接从对应的队列弹出；
 *      如果是全部弹，则对比2队列最开始的时间戳(计数器)，谁先就先弹出.
 *
 * @author VincentJ
 * @date 2019-05-05
 */
public class CatDogQueue {
    private Queue<PetEnterQueue> mDogQueue;
    private Queue<PetEnterQueue> mCatQueue;
    private long mCount;

    public CatDogQueue(Queue<PetEnterQueue> pDogQueue, Queue<PetEnterQueue> pCatQueue, long pCount) {
        this.mDogQueue = new LinkedList<>(pDogQueue);
        this.mCatQueue = new LinkedList<>(pCatQueue);
        this.mCount = pCount;
    }

    public void add(Pet pPet) {
        if (Dog.TYPE.equals(pPet.getType())) {
            this.mDogQueue.add(new PetEnterQueue(pPet, this.mCount++));
        } else if (Cat.TYPE.equals(pPet.getType())) {
            this.mCatQueue.add(new PetEnterQueue(pPet, this.mCount++));
        } else {
            throw new RuntimeException("error,not dog or cat.");
        }
    }

    public Dog pollDog() {
        if (!isDogQueueEmpty()) {
            return (Dog) mDogQueue.poll().getPet();
        } else {
            throw new RuntimeException("Dog queue is empty.");
        }
    }

    public Cat pollCat() {
        if (!isCatQueueEmpty()) {
            return (Cat) mCatQueue.poll().getPet();
        } else {
            throw new RuntimeException("Cat queue is empty.");
        }
    }

    public Pet pollAll() {
        if (!mDogQueue.isEmpty() && !mCatQueue.isEmpty()) {
            // 这里对比的时候用的是peek而不是poll.
            if (mDogQueue.peek().getCount() < mCatQueue.peek().getCount()) {
                return mDogQueue.poll().getPet();
            } else {
                return mCatQueue.poll().getPet();
            }
        } else if (!mDogQueue.isEmpty()) {
            return mDogQueue.poll().getPet();
        } else if (!mCatQueue.isEmpty()) {
            return mCatQueue.poll().getPet();
        } else {
            throw new RuntimeException("error,queue is empty.");
        }
    }

    public boolean isEmpty() {
        return mCatQueue.isEmpty() && mDogQueue.isEmpty();
    }

    public boolean isDogQueueEmpty() {
        return mDogQueue.isEmpty();
    }

    public boolean isCatQueueEmpty() {
        return mCatQueue.isEmpty();
    }

}
