package com.example.myapplication.model;

import com.example.myapplication.R;

public class ThirdStory {

    private Page[] pages;

    public ThirdStory(){
        pages = new Page[7];

        pages[0] = new Page(R.drawable.page03, R.string.page03,
                new Choice(R.string.page03_choice1, 1),
                new Choice(R.string.page03_choice2, 2));

        pages[1] = new Page(R.drawable.page13,
                R.string.page13,
                new Choice(R.string.page13_choice1, 3),
                new Choice(R.string.page13_choice2, 3));

        pages[2] = new Page(R.drawable.page23,
                R.string.page23,
                new Choice(R.string.page23_choice1, 4),
                new Choice(R.string.page23_choice2, 4));

        pages[3] = new Page(R.drawable.page33,
                R.string.page33,
                new Choice(R.string.page33_choice1, 4),
                new Choice(R.string.page33_choice2, 4));

        pages[4] = new Page(R.drawable.page43,
                R.string.page43,
                new Choice(R.string.page43_choice1, 5),
                new Choice(R.string.page43_choice2, 6));

        pages[5] = new Page(R.drawable.page53,
                R.string.page53);

        pages[6] = new Page(R.drawable.page53, R.string.page63);
    }

    public Page getPage(int pageNumber){
        if(pageNumber >= pages.length){
            pageNumber = 0;
        }
        return pages[pageNumber];
    }
}
