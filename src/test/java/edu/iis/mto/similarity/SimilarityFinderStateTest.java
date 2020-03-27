package edu.iis.mto.similarity;

import edu.iis.mto.search.MockSearcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderStateTest
{
    private static final double COMPLETELY_DIFFERENT = 0.0d;
    private static final double IDENTICAL = 1.0d;
    private SimilarityFinder similarityFinder;
    private int [] emptySeq;
    private int [] oneElementSeq;
    private int [] exampleSeq;
    private int [] similarSeq;
    private int [] differentSeq;

    @BeforeEach
    void init()
    {
        similarityFinder = new SimilarityFinder(new MockSearcher());
        emptySeq = new int [0];
        oneElementSeq = new int [] {3};
        exampleSeq = new int [] {9, 3, 7, 1, 0, 6, 200};
        differentSeq = new int [] {8, 4, 5, 2, -1, 12, 300};
        similarSeq = new int [] {9, 3, 7, 1, 0, 6, 9};
    }

    @Test
    void firstSeqEmptyTest()
    {
        assertEquals(COMPLETELY_DIFFERENT, similarityFinder.calculateJackardSimilarity(emptySeq, exampleSeq));
    }

    @Test
    void secondSeqEmptyTest()
    {
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
        double result = similarityFinder.calculateJackardSimilarity(oneElementSeq, exampleSeq);
        assertTrue(result > COMPLETELY_DIFFERENT && result < IDENTICAL);
    }

    @Test
    void secondSeqOneElementTest()
    {
        double result = similarityFinder.calculateJackardSimilarity(exampleSeq, oneElementSeq);
        assertTrue(result > COMPLETELY_DIFFERENT && result < IDENTICAL);
    }

    @Test
    void bothSeqOneElementTest()
    {
        assertEquals(IDENTICAL, similarityFinder.calculateJackardSimilarity(oneElementSeq, oneElementSeq));
    }

    @Test
    void identicalSeqTest()
    {
        assertEquals(IDENTICAL, similarityFinder.calculateJackardSimilarity(exampleSeq, exampleSeq));
    }

    @Test
    void completelyDifferentSeqTest()
    {
        assertEquals(COMPLETELY_DIFFERENT, similarityFinder.calculateJackardSimilarity(exampleSeq, differentSeq));
    }

    @Test
    void slightlyDifferentSeqTest()
    {
        double result = similarityFinder.calculateJackardSimilarity(exampleSeq, similarSeq);
        assertTrue(result > COMPLETELY_DIFFERENT && result < IDENTICAL);
    }

}