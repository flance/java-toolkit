# java-toolkit
总结Java开发生涯中经常用到的工具类

- [FtpUtils.java](./src/main/java/com/github/flance/util/network/FtpUtils.java)  
    实现了 `FTP` 协议下的网络连接、文件上传、文件下载功能。
    
- [DocUtils.java](./src/main/java/com/github/flance/util/file/DocUtils.java)  
    实现一个简易 `word` 模版内容替换工具，兼容 `docx` 和 `doc` 格式。  
    使用时需要在 `word` 文档中通过占位符 `${key}` 表述对应的内容，然后构造一个 `Map<String key,String value>` 传入对应的模版文档中即可，该工具会自动用 `Map` 中的 `value` 替换文档中的对应的占位符 `key` 代表的数据。