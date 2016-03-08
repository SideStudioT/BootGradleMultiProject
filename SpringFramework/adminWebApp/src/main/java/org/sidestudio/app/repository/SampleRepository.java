package org.sidestudio.app.repository;

import org.sidestudio.app.domain.Book;
import org.sidestudio.app.domain.SessionUserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Sample Repository
 *
 * @author logan
 * @since 2016-03-04
 */
@Repository
public interface SampleRepository {

    public List<Book> selectBookAllList();

    public void insertBook(Book book);

    public SessionUserDetails selectUserInfoById(Map<String, Object> parameterMap);
}
