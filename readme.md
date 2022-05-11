# JWT认证SDK使用说明

Java版本的SDK，用于生成Orchsym API Gateway JWT 认证所需的token。

支持 Java 8+。


### JWT简介

* JWT(全称：Json Web Token)是一个开放标准(RFC 7519)，它定义了一种紧凑的、自包含的方式，用于作为JSON对象在各方之间安全地传输信息。该信息可以被验证和信任，因为它是数字签名的。

### 生成token

1. 使用RS256算法
- 1.生成秘钥对秘钥对 keySize为 1024或2048
```
    OrchsymRSA256KeyGenerator orchsymRSA256KeyGenerator = new OrchsymRSA256KeyGenerator(keySize);
```

- 2.通过clientId,tokenPeriod和生成的私钥生成token
```
    JwtGenerator jwtGenerator = new JwtGenerator(privateKey, client, tokenPeriod, "RS256");
```

2). 使用HS256算法
- 1.通过clientId,tokenPeriod,secretCode生成token
```
    JwtGenerator jwtGenerator = new JwtGenerator(secretCode, client, tokenPeriod, "HS256");
```

### 

### 构建请求头报文：

设置header：  

```
  orchsym_app_code : appCode
  Authorization : bearer token
```

### 方法说明：

#### JwtTest.test(String appCode)

描述：生成 Jwt Token 实例，发送请求。

参数：

- orchsym_app_code: appCode

### 代码示例：

```
       String urlStr = "http://172.18.89.241/env-101/por-4203/testlinhu/r0224/app/mock/285744/app/mock/yst/hello4";

        JwtGenerator jwtGenerator = new JwtGenerator(privateKey, "2aed9b77-2ed6-47da-828f-40d19e67ad65", 300, "RS256");

        System.out.println("token : " + jwtGenerator.getToken());

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlStr)
                .addHeader("orchsym_app_code", appCode)
                .addHeader("Authorization", "bearer " + jwtGenerator.getToken())
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println("Response Code: " + response.code());

        ResponseBody body = response.body();
        if (body != null) {
            System.out.println("Response Body: " + body.string());
        }

        response.close();
```


