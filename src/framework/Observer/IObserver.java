package framework.Observer;

public interface IObserver<T> {
    void deductUpdate(T object);
    void increaseUpdate(T object);
    void transferUpdate(T object);
}
