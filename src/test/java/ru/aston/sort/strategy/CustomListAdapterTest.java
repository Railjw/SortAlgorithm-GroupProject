package ru.aston.sort.strategy;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;
import ru.aston.customcollection.CustomListAdapter;

public class CustomListAdapterTest {
    @Test
    void testBasicOperations() {
        CustomListAdapter<Integer> cla = new CustomListAdapter<Integer>();
        cla.add(1);
        cla.add(2);
        cla.add(3);
        
        assertEquals(3, cla.size());
        assertEquals(1, cla.get(0));
        assertEquals(2, cla.get(1));
        assertEquals(3, cla.get(2));              
        
        cla.clear();
        
        assertEquals(0, cla.size());
        assertEquals(true, cla.isEmpty());
        
        List<Integer> srcLst = new ArrayList<Integer>();
        srcLst.add(1);
        srcLst.add(2);
        srcLst.add(3);
        cla = new CustomListAdapter<Integer>(srcLst);
        
        assertEquals(3, cla.size());
        assertEquals(false, cla.isEmpty());
        assertEquals(1, cla.get(0));
        assertEquals(2, cla.get(1));
        assertEquals(3, cla.get(2));              
        
        assertEquals(true, cla.contains(1));
        assertEquals(false, cla.contains(5));
        
        Iterator<Integer> itr0 = cla.iterator();
        assertEquals(true, itr0.hasNext());
        itr0.next();
        assertEquals(true, itr0.hasNext());
        itr0.next();
        assertEquals(true, itr0.hasNext());
        itr0.next();
        assertEquals(false, itr0.hasNext());
        
        assertArrayEquals(new Integer[] { 1, 2, 3 }, cla.toArray());
        assertArrayEquals(new Integer[] { 1, 2, 3 }, cla.toArray(new Integer[] { null, null }));
        assertArrayEquals(new Integer[] { 1, 2, 3, null }, cla.toArray(new Integer[] { null, null, null, null }));
        
        cla.remove(Integer.valueOf(2));
        assertArrayEquals(new Integer[] { 1, 3 }, cla.toArray());
        
        cla.add(1, 2);
        assertArrayEquals(new Integer[] { 1, 2, 3 }, cla.toArray());
        
        cla.remove(0);
        assertArrayEquals(new Integer[] { 2, 3 }, cla.toArray());
        assertEquals(true, cla.containsAll(srcLst.subList(1, 3)));
        assertEquals(false, cla.containsAll(srcLst.subList(0, 2)));
        
        cla.add(0, 1);
        
        cla.addAll(srcLst);
        assertArrayEquals(new Integer[] { 1, 2, 3, 1, 2, 3 }, cla.toArray());
        
        cla.removeAll(srcLst);
        assertEquals(true, cla.isEmpty());
        
        cla.addAll(srcLst);
        
        srcLst.remove((Object)2);
        cla.retainAll(srcLst);
        assertArrayEquals(new Integer[] { 1, 3 }, cla.toArray());
        
        cla.set(0, 10);
        assertEquals(10, cla.get(0));
        
        cla.set(0, 1);
        cla.add(1, 2);
        
        assertEquals(1, cla.indexOf(2));
        assertEquals(-1, cla.indexOf(4));
        assertEquals(1, cla.lastIndexOf(2));
        assertEquals(-1, cla.lastIndexOf(4));
        
        assertArrayEquals(new Integer[] { 1, 2 }, cla.subList(0, 2).toArray());
        
        List<Integer> testLst = new ArrayList<Integer>();
        ListIterator<Integer> lstItr = cla.listIterator();
        
        while(lstItr.hasNext())
            testLst.add(lstItr.next());
        
        assertArrayEquals(cla.toArray(), testLst.toArray());
        
        testLst.clear();
        lstItr = cla.listIterator(1);
        
        while(lstItr.hasNext())
            testLst.add(lstItr.next());
        
        assertArrayEquals(cla.subList(1, 3).toArray(), testLst.toArray());
        
        cla.clear();        
    }
}