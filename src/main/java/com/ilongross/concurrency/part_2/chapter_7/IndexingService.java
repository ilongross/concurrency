package com.ilongross.concurrency.part_2.chapter_7;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;


// Реализация выключения потока с помощью "Ядовитой таблетки"

public class IndexingService {
    private static final File POISON = new File("");
    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;


    public IndexingService(BlockingQueue<File> queue, FileFilter fileFilter, File root) {
        this.queue = queue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }


    private class IndexerThread extends Thread {
        @Override
        public void run() {
            try {
                while(true) {
                    File file = queue.take();
                    if(file == POISON)
                        break;
                    else
                        indexFile(file);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void indexFile(File file) {
            // Реализация индексации файла
        }
    }

    private class CrawlerThread extends Thread {
        @Override
        public void run() {
            try {
                crawl(root);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                while(true) {
                    try {
                        queue.put(POISON);
                        break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        private void crawl(File file) {
            // реализация метода
        }
    }


}
