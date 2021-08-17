package com.ilongross.concurrency.chapter_2;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;


// Потокобезопасный сервлет, который кэширует свой последний запрос и результат

@ThreadSafe
public class CachedFactorizer implements Servlet {

    @GuardedBy("this")
    private BigInteger lastNumber;
    @GuardedBy("this")
    private BigInteger[] lastFactors;
    @GuardedBy("this")
    private long hits;
    @GuardedBy("this")
    private long cacheHits;

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = new BigInteger("1");
        BigInteger[] factors = null;

        synchronized (this) {
            ++hits;
            if(i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }
        if(factors == null) {
            factors = new BigInteger[]{i};
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }

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
