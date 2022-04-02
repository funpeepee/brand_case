package service.impl;

import mapper.BrandMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Brand;
import pojo.pageBean;
import service.BrandService;
import util.SqlSessionFactoryUtil;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    private static SqlSessionFactory sqlSessionFactory= SqlSessionFactoryUtil.createSqlSessionFactory();
    @Override
    public List<Brand> selectAll() {
        SqlSession sqlsession=sqlSessionFactory.openSession();
        BrandMapper brandMapper=sqlsession.getMapper(BrandMapper.class);
        List<Brand> brands= brandMapper.selectAll();
        sqlsession.close();
        return brands;
    }

    @Override
    public int add(Brand brand) {
        SqlSession session=sqlSessionFactory.openSession();
        BrandMapper brandMapper=session.getMapper(BrandMapper.class);
        int result= brandMapper.add(brand);
        session.commit();
        session.close();
        return result;
    }

    @Override
    public int deleteById(Integer id) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BrandMapper brandMapper=sqlSession.getMapper(BrandMapper.class);
        int result=brandMapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    @Override
    public int deleteByIds(int[] ids) {
        SqlSession sqlSession= sqlSessionFactory.openSession();
        BrandMapper brandMapper=sqlSession.getMapper(BrandMapper.class);
        int result=brandMapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    @Override
    public int updateById(Brand brand) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BrandMapper brandMapper=sqlSession.getMapper(BrandMapper.class);
        int result=brandMapper.updateById(brand);
        sqlSession.commit();
        sqlSession.close();
        return result;
    }

    @Override
    public Brand selectById(Integer id) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BrandMapper brandMapper=sqlSession.getMapper(BrandMapper.class);
        Brand brand=brandMapper.selectById(id);
        sqlSession.close();
        return brand;
    }

    @Override
    public pageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BrandMapper brandMapper=sqlSession.getMapper(BrandMapper.class);
       //计算查询条目数
        int size=pageSize;
        //计算开始索引
        int begin=(currentPage-1)*size;
        //查询当前页数据
        List<Brand> rows= brandMapper.selectByPage(begin,size);
        //查询总计
        int totalCount=brandMapper.selectTotalCount();
        //封装成pagebean对象
        pageBean<Brand> pageBean=new pageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public pageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        BrandMapper brandMapper=sqlSession.getMapper(BrandMapper.class);
        //计算开始索引
        int begin=(currentPage-1)*pageSize;
        //计算查询条目数
        int size=pageSize;
        //查询当前页数据
        String brandName=brand.getBrandName();
        String companyName=brand.getCompanyName();
        if(brandName !=null && brandName.length()>0){
            brand.setBrandName("%"+brandName+"%");
        }
        if(companyName != null && companyName.length()>0){
            brand.setCompanyName("%"+companyName+"%");
        }
        List<Brand> rows= brandMapper.selectByPageAndCondition(begin,size,brand);
        //查询所有数据数
        int totalCount=brandMapper.selectByConditionTotalCount(brand);
        pageBean<Brand> pageBean=new pageBean<>();
        pageBean.setTotalCount(totalCount);
        pageBean.setRows(rows);
        sqlSession.close();
        return pageBean;
    }
}
