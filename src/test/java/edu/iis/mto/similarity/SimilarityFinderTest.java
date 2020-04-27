package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SearcherDouble;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderTest
{
    private static final double COMPLETELY_DIFFERENT = 0.0d;
    private static final double IDENTICAL = 1.0d;
    private SearcherDouble searcherDouble;
    private SimilarityFinder similarityFinder;
    private int [] emptySeq;
    private int [] oneElementSeq;
    private int [] exampleSeq;
    private int [] similarSeq;
    private int [] differentSeq;


    @BeforeEach
    void init()
    {
        searcherDouble = new SearcherDouble();
        similarityFinder = new SimilarityFinder(searcherDouble);
        emptySeq = new int [0];
        oneElementSeq = new int [] {3};
        exampleSeq = new int [] {9, 3, 7, 1, 0, 6, 200};
        differentSeq = new int [] {8, 4, 5, 2, -1, 12, 300};
        similarSeq = new int [] {9, 3, 7, 1, 0, 6, 9};
    }

    //Behaviour

    @Test
    void ArgumentNullTest()
    {
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(null, exampleSeq));
    }

    @Test
    void numberOfCallsTest()
    {
        ArrayList<SearchResult> results = buildTrueResults(exampleSeq.length);
        searcherDouble.setResults(results);

        similarityFinder.calculateJackardSimilarity(exampleSeq, exampleSeq);
        assertEquals(exampleSeq.length, searcherDouble.callCounter);
    }

    //State

    @Test
    void firstSeqEmptyTest()
    {
        assertEquals(COMPLETELY_DIFFERENT, similarityFinder.calculateJackardSimilarity(emptySeq, exampleSeq));
    }

    @Test
    void secondSeqEmptyTest()
    {
        ArrayList<SearchResult> results = buildFalseResults(exampleSeq.length);
        searcherDouble.setResults(results);

        assertEquals(COMPLETELY_DIFFERENT, similarityFinder.calculateJackardSimilarity(exampleSeq, emptySeq));
    }

    @Test
    void bothSeqEmptyTest()
    {
        assertEquals(IDENTICAL, similarityFinder.calculateJackardSimilarity(emptySeq, emptySeq));
    }

    @Test
    void firstSeqOneElementTest()
    {
        ArrayList<SearchResult> results = new ArrayList<>();
        results.add(SearchResult.builder().withPosition(2).withFound(true).build());

        searcherDouble.setResults(results);

        double result = similarityFinder.calculateJackardSimilarity(oneElementSeq, exampleSeq);
        assertTrue(result > COMPLETELY_DIFFERENT && result < IDENTICAL);
    }

    @Test
    void secondSeqOneElementTest()
    {
        ArrayList<SearchResult> results = buildFalseResults(exampleSeq.length);
        results.set(1, SearchResult.builder().withPosition(0).withFound(true).build());

        searcherDouble.setResults(results);

        double result = similarityFinder.calculateJackardSimilarity(exampleSeq, oneElementSeq);
        assertTrue(result > COMPLETELY_DIFFERENT && result < IDENTICAL);
    }

    @Test
    void bothSeqOneElementTest()
    {
        ArrayList<SearchResult> results = new ArrayList<>();
        results.add(SearchResult.builder().withPosition(0).withFound(true).build());

        searcherDouble.setResults(results);

        assertEquals(IDENTICAL, similarityFinder.calculateJackardSimilarity(oneElementSeq, oneElementSeq));
    }

    @Test
    void identicalSeqTest()
    {
        ArrayList<SearchResult> results = buildTrueResults(exampleSeq.length);
        searcherDouble.setResults(results);

        assertEquals(IDENTICAL, similarityFinder.calculateJackardSimilarity(exampleSeq, exampleSeq));
    }

    @Test
    void completelyDifferentSeqTest()
    {
        ArrayList<SearchResult> results = buildFalseResults(exampleSeq.length);
        searcherDouble.setResults(results);

        assertEquals(COMPLETELY_DIFFERENT, similarityFinder.calculateJackardSimilarity(exampleSeq, differentSeq));
    }

    @Test
    void slightlyDifferentSeqTest()
    {
        ArrayList<SearchResult> results = buildTrueResults(exampleSeq.length);
        results.set(6, SearchResult.builder().withPosition(-1).withFound(false).build());

        searcherDouble.setResults(results);

        double result = similarityFinder.calculateJackardSimilarity(exampleSeq, similarSeq);
        assertTrue(result > COMPLETELY_DIFFERENT && result < IDENTICAL);
    }

    private ArrayList<SearchResult> buildTrueResults(int len)
    {
        ArrayList<SearchResult> results = new ArrayList<>();

        for (int i = 0; i < len; i++)
        {
            results.add(SearchResult.builder()
                    .withFound(true)
                    .withPosition(i)
                    .build());
        }
        return results;
    }

    private ArrayList<SearchResult> buildFalseResults(int len)
    {
        ArrayList<SearchResult> results = new ArrayList<>();

        for (int i = 0; i < len; i++)
        {
            results.add(SearchResult.builder()
                    .withFound(false)
                    .withPosition(-1)
                    .build());
        }
        return results;
    }
}