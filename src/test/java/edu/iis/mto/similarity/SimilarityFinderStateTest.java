package edu.iis.mto.similarity;

import edu.iis.mto.search.MockSearcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderStateTest
{
    private static final double COMPLETELY_DIFFERENT = 0;
    private static final double IDENTICAL = 1;
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

    }

    @Test
    void bothSeqEmptyTest()
    {

    }

    @Test
    void firstSeqOneElementTest()
    {

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