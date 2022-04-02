package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Brand;

import java.util.List;

public interface BrandMapper {
    /**
     * 查询所有数据
     */
    List<Brand> selectAll();
    /**
     * 增加数据
     */
    int add(Brand brand);
    /**
     * 根据ID删除数据
     */
    int deleteById(Integer id);
    /**
     * 根据ID批量删除数据
     */
    int deleteByIds(@Param("ids")int[] ids);
    /**
     * 根据ID更新数据
     */
    int updateById(Brand brand);
    /**
     * 根据ID查询数据
     */
    Brand selectById(Integer id);
    /**
     * 查询总记录数
     */
    int selectTotalCount();
    /**
     * 查询当前页数据
     */
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);
    /**
     * 多条件查询
     */
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("brand") Brand brand);
    /**
     * 条件查询总页数
     */
    int selectByConditionTotalCount(Brand brand);
}
