package org.sidestudio.app.services;

import org.sidestudio.app.domain.Book;
import org.sidestudio.app.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Sample 서비스 레이어
 *
 * @author logan
 * @since 2016-03-04
 */
@Service
public class SampleService {

    @Autowired
    private SampleRepository sampleRepository;

    // 선언적 트랜적선 처리 Rollback 처리
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertUser(Book book){

        sampleRepository.insertBook(book);
    }
}
