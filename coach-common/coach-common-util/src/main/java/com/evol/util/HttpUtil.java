package com.evol.util;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.*;

@Slf4j
public class HttpUtil {

//    private static final MediaType JSON
//            = MediaType.get("application/json; charset=utf-8");


    public static void asyncPostXml(String url, String xml) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>());

        Runnable task = () -> {
            try {
                String respStr = HttpClientUtil.doPostXml(url, xml, "");
                log.debug("response:" + respStr);
                //return response.code();
            }catch (Exception ex){
                log.error("远程请求错误", ex);
            }
        };
        executor.submit(task);
        // 设置超时时间为 1s
        //future.get(1, TimeUnit.SECONDS);
    }

    public static void asyncGetXml(String url) {

//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(url).get().build();
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS,
//                new SynchronousQueue<Runnable>());
//
//        Runnable task = () -> {
//            try {
//                try (Response response = client.newCall(request).execute()) {
//                    log.debug("response.code:" + response.code());
//                    log.debug(response.body().string());
//                    //return response.code();
//                }
//            }catch (Exception ex){
//                log.error("远程请求错误", ex);
//            }
//        };
//        executor.submit(task);
//        // 设置超时时间为 1s
//        //future.get(1, TimeUnit.SECONDS);
    }

}
