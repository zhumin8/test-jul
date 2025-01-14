This user-app uses `jul-to-slf4j` to bridge library's JUL logs to logback.
In addition, it configures a conversionRule rule to parse and output extra fields from JUL message.

sample log output:

(note format is not json. because logback config with PatternLayoutEncoder)
```asciidoc
16:14:49.552 [main] INFO  org.library.MyLibrary - this is a logging message from library  {}
16:14:49.555 [main] INFO  org.library.MyLibrary - This is a message  from library with {userId=user123}  {origin=library} and {transactionId=tx456} {"userId": "user123", "origin": "library", "transactionId": "tx456"}
16:14:49.555 [main] DEBUG org.library.MyLibrary - This is a debug level message from library {origin=library}  {"origin": "library"}
16:14:49.555 [main] INFO  org.library.Main - This is log item from user app {}
16:14:49.556 [main] INFO  org.library.Main - Another message from user app {}
16:14:49.556 [main] DEBUG org.library.Main - This is debug message from user app {}

```