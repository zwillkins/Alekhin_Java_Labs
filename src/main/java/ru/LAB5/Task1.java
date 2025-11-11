package ru.LAB5;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task1 {

    public static void main(String[] args) throws InterruptedException {

        final int ARRAY_SIZE = 100000000;
        final int NUM_THREADS = 5;
        int[] array = new Random().ints(ARRAY_SIZE, 0, 1234567890).toArray();

        List<MaxFinderTask> tasks = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        int chunkSize = ARRAY_SIZE / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            int start = i * chunkSize;
            int end = (i == NUM_THREADS - 1) ? ARRAY_SIZE : start + chunkSize;

            MaxFinderTask task = new MaxFinderTask(array, start, end);
            tasks.add(task);
            Thread thread = new Thread(task, "Finder-" + i);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int globalMax = -1;
        for (MaxFinderTask task : tasks) {
            if (task.getLocalMax() > globalMax) {
                globalMax = task.getLocalMax();
            }
        }

        System.out.println("Найденный максимум: " + globalMax);
    }




    static class MaxFinderTask implements Runnable {
        private final int[] array;
        private final int start;
        private final int end;
        private int localMax = -1;

        public MaxFinderTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                if (array[i] > localMax) {
                    localMax = array[i];
                }
            }
            System.out.println(Thread.currentThread().getName() + " нашел локальный максимум: " + localMax);
        }

        public int getLocalMax() {
            return localMax;
        }
    }
}