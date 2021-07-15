package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            authors = Arrays.copyOf(authors, authors.length + 1);
            authors[authors.length - 1] = author;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (name.equals(author.getName()) && lastname.equals(author.getLastName())) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        //if author to delete exists
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            int authorIndex = 0;
            //finding index to remove
            for (Author a : authors) {
                if (author.equals(a)) {
                    break;
                }
                authorIndex++;
            }
            // Create and rebuild new array, skipping the author
            Author[] resultAuthorList = new Author[authors.length - 1];
            System.arraycopy(authors, 0, resultAuthorList, 0, authors.length - 1);
            System.arraycopy(authors, authorIndex + 1, resultAuthorList, authorIndex, authors.length - 1);
            authors = resultAuthorList;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}
