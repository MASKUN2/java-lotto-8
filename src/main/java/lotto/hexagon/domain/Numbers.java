package lotto.hexagon.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Numbers implements Iterable<Number> {
    public static final int REQUIRED_SIZE = 6;
    static final String ERROR_UNIQUE = "로또 번호는 유일한 숫자만 있어야 합니다";
    static final String ERROR_SIZE = String.format("로또 번호는 %d 개여야 합니다", REQUIRED_SIZE);

    private final Set<Number> set;

    private Numbers(Set<Number> set) {
        this.set = new TreeSet<>(set);
    }

    public static Numbers of(Collection<Number> collection) {
        assertUnique(collection);
        Set<Number> set = Set.copyOf(collection);
        assertSize(set);

        return new Numbers(set);
    }

    private static void assertUnique(Collection<Number> collection) {
        Set<Number> set = new HashSet<>();
        boolean anyDuplicated = collection.stream()
                .anyMatch(number -> !set.add(number));

        if (anyDuplicated) {
            throw new IllegalArgumentException(ERROR_UNIQUE);
        }
    }

    private static void assertSize(Set<Number> set) {
        if (set.size() != REQUIRED_SIZE) {
            throw new IllegalArgumentException(ERROR_SIZE);
        }
    }

    @Override
    public Iterator<Number> iterator() {
        return set.iterator();
    }

    public Stream<Number> stream() {
        return set.stream();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Numbers numbers)) {
            return false;
        }
        return Objects.equals(set, numbers.set);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(set);
    }
}
