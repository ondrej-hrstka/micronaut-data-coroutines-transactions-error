1. In example application run `./gradlew test --tests '*normal*'` which works.
2. Run `./gradlew test --tests '*coroutines*'` which fails
3. Run `./gradlew test --tests '*timeout*'` which also fails, resulting into timeout
4. Run `./gradlew test`. First 2 tests passes, last 3 not. However, the test `normal2` should pass as well. 
Also the `timeoutTest` test should fail, but for some reason actually passes.
