package com.ilongross.concurrency.part_2.chapter_6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


// Класс с реализацией ожидания загрузки изображения с объектом Future

public class FutureRenderer {
    private final ExecutorService execService = Executors.newFixedThreadPool(10);

    public void renderPage(CharSequence source) {
        final List<ImageInfo> imageInformation = scanForImageInfo(source);
        Callable<List<ImageData>> task =
                new Callable<List<ImageData>>() {
                    @Override
                    public List<ImageData> call() throws Exception {
                        List<ImageData> result =
                                new ArrayList<ImageData>();
                        for (ImageInfo imageInfo : imageInformation) {
                            result.add(imageInfo.downloadImage());
                        }
                        return result;
                    }
                };

        Future<List<ImageData>> future = execService.submit(task);
//        renderText(source);

        try {
            List<ImageData> imageData = future.get();
            for (ImageData data : imageData) {
//                renderImage(data);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            // Переподтвердить статус прерванност ипотока
            Thread.currentThread().interrupt();
            // Нам не нужен результат, поэтому отменить задачу
            future.cancel(true);
        }

    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return new ArrayList<ImageInfo>();
    }


}
