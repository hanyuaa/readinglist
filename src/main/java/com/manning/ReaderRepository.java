package com.manning;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hanyu on 2018/3/26.
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {

    Reader findByUsername(String username);
}
