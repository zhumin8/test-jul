library is responsible for configuring log output for library's package, this is done Programmatically.

`user-app-jul` is not affected because library use reflection to detect logback presence. 

with no code/config changes in user-app,

output from `library.log`. Format not exactly right because of parsing logic in `CustomFieldConverter`

```asciidoc

{"timestamp": "2025-01-14T16:07:21.694Z", "level": "INFO", "logger": "org.library.MyLibrary", "message": "this is a logging message from library "  "}
{"timestamp": "2025-01-14T16:07:21.702Z", "level": "INFO", "logger": "org.library.MyLibrary", "message": "This is a message  from library with    and "  ", "userId": "user123", "origin": "library", "transactionId": "tx456"}
{"timestamp": "2025-01-14T16:07:21.703Z", "level": "DEBUG", "logger": "org.library.MyLibrary", "message": "This is a debug level message from library  "  ", "origin": "library"}
{"timestamp": "2025-01-14T16:07:21.703Z", "level": "INFO", "logger": "org.library.MyLibrary", "message": "From library: isLogbackPresent?true"  "}
```
This output format is hardcoded and not customizable in user-app now. 

output from `my-app.log`, has both library and user-app log output because root logger is configured in logback.xml
```asciidoc
{"@timestamp":"2025-01-14T16:07:21.694308689-05:00","@version":"1","message":"this is a logging message from library ","logger_name":"org.library.MyLibrary","thread_name":"org.example.Main.main()","level":"INFO","level_value":20000,"caller_class_name":"org.library.MyLibrary","caller_method_name":"testLogging","caller_file_name":"MyLibrary.java","caller_line_number":30}
{"@timestamp":"2025-01-14T16:07:21.702995827-05:00","@version":"1","message":"This is a message  from library with {userId=user123}  {origin=library} and {transactionId=tx456}","logger_name":"org.library.MyLibrary","thread_name":"org.example.Main.main()","level":"INFO","level_value":20000,"caller_class_name":"org.library.MyLibrary","caller_method_name":"testLogging","caller_file_name":"MyLibrary.java","caller_line_number":31}
{"@timestamp":"2025-01-14T16:07:21.703427847-05:00","@version":"1","message":"This is a debug level message from library {origin=library} ","logger_name":"org.library.MyLibrary","thread_name":"org.example.Main.main()","level":"DEBUG","level_value":10000,"caller_class_name":"org.library.MyLibrary","caller_method_name":"testLogging","caller_file_name":"MyLibrary.java","caller_line_number":32}
{"@timestamp":"2025-01-14T16:07:21.703775617-05:00","@version":"1","message":"From library: isLogbackPresent?true","logger_name":"org.library.MyLibrary","thread_name":"org.example.Main.main()","level":"INFO","level_value":20000,"caller_class_name":"org.library.MyLibrary","caller_method_name":"testLogging","caller_file_name":"MyLibrary.java","caller_line_number":34}
{"@timestamp":"2025-01-14T16:07:21.703946817-05:00","@version":"1","message":"This is log item from user app","logger_name":"org.library.Main","thread_name":"org.example.Main.main()","level":"INFO","level_value":20000,"caller_class_name":"org.example.Main","caller_method_name":"main","caller_file_name":"Main.java","caller_line_number":17}
{"@timestamp":"2025-01-14T16:07:21.704071607-05:00","@version":"1","message":"This is debug message from user app","logger_name":"org.library.Main","thread_name":"org.example.Main.main()","level":"DEBUG","level_value":10000,"caller_class_name":"org.example.Main","caller_method_name":"main","caller_file_name":"Main.java","caller_line_number":19}
```