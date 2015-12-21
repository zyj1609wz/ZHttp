package com.app.http;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ${zyj} on 2015/12/21 fd ${f}.
 * 描述：
 */
public class Http {

    private int connectTime = 1000 * 15 ;   //设置连接主机超时  15秒
    private int readTime = 1000 * 15 ;  //置从主机读取数据超时（单位：毫秒）  15秒

    public String httpGet( String urlString ){
        HttpURLConnection httpURLConnection  = null ;  //连接对象
        InputStream is = null;
        String result = "" ;
        try {
            URL url = new URL( urlString ) ;
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true); //允许输入流，即允许下载
            httpURLConnection.setDoOutput(true); //允许输出流，即允许上传
            httpURLConnection.setUseCaches(false); //不使用缓冲
            httpURLConnection.setRequestMethod("GET") ;  //使用get请求
            httpURLConnection.setConnectTimeout( connectTime );  //设置连接主机超时（单位：毫秒）
            httpURLConnection.setReadTimeout( readTime  );     //设置从主机读取数据超时（单位：毫秒）

            is = httpURLConnection.getInputStream();   //获取输入流，此时才真正建立链接
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine  = "" ;
            while((inputLine = bufferReader.readLine()) != null){
                result += inputLine + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if( is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if( httpURLConnection != null){
                httpURLConnection.disconnect();
            }
        }
        return result ;
    }

    /**
     * 设置超时时间
     * @param connectTime
     * @return
     */
    public Http setConnectTimeout( int connectTime ){
        this.connectTime = connectTime ;
        return this ;
    }

    /**
     * 置从主机读取数据超时
     * @param readTime
     * @return
     */
    public Http setReadTime( int readTime ){
        this.readTime = readTime ;
        return this ;
    }

}
