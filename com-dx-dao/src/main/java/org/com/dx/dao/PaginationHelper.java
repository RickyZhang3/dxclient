package org.com.dx.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class PaginationHelper<E> {
	
	private static final Logger log = LoggerFactory.getLogger(PaginationHelper.class);
	
	public Page<E> fetchPage(
            final JdbcTemplate jdbcTemplate,
            final String sqlCount,
            final String sqlFetch,
            final Object args[],
            final int pageNo,
            final int pageSize,
            final RowMapper<E> rowMapper) {

        // determine how many rows are available
        final int rowCount = jdbcTemplate.queryForObject(sqlCount, args,Integer.class);
        
        log.info("rowCount:{}",rowCount);

        // calculate the number of pages
        int pageCount = rowCount / pageSize;
        if (rowCount > pageSize * pageCount) {
            pageCount++;
        }

        // create the page object
        final Page<E> page = new Page<E>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotalCount(rowCount);

        // fetch a single page of results
        final int startRow = (pageNo - 1) * pageSize;
        jdbcTemplate.query(
                sqlFetch,
                args,
                new ResultSetExtractor() {
                    public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                        final List pageItems = page.getResultList();
                        int currentRow = 0;
                        while (rs.next() && currentRow <startRow + pageSize) {
                            if (currentRow >= startRow) {
                                pageItems.add(rowMapper.mapRow(rs, currentRow));
                            }
                            currentRow++;
                        }
                        return page;
                    }
                });
        return page;
    }
	
}
