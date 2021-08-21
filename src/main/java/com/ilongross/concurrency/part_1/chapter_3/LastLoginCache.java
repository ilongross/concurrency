package com.ilongross.concurrency.part_1.chapter_3;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Потокобезопасное хранилизе даты последнего входа пользоватея

public class LastLoginCache {
    private Map<Long, Date> loginsCache = Collections.synchronizedMap(
            new HashMap<Long, Date>());

    public synchronized void putNewLogin(Long id) {
        loginsCache.put(id, new Date());
    }

    public synchronized Date getLastLoginDate(Long id) {
        return loginsCache.get(id);
    }
}
