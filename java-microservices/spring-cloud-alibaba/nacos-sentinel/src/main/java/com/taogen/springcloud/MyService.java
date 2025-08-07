package com.taogen.springcloud;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MyService {
    @SentinelResource(value = "MyService#service1", fallback = "service1Fallback", blockHandler = "service1BlockHandler")
    public String service1() {
        // 模拟异常，Sentinel 触发服务降级或熔断
        int i = 1 / 0;
        return "test" + System.currentTimeMillis();
    }

    /**
     * 熔断和服务降级的fallback方法
     *
     * @param t
     * @return
     */
    public String service1Fallback(Throwable t) {
        if (BlockException.isBlockException(t)) {
            // 熔断（达到异常阈值）或限流。如果用 blockHandler 则不会走到这里
            return "Blocked by Sentinel: " + t.getClass().getSimpleName() + new Date();
        }
        // 服务降级（未达到异常阈值）
        return "Oops, failed: " + t.getClass().getCanonicalName();
    }

    /**
     * 熔断或限流的blockHandler方法
     *
     * @param e
     * @return
     */
    public String service1BlockHandler(BlockException e) {
        return "service1BlockHandler: Blocked by Sentinel: " + e.getClass().getSimpleName() + new Date();
    }
}
