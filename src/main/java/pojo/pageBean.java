package pojo;

import java.util.List;

/**
 * 分页实体
 */
public class pageBean<T> {
    private Integer totalCount;//总记录数
    private List<T> rows;//当前页数据

    public pageBean() {
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
