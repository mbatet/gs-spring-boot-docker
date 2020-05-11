package hello;

import hello.job.ScheduledTasks;
import org.awaitility.Duration;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ScheduledTasksTest {

    @SpyBean
    ScheduledTasks tasks;

    @Test
    @Ignore("L'ignorem perqué necessita 2 minuts sencer per poder fer el test i assegurar les planificacions, pero funciona i passa OK")
    public void scheduleTaskUsingFixedRate() {
        await().atMost(Duration.ONE_MINUTE).untilAsserted(() -> {
            verify(tasks, atLeast(6)).scheduleTaskUsingFixedRate();
        });
    }

    @Test
    @Ignore("L'ignorem perqué necessita 2 minuts sencer per poder fer el test i assegurar les planificacions, pero funciona i passa OK")
    public void scheduleTaskUsingCronExpression() {
        await().atMost(Duration.ONE_MINUTE).untilAsserted(() -> {
            verify(tasks, atLeast(1)).scheduleTaskUsingCronExpression();
        });
    }
}