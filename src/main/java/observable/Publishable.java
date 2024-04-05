package observable;

public interface Publishable<T> {
    void subscribe(Observable<T> observable);

    void push(T t);
}
