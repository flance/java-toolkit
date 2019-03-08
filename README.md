# java-toolkit
总结Java开发生涯中经常用到的工具类

- [FtpUtils.java](./src/main/java/com/github/flance/util/network/FtpUtils.java)  
    实现了 `FTP` 协议下的网络连接、文件上传、文件下载功能。
    
- [DocUtils.java](./src/main/java/com/github/flance/util/file/DocUtils.java)  
    实现一个简易 `word` 模版内容替换工具，兼容 `docx` 和 `doc` 格式。  
    使用时需要在 `word` 文档中通过占位符 `${key}` 表述对应的内容，然后构造一个 `Map<String key,String value>` 传入对应的模版文档中即可，该工具会自动用 `Map` 中的 `value` 替换文档中的对应的占位符 `key` 代表的数据。

- [MoneyUtils.java](./src/main/java/com/github/flance/util/number/MoneyUtils.java)  
    货币格式化工具，可以将金额转换为国际通用的千分位分隔格式的显示方式，使之看起来更直观。
    
- [DateFormatUtils.java](./src/main/java/com/github/flance/util/time/DateFormatUtils.java)  
    日期时间格式化工具，将日期按照指定格式转化为 `ISO` 标准显示方式，或者国内通用的常规显示格式。
    
-[Axis2StubUtil.java](./src/main/java/com/github/flance/util/file/Axis2StubUtil.java)
    Axis2 生成的 `Webservice stub` 文件处理工具，可以删除所有毫无意义的单行和多行注释、删除所有空行、简化生成的代码中对 `java.lang.**` 的垃圾引用，尽最大努力压缩简化 `stub` 文件。