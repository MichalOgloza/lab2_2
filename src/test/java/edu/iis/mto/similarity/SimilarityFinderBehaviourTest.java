package edu.iis.mto.similarity;

import edu.iis.mto.search.MockSearcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderBehaviourTest
{
    SimilarityFinder similarityFinder;
    MockSearcher mockSearcher;
    int [] exampleSeq;

    @BeforeEach
    void init()
    {
        mockSearcher = new MockSearcher();
        similarityFinder = new SimilarityFinder(mockSearcher);
        exampleSeq = new int[] {9, 3, 0, 10};
    }

    @Test
    void firstArgumentNullTest()
    {
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(null, exampleSeq));
    }

    @Test
    void secondArgumentNullTest()
    {
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(exampleSeq, null));
    }

    @Test
    void bothArgumentNullTest()
    {
        assertThrows(NullPointerException.class, () -> similarityFinder.calculateJackardSimilarity(null, null));
    }

    @Test
    void numberOfCallsTest()
    {
        similarityFinder.calculateJackardSimilarity(exampleSeq, exampleSeq);
        assertEquals(exampleSeq.length, mockSearcher.callCounter);
    }
}