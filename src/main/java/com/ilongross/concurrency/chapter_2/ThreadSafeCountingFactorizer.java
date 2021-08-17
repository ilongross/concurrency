package com.ilongross.concurrency.chapter_2;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

// Потокобозопасный сервлет подсчитывающий запросы
// Применяется атомарная переменная - гарантирует, что все действия,
//                              которые обращаются к состоянию счетчика, являются атомарными.

@ThreadSafe
public class ThreadSafeCountingFactorizer implements Servlet {

    private final AtomicLong count = new AtomicLong(0);

    public long getCount() {
        return count.get();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        count.incrementAndGet();
    }


    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
