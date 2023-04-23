package jbro.study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

public class AtomicLongTest {

    private AtomicLong atomicLong;

    @BeforeEach
    void setUp() {
        atomicLong = new AtomicLong();
    }

    @Test
    void initialize() {
        assertThat(atomicLong).isInstanceOf(AtomicLong.class);

        assertThat(atomicLong).isNotInstanceOf(Long.class);
        assertThat(atomicLong).isInstanceOf(Number.class);
    }

    @Test
    void get_then_long_type() {
        long actual = atomicLong.get();

        assertThat(actual).isEqualTo(0L);
    }

    @Test
    void set() throws InterruptedException {
        atomicLong.set(20L);

        assertThat(atomicLong.get()).isEqualTo(20L);
    }

    @Test
    void incrementAndGet() {
        assertThat(atomicLong.incrementAndGet()).isEqualTo(1L);
    }
}
