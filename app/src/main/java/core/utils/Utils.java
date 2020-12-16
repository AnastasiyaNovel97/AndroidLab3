package core.utils;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.function.Supplier;

@UtilityClass
public class Utils {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static <T> List<T> getRandomElements(List<T> src, int count) {
        src.sort((T o1, T o2) -> (int) Math.floor(0.5 - Math.random()));
        return src.subList(0, Math.min(count, src.size()));
    }

    public static <T> T getRandomElement(List<T> src) {
        return getRandomElements(src, 1).get(0);
    }

    public static <T> void lazyInit(T ref, Supplier<T> valueSupplier) {
        ref = valueSupplier.get();
    }
}
