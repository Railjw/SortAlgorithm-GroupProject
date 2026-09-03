package ru.aston.extra;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;

public class Task4 {
    static class PartialCounter implements Callable<Integer> {
        private final Object objToSearch;
        private final List<Object> globalList;
        private final int startIndex;
        private final int nextChunkIndex;
        
        public PartialCounter(List<Object> globalList, Object objToSearch, int startIndex, int nextChunkIndex) {
            this.globalList = globalList;
            this.objToSearch = objToSearch;
            this.startIndex = startIndex;
            this.nextChunkIndex = nextChunkIndex;
        }
        
	@Override
	public Integer call() {
            Integer count = 0;
            
            for (int i = this.startIndex; i < this.nextChunkIndex; i++) {
                if (this.objToSearch.equals(this.globalList.get(i))) {
                    count++;
                }
            }
            
            return count;
	}
    }
    
    public static void calcSameObjectsCountAndPrint(Object testObj, List globalList) {
        if (testObj == null) {
            System.out.println("testObj is null, nothing to search!");
            return;
        }
        
        if (globalList == null) {
            System.out.println("globalList is null or empty, nowhere to search!");
            return;
        }
        
        if (globalList.isEmpty()) {
            System.out.println("Same objects count is 0.");
            return;
        }
        
        int logicalCores = Runtime.getRuntime().availableProcessors();
        int threadsCountToRun = Math.min(logicalCores, globalList.size());
        List<Future<Integer>> countChunks = new LinkedList<Future<Integer>>();
        ExecutorService executor = Executors.newFixedThreadPool(threadsCountToRun);       
        
        for (int i = 0; i < threadsCountToRun; i++) {
            int startIndex = (globalList.size() * i) / threadsCountToRun;
            int nextChunkIndex = (globalList.size() * (i + 1)) / threadsCountToRun;
            PartialCounter tempPC = new PartialCounter(globalList, testObj, startIndex, nextChunkIndex);
            Future<Integer> tempForecast = executor.submit(tempPC);
            countChunks.add(tempForecast);
	}
        
        int finalCount = 0;
        
        for (int i = 0; i < countChunks.size(); i++) {
            int tempCount = 0;
            
            try {
                tempCount = countChunks.get(i).get();
            } catch (InterruptedException | ExecutionException ex) {
                System.out.println("Something went wrong (" + ex.getMessage() + ") at receiving future value, impossible to get correct result now...");
                executor.shutdown();
                return;
            }
            
            finalCount += tempCount;
        }
        
	executor.shutdown();
        
        System.out.println("Same objects count is " + finalCount);
    }    
}