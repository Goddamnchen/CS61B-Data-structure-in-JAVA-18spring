package lab9tester;

import static org.junit.Assert.*;

import org.junit.Test;
import lab9.BSTMap;

import java.util.Set;

/**
 * Tests by Brendan Hu, Spring 2015, revised for 2018 by Josh Hug
 * Revised for integrated implementation of BSTMap by Guanting Chen
 */
public class TestBSTMap {

    @Test
    public void sanityGenericsTest() {
        try {
            BSTMap<String, String> a = new BSTMap<String, String>();
            BSTMap<String, Integer> b = new BSTMap<String, Integer>();
            BSTMap<Integer, String> c = new BSTMap<Integer, String>();
            BSTMap<Boolean, Integer> e = new BSTMap<Boolean, Integer>();
        } catch (Exception e) {
            fail();
        }
    }

    //assumes put/size/containsKey/get work
    @Test
    public void sanityClearTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1 + i);
            //make sure put is working via containsKey and get
            assertTrue(null != b.get("hi" + i));
            assertTrue(b.get("hi" + i).equals(1 + i));
            assertTrue(b.containsKey("hi" + i));
        }
        assertEquals(455, b.size());
        b.clear();
        assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertTrue(null == b.get("hi" + i) && !b.containsKey("hi" + i));
        }
    }

    // assumes put works
    @Test
    public void sanityContainsKeyTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
    }

    // assumes put works
    @Test
    public void sanityGetTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(null, b.get("starChild"));
        assertEquals(0, b.size());
        b.put("starChild", 5);
        assertTrue(((Integer) b.get("starChild")).equals(5));
        b.put("KISS", 5);
        assertTrue(((Integer) b.get("KISS")).equals(5));
        assertNotEquals(null, b.get("starChild"));
        assertEquals(2, b.size());
    }

    // assumes put works
    @Test
    public void sanitySizeTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(0, b.size());
        b.put("hi", 1);
        assertEquals(1, b.size());
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        assertEquals(456, b.size());
    }

    //assumes get/containskey work
    @Test
    public void sanityPutTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi", 1);
        assertTrue(b.containsKey("hi"));
        assertTrue(b.get("hi") != null);
    }

    //assume remove(key) work
    @Test
    public void sanityRemove1Test() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi", 1);
        b.put("hi1", 2);
        int expect = 2;     //expected size
        assertEquals(expect, b.size());       //verify size after insertion
        int removedValue = b.remove("hi");
        assertTrue(!b.containsKey("hi"));
        assertTrue(b.containsKey("hi1"));
        assertEquals(1, removedValue);
        assertEquals(1, b.size());
    }
    @Test
    public void sanityRemove2Test() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi", 0);
        b.put("hi2", 2);
        b.put("hi1", 1);
        Object rm1 = b.remove("hi", 2);
        assertEquals(null, rm1);
        Object rm2 = b.remove("hi2", 2);
        assertEquals(2, rm2);
        assertEquals(2, b.size());
    }
    @Test
    public void sanityKeysSetTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 10; i++) {
            b.put("hi" + i, 1);
        }
        b.remove("hi9");
        Set<String> bSet = b.keySet();
        assertEquals(9, bSet.size());
        for (int i = 0; i < 9; i++) {
            bSet.contains("hi" + i);
        }
    }
    @Test
    public void sanityIteratorTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 10; i++) {
            b.put("hi" + i, 1);
        }
        b.remove("hi9");
        for(String key : b) {
            System.out.println(key);
        }
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestBSTMap.class);
    }
}
