package com.manning;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Created by hanyu on 2018/3/24.
 */
public interface ReadingListRespository extends JpaRepository<Book,Long> {

    List<Book> findByReader(String reader);
    Book save(Book book);
}
