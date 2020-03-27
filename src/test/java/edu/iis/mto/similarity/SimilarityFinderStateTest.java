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
    private int [] differentSeq;

    @BeforeEach
    void init()
    {
        similarityFinder = new SimilarityFinder(new MockSearcher());
        emptySeq = new int [0];
        oneElementSeq = new int [] {3};
        exampleSeq = new int [] {9, 3, 7, 1, 0, 6, 200};
        differentSeq = new int [] {9, 3, 7, 1, 0, 6, 9};
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

    }

    @Test
    void bothSeqOneElementTest()
    {

    }

    @Test
    void identicalSeqTest()
    {

    }

    @Test
    void completelyDifferentSeqTest()
    {

    }

    @Test
    void slightlyDifferentSeqTest()
    {

    }

}