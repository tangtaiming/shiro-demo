package com.application.ttm;

import com.mysql.cj.xdevapi.FetchResult;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2019-06-12</p>
 * <p>@Version 1.0</p>
 **/
public class QiniuUploadImageTest {

    private String ACCESS_KEY = "";
    private String SECRET_KEY = "";
    //上传的空间
    private String bucketname = "imgdemo";
    //上传到七牛后保存的文件名
    private String imageName = "upload-01.jpg";
    //上传文件的路径
    private String filePath = "D://upload.jpg";
    //密钥配置
    private Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    ///////////////////////指定上传的Zone的信息//////////////////
    //第一种方式: 指定具体的要上传的zone
    //注：该具体指定的方式和以下自动识别的方式选择其一即可
    //要上传的空间(bucket)的存储区域为华东时
    // Zone z = Zone.zone0();
    //要上传的空间(bucket)的存储区域为华北时
    // Zone z = Zone.zone1();
    //要上传的空间(bucket)的存储区域为华南时
    // Zone z = Zone.zone2();

    //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
    Zone z = Zone.autoZone();
    Configuration configuration = new Configuration(z);
    //创建上传对象
    UploadManager uploadManager = new UploadManager(configuration);

    /**
     * 上传图片
     */
    @Ignore
    @Test
    public void uploadImageSuccessTest() {
        upload();
    }

    /**
     * 获取图片
     */
    @Test
    public void fetchImageSuccessTest() {
        BucketManager bucketManager = new BucketManager(auth, configuration);
        try {
            FileListing fileListing = bucketManager.listFilesV2(bucketname, "148889", null, 100, "/");
            FileInfo[] fileItems = fileListing.items;
            for (int x = 0; x < fileItems.length; x++) {
                System.out.println(JsonUtils.toJson(fileItems[x]));
            }
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    public void upload() {
        try {
            Response response = uploadManager.put(filePath, imageName, getUpToken());
            //打印返回信息
            System.out.println("-->  " + response.statusCode);
            System.out.println("res: " + response.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            e.printStackTrace();
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }

    }

}
