package edu.iis.mto.search;

public class MockSearcher implements SequenceSearcher
{
    public int callCounter = 0;

    @Override
    public SearchResult search(int elem, int[] seq)
    {
        callCounter++;
        for(int i = 0; i < seq.length; i++)
        {
            if(seq[i] == elem)
            {
                return SearchResult.builder()
                        .withFound(true)
                        .withPosition(i)
                        .build();
            }
        }
        return SearchResult.builder()
                .withFound(false)
                .withPosition(-1)
                .build();
    }
}
