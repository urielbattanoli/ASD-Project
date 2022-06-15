package framework.Observer;

public interface IObserver<T> {
    void deductDone(T object);
    void increaseDone(T object);
    void transferDone(T object);
}
