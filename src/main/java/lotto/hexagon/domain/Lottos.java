package lotto.hexagon.domain;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Lottos implements Iterable<Lotto> {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public int getSize() {
        return lottos.size();
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottos.iterator();
    }

    public Stream<Lotto> stream() {
        return lottos.stream();
    }
}
