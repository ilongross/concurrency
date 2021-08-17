package com.ilongross.concurrency.chapter_2;

import net.jcip.annotations.NotThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

// Этот класс не рекомендован к использованию
// Пример плохого варианта потокобезопасного сервлета

@NotThreadSafe
public class UnsafeCountingFactorizer implements Servlet {

    private long count = 0;
    public long getCount() {
        return count;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        ++count;
        encodeIntoResponse(res, factors);
    }

    private BigInteger extractFromRequest(ServletRequest req) {
        return BigInteger.valueOf(Long.parseLong(req.getParameter("number")));
    }

    private BigInteger[] factor(BigInteger i) {
        char[] chars = i.toString().toCharArray();
        BigInteger[] result = new BigInteger[chars.length];
        for (int j = 0; j < result.length; j++) {
            result[j] = BigInteger.valueOf(chars[j]);
        }
        return result;
    }

    private void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {
        // todo
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
