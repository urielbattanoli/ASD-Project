package framework;

public interface ISubject {
    public abstract IObserver addObserver(IObserver observer);
    public abstract boolean removeObserver();
    public abstract void notifyObserver();
}
