package zad1;

import java.util.function.Function;

public class InputConverter<T> {

    private T t;

    public InputConverter(T t) {
        this.t = t;
    }

    public <B> B convertBy(Function... functions) {
        Object input = t;
        Object result = null;

        for (Function f : functions) {
            result = f.apply(input);
            input = result;
        }

        return (B) result;
    }

}