package com.rosefinch.example.future;

import com.yy.onepiece.commons.result.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author York.Hwang
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public class Main {

    Executor executor = Executors.newCachedThreadPool();

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(), new AsyncThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //new Main().execute();
        //调用主方法
        new Main().executeFuture();


    }

    public void executeFuture()  {

        try {
            final Long userId = 10L;
            final long st = System.currentTimeMillis();
            final Callable<String> userLevelCall = () -> getUserLevel(userId);
            final Callable<String> userTagCall = () -> getUserTag(userId);

            final FutureTask<String> userLevelTask = new FutureTask<>(userLevelCall);
            final FutureTask<String> userTagTask = new FutureTask<>(userTagCall);

            EXECUTOR.submit(userLevelTask);
            EXECUTOR.submit(userTagTask);

            log.info("1-cost seconds:" + (System.currentTimeMillis() - st));
            log.info("userTag==" + userTagTask.get());
            log.info("2-cost seconds:" + (System.currentTimeMillis() - st));
            log.info("userLevel==" + userLevelTask.get());
            log.info("3-cost seconds:" + (System.currentTimeMillis() - st));

        } catch (final RuntimeException e){
           log.error("", e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            EXECUTOR.shutdown();
        }
    }


    public void execute(){

        final List<String> resultLive = new Vector<>();
        final Long userId = 100000L;

        final CompletableFuture<String> userLevelFuture = CompletableFuture.supplyAsync(() -> getUserLevel(userId));
        final CompletableFuture<String> userTagFuture = CompletableFuture.supplyAsync(() -> getUserTag(userId));


        final CompletableFuture<Void> completableFuture = CompletableFuture.allOf(userLevelFuture, userTagFuture);
        final long st = System.currentTimeMillis();
        completableFuture.thenApplyAsync((v) -> {
            try {
                log.info("1-result=="+resultLive+" t cost="+(System.currentTimeMillis()-st));
                resultLive.add(userLevelFuture.get());
                log.info("2-result=="+resultLive+" t cost="+(System.currentTimeMillis()-st));
                resultLive.add(userTagFuture.get());
                log.info("3-result=="+resultLive+" t cost="+(System.currentTimeMillis()-st));
            } catch (final InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }, EXECUTOR).join();

        log.info("cost seconds:"+(System.currentTimeMillis()-st));
        log.info("result=="+resultLive);


    }

    public String getUserLevel(Long userId){
        try {
            TimeUnit.SECONDS.sleep(1);
            log.info("=============getUserLevel");
            if(true){
                throw new BusinessException("DEF_CODE", "主动异常");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(--userId);
    }


    public String getUserTag(Long userId){
        try {
            TimeUnit.SECONDS.sleep(2);
            log.info("=============getUserTag");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(++userId);
    }


    static class AsyncThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        AsyncThreadFactory() {
            final SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-async-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);

            if (t.isDaemon()){
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY){
                t.setPriority(Thread.NORM_PRIORITY);
            }

            return t;
        }



    }

    static class MyUncaughtExceptionHandle implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("caught " + e);
        }
    }

    /**
     * Executors.DefaultThreadFactory实现
     */
    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager var1 = System.getSecurityManager();
            this.group = var1 != null ? var1.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable var1) {
            Thread var2 = new Thread(this.group, var1, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
            if (var2.isDaemon()) {
                var2.setDaemon(false);
            }

            if (var2.getPriority() != 5) {
                var2.setPriority(5);
            }

            return var2;
        }
    }
}
