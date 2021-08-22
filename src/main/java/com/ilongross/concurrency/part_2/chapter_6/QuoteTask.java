package com.ilongross.concurrency.part_2.chapter_6;

import java.util.*;
import java.util.concurrent.*;


// Класс с реализацией возможности запроса цен на поездки в рамках бюджета времени

public class QuoteTask implements Callable<TravelQuote> {

    private final ExecutorService exec = Executors.newFixedThreadPool(10);

    private final TravelCompany company;
    private final TravelInfo travelInfo;

    public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
        this.company = company;
        this.travelInfo = travelInfo;
    }

    @Override
    public TravelQuote call() throws Exception {
        return company.solicitQuote(travelInfo);
    }

    public List<TravelQuote> getRankedTravelQuotes
            (TravelInfo info, Set<TravelCompany> companies,
             Comparator<TravelQuote> ranking,
             long time, TimeUnit unit) throws InterruptedException {

        List<QuoteTask> tasks = new ArrayList<QuoteTask>();
        for (TravelCompany company : companies) {
            tasks.add(new QuoteTask(company, travelInfo));
        }

        List<Future<TravelQuote>> futures = exec.invokeAll(tasks, time, unit);
        List<TravelQuote> quotes = new ArrayList<TravelQuote>(tasks.size());
        Iterator<QuoteTask> taskIter = tasks.iterator();
        for (Future<TravelQuote> f : futures) {
            QuoteTask task = taskIter.next();

            try {
                quotes.add(f.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(quotes, ranking);
        return quotes;
    }

}


