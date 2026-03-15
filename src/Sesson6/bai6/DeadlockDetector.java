package Sesson6.bai6;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class DeadlockDetector {
    public static void scan() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] threadIds = bean.findDeadlockedThreads();

        System.out.println("Đang quét deadlock...");
        if (threadIds != null) {
            System.err.println("!!! CẢNH BÁO: Phát hiện " + threadIds.length + " luồng bị Deadlock!");
        } else {
            System.out.println("   Không phát hiện deadlock.");
        }
    }
}