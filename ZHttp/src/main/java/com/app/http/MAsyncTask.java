package com.app.http;

import android.os.AsyncTask;

/**
 * Created by ${zyj} on 2015/12/21 fd ${f}.
 * 描述：
 */
public class MAsyncTask extends AsyncTask<String, Integer , String> {

    private String url = null ;
    private HttpCallBack httpCallBack = null  ;
    private Http http  = null ;

    public MAsyncTask( String url , HttpCallBack httpCallBack ) {
        this.url = url;
        this.httpCallBack = httpCallBack ;

        if ( http == null ){
            http = new Http() ;
        }

        //开始执行
        execute( url ) ;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if ( httpCallBack != null ){
            httpCallBack.start();
        }

    }

    @Override
    protected String doInBackground(String... params) {
        if ( http == null ){
            http = new Http() ;
        }
        return http.httpGet( params[0] )  ;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if ( httpCallBack != null ){
            httpCallBack.finished( result ) ;
        }

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

        if ( httpCallBack != null ){
            httpCallBack.cancle();
        }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values) ;

        if ( httpCallBack != null ){
            httpCallBack.progressUpdate( values[0] ) ;
        }
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }
}
