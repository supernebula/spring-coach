//package com.evol.util.ali;
//
//
//import com.aliyun.oss.OSS;
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//
//public class AliOssUtil {
//
//    private OSS ossClient;
//
//
//    public void uploadStream(String examplebucket, InputStream inputStream){
//
//
//
//// 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
//        InputStream inputStream = new FileInputStream("D:\\localpath\\examplefile.txt");
//// 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
//        ossClient.putObject("examplebucket", "exampledir/exampleobject.txt", inputStream);
//
//// 关闭OSSClient。
//        ossClient.shutdown();
//
//    }
//
//}
