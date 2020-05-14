package com.example.myapplication.model;

import com.example.myapplication.R;

public class SecondStory {

    private Page[] pages;

    public SecondStory(){
        pages = new Page[9];

        pages[0] = new Page(R.drawable.page02, R.string.page02,
                new Choice(R.string.page02_choice1, 1),
                new Choice(R.string.page02_choice2, 2));

        pages[1] = new Page(R.drawable.page12,
                R.string.page12,
                new Choice(R.string.page12_choice1, 3),
                new Choice(R.string.page12_choice2, 3));

        pages[2] = new Page(R.drawable.page22,
                R.string.page22,
                new Choice(R.string.page22_choice1, 4),
                new Choice(R.string.page22_choice2, 5));

        pages[3] = new Page(R.drawable.page32,
                R.string.page32,
                new Choice(R.string.page32_choice1, 6),
                new Choice(R.string.page32_choice2, 6));

        pages[4] = new Page(R.drawable.page42,
                R.string.page42,
                new Choice(R.string.page42_choice1, 3),
                new Choice(R.string.page42_choice2, 3));

        pages[5] = new Page(R.drawable.page52,
                R.string.page52);

        pages[6] = new Page(R.drawable.page62, R.string.page62,
                new Choice(R.string.page62_choice1, 7),
                new Choice(R.string.page62_choice2, 8));

        pages[7] = new Page(R.drawable.page72, R.string.page72);

        pages[8] = new Page(R.drawable.page82, R.string.page82);


    }

    public Page getPage(int pageNumber){
        if(pageNumber >= pages.length){
            pageNumber = 0;
        }
        return pages[pageNumber];
    }
}

