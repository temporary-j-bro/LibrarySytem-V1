package jbro.study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SynchronizationTest {
    private static final int THREAD_COUNT = 1000;

    private Long justLong = 0L;
    private AtomicLong atomicLong = new AtomicLong(0);

    private ExecutorService executor;

    @BeforeEach
    void setUp() {
        executor = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    @DisplayName("long 타입은 멀티스레딩 환경에서 동기화를 보장하지 않는다")
    @Test
    void long_is_not_ensured() throws InterruptedException {

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                justLong++;
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        assertNotEquals(THREAD_COUNT, justLong.longValue());
    }

    @DisplayName("atomicLong 타입은 멀티스레딩 환경에서 동기화를 보장한다")
    @Test
    void atomicLong_is_ensured() throws InterruptedException {

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                atomicLong.incrementAndGet();
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        assertEquals(THREAD_COUNT, atomicLong.get());
    }
}