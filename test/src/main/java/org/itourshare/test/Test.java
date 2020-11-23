package org.itourshare.test;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @ClassName : Test
 * @Description :
 * @Author : its
 * @Date: 2020-09-03 08:58
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {

        LocalDateTime now = LocalDateTime.now();
        Thread.sleep(1999);
        LocalDateTime expire = LocalDateTime.now();
        long delay = ChronoUnit.MILLIS.between(now, expire);
        System.out.println(delay);
    }
}
