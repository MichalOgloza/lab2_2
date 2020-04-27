package edu.iis.mto.search;

import java.util.ArrayList;

public class SearcherDouble implements SequenceSearcher
{
    public int callCounter = 0;
    private ArrayList<SearchResult> results = new ArrayList<>();

    public void setResults(ArrayList<SearchResult> results)
    {
        this.results = results;
        callCounter = 0;
    }

    @Override
    public SearchResult search(int elem, int[] seq)
    {
        SearchResult result = results.get(callCounter);
        callCounter ++;
        return result;
    }
}
