package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T> {

    private T value;

    private Maybe (T value) {
        this.value = value;
    }

    public static <F> Maybe<F> of(F value) {
        return new Maybe<>(value);
    }

    public void ifPresent (Consumer consumer) {
        if (this.value != null)
            consumer.accept(value);
    }

    public <F> Maybe<F> map (Function<T, F> function) {
        return this.value != null ? new Maybe<>(function.apply(this.value)) : new Maybe<>(null);
    }

    public boolean isPresent () {
        return value != null;
    }

    public T orElse (T value) {
        return (this.value != null) ? this.value : value;
    }

    public T get () {
        if (value == null)
            throw new NoSuchElementException("maybe is empty");
        return this.value;
    }

    public Maybe<T> filter (Predicate<T> predicate) {
        if (this.value == null) {
            return this;
        } else if (predicate.test(this.value)){
            return this;
        } else return Maybe.of(null);
    }

    @Override
    public String toString() {
        if(this.value == null)
            return "Maybe is empty";
        else
            return "Maybe has value " + this.value;
    }
}