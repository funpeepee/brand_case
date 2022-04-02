package service;

import org.apache.ibatis.annotations.Param;
import pojo.Brand;
import pojo.pageBean;

import java.util.List;

public interface BrandService {
    /**
     * 查找所有数据
     */
    List<Brand>  selectAll();
    /**
     * 增加数据
     */
    int add(Brand brand);
    /**
     * 根据ID删除数据
     */
    int deleteById(Integer id);
    /**
     * 根据IDS批量删除数据
     */
    int deleteByIds(int[] ids);
    /**
     * 根据ID更改数据
     */
    int updateById(Brand brand);
    /**
     * 根据ID查找数据
     */
    Brand selectById(Integer id);
    /**
     * 分页查询
     */
    pageBean<Brand> selectByPage(int currentPage,int pageSize);
    /**
     * 多条件查询
     */
    pageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize, Brand brand);

}
