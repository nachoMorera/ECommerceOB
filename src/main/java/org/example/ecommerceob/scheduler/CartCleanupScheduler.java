package org.example.ecommerceob.scheduler;

import org.example.ecommerceob.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CartCleanupScheduler {

    @Autowired
    CartRepository cartRepository;

    private static final Logger logger = LoggerFactory.getLogger(CartCleanupScheduler.class);

    @Scheduled(fixedRate = 600000, initialDelay = 600000)
    public void cleanupInactiveCarts() {
        logger.info("Running cart cleanup task...");
        cartRepository.removeInactiveCarts(10);
    }
}
