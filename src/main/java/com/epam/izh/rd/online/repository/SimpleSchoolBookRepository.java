package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] booksByName = new SchoolBook[0];
        for (SchoolBook schoolBook : schoolBooks) {
            if (name.equals(schoolBook.getName())) {
                booksByName = Arrays.copyOf(booksByName, booksByName.length + 1);
                booksByName[booksByName.length - 1] = schoolBook;
            }
        }
        return booksByName;
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] booksToRemove = findByName(name);
        //if books to delete exist
        if (booksToRemove.length != 0) {
            int i = 0;
            //Create and rebuild new array, skipping the "name"
            SchoolBook[] resultBooksList = new SchoolBook[schoolBooks.length - booksToRemove.length];
            for (SchoolBook schoolBook : schoolBooks) {
                if (!schoolBook.getName().equals(name)) {
                    resultBooksList[i] = schoolBook;
                    i++;
                }
            }
            schoolBooks = resultBooksList;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
