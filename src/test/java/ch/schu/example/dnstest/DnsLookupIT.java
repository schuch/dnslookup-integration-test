package ch.schu.example.dnstest;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.unlogic.eagledns.EagleDNS;

public class DnsLookupIT {

    private EagleDNS dnsserver;
    
    @BeforeClass
    public static void init() {
        System.setProperty("sun.net.spi.nameservice.nameservers", "127.0.0.1");
        System.setProperty("sun.net.spi.nameservice.provider.1", "default");
        System.setProperty("sun.net.spi.nameservice.provider.2", "dns,sun");
    }
    
    @Before
    public void setup() throws UnknownHostException {
        dnsserver = new EagleDNS(); 
        dnsserver.setConfigClassPath("dns/config.xml");
        dnsserver.start();
    }
    
    @Test
    public void testInternal() throws UnknownHostException {
        InetAddress address = InetAddress.getByName("samplezone.org");
        assertEquals("1.2.3.4", address.getHostAddress());
    }
    
    @Test
    public void testExternal() throws UnknownHostException {
        InetAddress address = InetAddress.getByName("google.com");
        assertNotNull(address.getHostAddress());
        assertNotEquals("1.2.3.4", address.getHostAddress());
    }
    
    @Test(expected = UnknownHostException.class)
    public void testUnknown() throws UnknownHostException {
        InetAddress.getByName("unknown_1234_xxx.org");
    }
    
    @After
    public void tearDown() {
        dnsserver.shutdown();
    }

}
