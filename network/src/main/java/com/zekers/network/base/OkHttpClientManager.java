package com.zekers.network.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.zekers.network.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okio.Buffer;
import retrofit2.Retrofit;

/**
 * OKHTTP设置
 * Created by Zekers on 2016/12/23.
 */
public class OkHttpClientManager {
    private static final String TAG = OkHttpClientManager.class.getSimpleName();
    private static OkHttpClient client;

    //配置OKHTTP
    public static OkHttpClient getClient() {
        if (client == null) {
            try {
                client = new OkHttpClient.Builder()
                        .addInterceptor(new HandlerInterceptor())
                        .sslSocketFactory(getIgnoreSSLSocketFactory(),x509TrustManager).hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    private static String CER_12306 = "-----BEGIN CERTIFICATE-----\n" +
            "MIIC9jCCAd6gAwIBAgIEPn83vjANBgkqhkiG9w0BAQsFADAiMSAwHgYDVQQDExdlZHUuZGVwdHMu\n" +
            "YmluZ29zb2Z0Lm5ldDAgFw0xNjEwMjYwNjM1NTRaGA8yMjkwMDgxMTA2MzU1NFowIjEgMB4GA1UE\n" +
            "AxMXZWR1LmRlcHRzLmJpbmdvc29mdC5uZXQwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIB\n" +
            "AQCHTxtDmJ5NzTb7+kegR0pv/ETpdril17DQYcPsPMK0NcnGp5PeAcIR8BEvkRBPGWOXnKi1RyoP\n" +
            "VG0MU8IVH+RbBEB3kNoQ3hUWdoXO3xFnH1pHgIQhhU367J/0A4L/HefjS7zM2npnXjUp5ou5ycyN\n" +
            "vL9bGCAJsCxgG6o0CFemdDMT5/xpjKgByEegots4mqzMimrqsoxCi8Yt36c+8P9YggZzeD1g04+M\n" +
            "ezMzuyS4wlrwOX1rJ3qGLJ16uv64c46NWHnZey8S/hqHIyOlZ5dx8BAlQVJQxQl56yUws0ZkXw/a\n" +
            "2fJgG6vheFYae5J1o36ZqyV4GzCaPgd6n873aUBDAgMBAAGjMjAwMA8GA1UdEQQIMAaHBArJThow\n" +
            "HQYDVR0OBBYEFN13IR9xwY3IM/u64MCbYCtaSbDlMA0GCSqGSIb3DQEBCwUAA4IBAQAxPG+b4rhr\n" +
            "56S7ZL18inELl2ubJ5I/bekA4nsz/VZ2VTRen68yRwUUfepqGxl6zn0DFG3i5spxIXNYBbUnHGdW\n" +
            "ItZ9ofxB0jMZtGMM9f1UyvL1NxYqcKSelKeGsmdxev66Q9c5CIoOH94feNgKW6hiR8HbSauU99Lm\n" +
            "mxph7fZeA3hTuMg3GPZZzWqQbFqF+wom3Ndwlvaaq6NnLW/y3BwR6Fl0QRymL2M9UY4VwpH1G7lC\n" +
            "R1aB74jgLUZ7ZHeU+Eqzu/j1LTXN9Y2uS4QuOQsEG5qZzY+wAJ3+KPrYwdKh9qBIHFRubDL1bWSs\n" +
            "5W/bHTtVZfisKp0uC2r1TOK1vv+3\n" +
            "-----END CERTIFICATE-----";



    public static void setIgnoreSslSocketFactory(OkHttpClient.Builder builder) throws Exception {
        Log.i(TAG,"#setIgnoreSslSocketFactory");
        builder.sslSocketFactory(getIgnoreSSLSocketFactory(),x509TrustManager).hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }

    public static void setSslSocketFactory(OkHttpClient.Builder builder) throws Exception {
        builder.sslSocketFactory(getSSLSocketFactory())
                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    }

    private static void onHttpCertficates(OkHttpClient.Builder builder) {
//        int[] certficates = new int[]{R.raw.media};
//        builder.socketFactory(getSSLSocketFactory(Application, certficates));
    }

    private static void setSslSocketFactory(OkHttpClient.Builder builder, String CER_12306) {
        getSSLSocketFactory(builder,new Buffer()
                .writeUtf8(CER_12306)
                .inputStream());
    }

    private static BGX509TrustManager x509TrustManager=new BGX509TrustManager();
    //暴力忽略版
    public static SSLSocketFactory getIgnoreSSLSocketFactory() throws Exception {
        //创建一个不验证证书链的证书信任管理器。
        final TrustManager[] trustAllCerts = new TrustManager[]{x509TrustManager};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null,
                trustAllCerts,
                new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        return sslContext
                .getSocketFactory();
    }

    //不内置证书
    public static SSLSocketFactory getSSLSocketFactory() throws Exception {
        // Create a trust manager that does not validate certificate chains
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            //证书中的公钥
            public static final String PUB_KEY = "3082010a0282010100d52ff5dd432b3a05113ec1a7065fa5a80308810e4e181cf14f7598c8d553cccb7d5111fdcdb55f6ee84fc92cd594adc1245a9c4cd41cbe407a919c5b4d4a37a012f8834df8cfe947c490464602fc05c18960374198336ba1c2e56d2e984bdfb8683610520e417a1a9a5053a10457355cf45878612f04bb134e3d670cf96c6e598fd0c693308fe3d084a0a91692bbd9722f05852f507d910b782db4ab13a92a7df814ee4304dccdad1b766bb671b6f8de578b7f27e76a2000d8d9e6b429d4fef8ffaa4e8037e167a2ce48752f1435f08923ed7e2dafef52ff30fef9ab66fdb556a82b257443ba30a93fda7a0af20418aa0b45403a2f829ea6e4b8ddbb9987f1bf0203010001";


            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            //客户端并为对ssl证书的有效性进行校验
            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
                if (chain == null) {
                    throw new IllegalArgumentException("checkServerTrusted:x509Certificate array isnull");
                }

                if (!(chain.length > 0)) {
                    throw new IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
                }

                if (!(null != authType && authType.equalsIgnoreCase("RSA"))) {
                    throw new CertificateException("checkServerTrusted: AuthType is not RSA");
                }

                // Perform customary SSL/TLS checks
                try {
                    TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
                    tmf.init((KeyStore) null);
                    for (TrustManager trustManager : tmf.getTrustManagers()) {
                        ((X509TrustManager) trustManager).checkServerTrusted(chain, authType);
                    }
                } catch (Exception e) {
                    throw new CertificateException(e);
                }
                // Hack ahead: BigInteger and toString(). We know a DER encoded Public Key begins
                // with 0×30 (ASN.1 SEQUENCE and CONSTRUCTED), so there is no leading 0×00 to drop.
                RSAPublicKey pubkey = (RSAPublicKey) chain[0].getPublicKey();

                String encoded = new BigInteger(1 /* positive */, pubkey.getEncoded()).toString(16);
                // Pin it!
                final boolean expected = PUB_KEY.equalsIgnoreCase(encoded);

                if (!expected) {
                    throw new CertificateException("checkServerTrusted: Expected public key: "
                            + PUB_KEY + ", got public key:" + encoded);
                }

            }


            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts,
                new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        return sslContext
                .getSocketFactory();
    }

    //内置证书的情况
    protected static SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {

        if (context == null) {
            throw new NullPointerException("context == null");
        }

        //CertificateFactory用来证书生成
        CertificateFactory certificateFactory;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            //Create a KeyStore containing our trusted CAs
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);

            for (int i = 0; i < certificates.length; i++) {
                //读取本地证书
                InputStream is = context.getResources().openRawResource(certificates[i]);
                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(is));

                if (is != null) {
                    is.close();
                }
            }
            //Create a TrustManager that trusts the CAs in our keyStore
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            //Create an SSLContext that uses our TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();

        } catch (Exception e) {

        }
        return null;
    }

    //鸿神提供
    public static void getSSLSocketFactory(OkHttpClient.Builder builder, InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );
            builder.sslSocketFactory(sslContext.getSocketFactory());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
