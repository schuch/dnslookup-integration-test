# dnslookup-integration-test
Shows how to write a junit integration test for a customized dns lookup configuration

Uses https://github.com/resteasy/Resteasy/tree/master/eagledns to create a java based dns server for testing.
How to customize dns lookup is documented here: https://docs.oracle.com/javase/8/docs/technotes/guides/net/properties.html

Note that the test only succeeds when executed during the maven build. When running in an IDE (e.g. Eclipse), setting the system properties @BeforeClass is too late, because InetAddress has already instantiated its dns providers.
