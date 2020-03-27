package edu.iis.mto.similarity;

import edu.iis.mto.search.MockSearcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityFinderBehaviourTest
{
    SimilarityFinder similarityFinder;
    int [] exampleSeq;

    @BeforeEach
    void init()
    {
        similarityFinder = new SimilarityFinder(new MockSearcher());
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

    }
}